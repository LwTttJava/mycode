package thread;


import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author luotao
 * @date 2022-3-10  22:10
 */
public class ThreadDemo {

    private volatile int state=1;


    // 继承于 Thread
    public void test1(){
        class A extends Thread{
            @Override
            public void run() {
                System.out.println("创建线程继承于Thread");
            }
        }
        A a = new A();
        a.start();
    }
    // 实现接口Runnable 然后用Thread方式启动
    // new Thread(Runnable runnable).start()
    public void test2(){
        class B implements Runnable{
            @Override
            public void run() {
                System.out.println("创建线程实现Runnable接口");
            }
        }
        B b =new B();
        new Thread(b).start();
    }
    // new Thread(new FutureTask(Callable callable)).start()
    public void test3(){
        Callable callable = new Callable() {
             int sum = 0;
            @Override
            public Object call() throws Exception {
                for (int i = 0;i < 5;i ++) {
                    sum += i;
                }
                return sum;
            }
        };

        FutureTask futureTask = new FutureTask(callable);
        new Thread(futureTask).start();
        try {
            System.out.println(futureTask.get());
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void test4(){
        ExecutorService executor = Executors.newFixedThreadPool(5);
        //execute直接执行线程
        executor.execute(new Thread());
        executor.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("aaaa");
            }
        });
        //submit
        Future<String> future = executor.submit(new Callable<String>() {

            @Override
            public String call() throws Exception {
                return "a";
            }
        });

        Future<String> future1 = executor.submit(() -> {
            return "a";
        });
        try {
            System.out.println(future.get());
            System.out.println(future1.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

    }

    volatile static int flag = 1;
    volatile static Object o = new Object();


    public synchronized void test6(String t){
        while(true){

            try {

                Thread.sleep(2000);
                System.out.println(t+" wait");
                o.wait();
                System.out.println(t + "结束");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public static void test5(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    synchronized (o) {
                        System.out.println("t run");
                        if (flag == 0) {
                            try {
                                Thread.sleep(2000);
                                System.out.println("Thread t1 wait");
                                o.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        System.out.println("t1 notify 线程");
                        flag = 0;
                        //通知等待队列的一个线程获取锁
                        o.notifyAll();
                    }
                }
            }
        }).start();
        new Thread(()->{
            while (true){
                synchronized (o) {
                    if (flag == 1) {
                        try {
                            Thread.sleep(1000);
                            //System.out.println("Thread t2 wait");
                            o.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("thread2 run");
                    System.out.println("t2 notify 线程");
                    flag = 1;
                    //通知等待队列的一个线程获取锁
                    o.notifyAll();
                }
            }
        }).start();
    }



    public static void main(String[] args) {
        LockSupport.park();
        //test5();

        //test7();

        //test9();
    }

    private static void test9() {
        CountDownLatch o = new CountDownLatch(1);
        CountDownLatch latch = new CountDownLatch(4);

        Executors.newScheduledThreadPool(1);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 3, 0,
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(3),new MyThreadFactory()
               ,new ThreadPoolExecutor.AbortPolicy());
        //ExecutorService executor = Executors.newFixedThreadPool(1);
        for (int i = 0; i <6 ; i++) {
            executor.execute(()->{
                try {
                    o.await();
                    System.out.println(Thread.currentThread().getName()+"    得到裁判哨声");
                    TimeUnit.SECONDS.sleep(new Random().nextInt(10));
                    System.out.println(Thread.currentThread().getName()+"    到达终点");
                    latch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        try {
            System.out.println("裁判准备中");
            TimeUnit.SECONDS.sleep(3);
            System.out.println("裁判吹哨了");
            o.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            latch.await();
            System.out.println("比赛结束");
            executor.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void test7() {
        ReentrantLock lock = new ReentrantLock();
        CountDownLatch latch = new CountDownLatch(2);
        ExecutorService service = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 10; i++) {
           service.submit(
                    new Thread(() ->{
                        try {
                            //lock.lock();
                            System.out.println(Thread.currentThread().getName() + "::获取了锁");
                            TimeUnit.SECONDS.sleep(2);
                            latch.countDown();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } finally {
                            System.out.println(Thread.currentThread().getName() + "::释放锁");
                            //lock.unlock();
                        }
                    },"线程"+i)
            );
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("latch执行完毕");
        service.shutdown();
    }


}


class MyThreadFactory implements ThreadFactory{


    private final AtomicInteger threadNumber = new AtomicInteger(1);
    private final String namePrefix;

    public MyThreadFactory(){
        SecurityManager s = System.getSecurityManager();

        namePrefix = "MyThreadPool:" + "-thread-";
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(null, r,
                namePrefix + threadNumber.getAndIncrement(),
                0);
        if (t.isDaemon())
            t.setDaemon(false);
        if (t.getPriority() != Thread.NORM_PRIORITY)
            t.setPriority(Thread.NORM_PRIORITY);
        return t;
    }
}
