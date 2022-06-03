package ratelimiter;

import java.time.LocalTime;

/**
 * @author luotao
 * @date 2022-6-3  19:25
 */
public class RateLimiterSlidingWindowTest {
    public static void main(String[] args) throws InterruptedException {
        int qps = 2, count = 60, sleep = 200, success = count * sleep / 1000 * qps;
        System.out.println(String.format("当前QPS限制为:%d,当前测试次数:%d,间隔:%dms,预计成功次数:%d", qps, count, sleep, success));
        success = 0;
        RateLimiterSlidingWindow myRateLimiter = new RateLimiterSlidingWindow(qps);
        for (int i = 0; i < count; i++) {
            Thread.sleep(sleep);
            if (myRateLimiter.tryAcquire()) {
                success++;
                if (success % qps == 0) {
                    System.out.println(LocalTime.now() + ": success, ");
                } else {
                    System.out.print(LocalTime.now() + ": success, ");
                }
            } else {
                System.out.println(LocalTime.now() + ": fail");
            }
        }
        System.out.println();
        System.out.println("实际测试成功次数:" + success);
    }
}
