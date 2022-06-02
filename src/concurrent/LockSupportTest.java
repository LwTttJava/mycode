package concurrent;

import java.beans.Expression;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @author luotao
 * @date 2022-3-22  14:56
 */
public class LockSupportTest {
    private static volatile Object lock = new Object();

    public static void main(String[] args) throws Exception {
        //testLockSupport();
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("0001");
        Expression e = new Expression(arrayList, ArrayList.class, "add", new String[]{"0002"});
        e.getValue();
        e.setValue("0003");
        e.execute();
        System.out.println(e.toString());
        //System.out.println(arrayList);
}

    private static void testLockSupport() {
        Thread A = new Thread(() -> {

            LockSupport.parkNanos(lock, TimeUnit.SECONDS.toNanos(30));

            System.out.println(Thread.currentThread().getName() + ":未堵塞了");
        }, "线程A");
        A.start();

        new Thread(()->{
            try {
                System.out.println("线程B将休眠3秒");
                TimeUnit.SECONDS.sleep(3);
                System.out.println("线程B");
                System.out.println("线程B:"+LockSupport.getBlocker(Thread.currentThread()));
                System.out.println("线程A:"+LockSupport.getBlocker(A));
                //LockSupport.unpark(A);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

}
