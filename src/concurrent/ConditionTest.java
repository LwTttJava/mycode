package concurrent;


import java.util.LinkedList;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author luotao
 * @date 2022-3-22  15:31
 */
public class ConditionTest {
    // 是否发起生产
    public static void main(String[] args) {

        testCondition();
        //testWaitNotifyAll();
        //testABQ();
    }

    private static void testABQ() {
        ArrayBlockingQueue<String> poolQueue = new ArrayBlockingQueue(10);
        ExecutorService productThreadPool = Executors.newFixedThreadPool(15);
        for (int i = 0; i < 10; i++) {
            productThreadPool.execute(()->{
                while(true){
                    synchronized (ConditionTest.class){
                        String p = UUID.randomUUID().toString().substring(0, 4);
                        boolean offer = poolQueue.offer(p);
                        if(offer){
                            System.out.println(Thread.currentThread().getName()+"生产者：添加"+p+"  当前池商品数量:"+poolQueue.size());
                        }else{
                            System.out.println(Thread.currentThread().getName()+"池已满，暂停生产");
                            try {
                                TimeUnit.SECONDS.sleep(2);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                }
            });
        }

        for (int i = 0; i < 5; i++) {
            productThreadPool.execute(()->{
                while (true){
                    synchronized (ConditionTest.class){
                        String poll = poolQueue.poll();
                        if(null==poll){
                            System.out.println(Thread.currentThread().getName()+"池已空，暂停消费");
                            try {
                                TimeUnit.SECONDS.sleep(2);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }else{
                            System.out.println(Thread.currentThread().getName()+"消费者:消费"+poll+" 当前池中商品数量:"+poolQueue.size());
                        }
                    }
                }
            });
        }
        productThreadPool.shutdown();
    }

    private static void testWaitNotifyAll() {
        LinkedList<Object> pool = new LinkedList<Object>();
        Object o = new Object();
        ExecutorService productThreadPool = Executors.newFixedThreadPool(15);
        for (int i = 0; i < 10; i++) {
             productThreadPool.execute(()->{
                 synchronized (o){
                     while(true){
                         if (pool.size()!=10) {
                             String p = UUID.randomUUID().toString().substring(0, 4);
                             pool.add(p);
                             System.out.println(Thread.currentThread().getName()+"生产者：添加"+p+"  当前池商品数量:"+pool.size());
                             o.notifyAll();
                         }else{
                             System.out.println(Thread.currentThread().getName()+"池已满，暂停生产");
                             try {
                                 o.wait();
                             } catch (InterruptedException e) {
                                 e.printStackTrace();
                             }
                         }
                     }
                 }
             });
        }

        for (int i = 0; i < 5; i++) {
            productThreadPool.execute(()->{
                while (true){
                    synchronized (o){
                        if(pool.size()==0){
                            try {
                                System.out.println(Thread.currentThread().getName()+"池已空，暂停消费");
                                o.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }else{
                            System.out.println(Thread.currentThread().getName()+"消费者:消费"+pool.remove()+" 当前池中商品数量:"+pool.size());
                            o.notifyAll();
                        }
                    }
                }
            });
        }
        productThreadPool.shutdown();
    }


    private static void testCondition() {
        ReentrantLock lock = new ReentrantLock();
        Condition product = lock.newCondition();
        Condition consumer = lock.newCondition();
        ExecutorService productThreadPool = Executors.newFixedThreadPool(15);

        LinkedList<Object> pool = new LinkedList<Object>();
        for (int i = 0; i < 10; i++) {
            productThreadPool.execute(()->{
                while(true){
                    System.out.println("等待获取此锁的线程数的估计值："+lock.getQueueLength());
                    lock.lock();
                    if (pool.size()!=10) {
                        String p = UUID.randomUUID().toString().substring(0, 4);
                        pool.add(p);
                        System.out.println(Thread.currentThread().getName()+"生产者：添加"+p+"  当前池商品数量:"+pool.size());
                        consumer.signalAll();
/*                        try {
                            Thread.currentThread().wait(TimeUnit.SECONDS.toMillis(new Random().nextInt(3)+1));
                            //TimeUnit.SECONDS.sleep(new Random().nextInt(3)+1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }*/
                    }else{
                        System.out.println(Thread.currentThread().getName()+"池已满，暂停生产");
                        try {
                            product.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    lock.unlock();
                }
            });
        }
        for (int i = 0; i < 5; i++) {
            productThreadPool.execute(()->{
                while (true){
                    System.out.println("等待获取此锁的线程数的估计值："+lock.getQueueLength());
                    lock.lock();
                    if(pool.size()==0){
                        try {
                            System.out.println(Thread.currentThread().getName()+"池已空，暂停消费");
                            consumer.await();
                        } catch (InterruptedException e) {

                        }
                    }else{
                        System.out.println(Thread.currentThread().getName()+"消费者:消费"+pool.remove()+" 当前池中商品数量:"+pool.size());
                        product.signalAll();
                    }
/*                    try {
                        Thread.currentThread().wait(TimeUnit.SECONDS.toMillis(new Random().nextInt(3)+1));
                        //TimeUnit.SECONDS.sleep(new Random().nextInt(3)+1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }*/
                    lock.unlock();
                }
            });
        }
        productThreadPool.shutdown();
    }
}
