package ratelimiter;

import java.time.LocalTime;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author luotao
 * @date 2022-6-3  21:22
 * 固定窗口限流算法（计数器）
 */
public class RateLimiterSimpleWindow {

    // 时间窗口大小 1秒
    private static long windowSize = 1*1000;

    private static long startTime = System.currentTimeMillis();

    // 阈值
    private static int qps = 2;

    // 计数器
    public static AtomicInteger number = new AtomicInteger(0);

    public synchronized static  boolean tryAcquire() {
        long currentTimeMillis = System.currentTimeMillis();
        if(currentTimeMillis - startTime > windowSize){
            number.set(0);
            startTime = currentTimeMillis;
        }
        return number.incrementAndGet() <= qps;
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            Thread.sleep(250);
            LocalTime now = LocalTime.now();
            if (!tryAcquire()) {
                System.out.println(now + " 被限流");
            } else {
                System.out.println(now + " 做点什么");
            }
        }
    }
}
