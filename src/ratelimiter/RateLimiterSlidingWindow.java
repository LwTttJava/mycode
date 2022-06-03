package ratelimiter;

import javafx.stage.Window;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author luotao
 * @date 2022-6-3  19:07
 * 参照 https://segmentfault.com/a/1190000041548601 所写
 */
public class RateLimiterSlidingWindow {
    // 阈值
    private int qps = 2;

    // 时间窗口大小 1秒
    private long windowSize = 1*1000;

    // 一个时间窗口有多少个子窗口
    private Integer windowCount = 10;

    private WindowInfo[] windowsArray = new WindowInfo[windowCount];

    private long subWindowsTime;

    public RateLimiterSlidingWindow(int qps){
        this.qps = qps;
        this.subWindowsTime = windowSize / windowCount;
        long curTime = System.currentTimeMillis();
        for (int i = 0; i < windowCount; i++) {
            windowsArray[i] = new WindowInfo(curTime,new AtomicInteger(0));
        }
    }

    public synchronized boolean tryAcquire(){
        long currentTimeMillis  = System.currentTimeMillis();
        // 1、计算当前时间 在时间窗口 中 子窗口的位置
        int curIndex = (int)(currentTimeMillis % windowSize / (windowSize / windowCount));
        System.out.println(String.format("子窗口位置:%d",curIndex));
        int sum = 0;
        for (int i = 0; i < windowCount; i++) {
            WindowInfo windowInfo = windowsArray[i];
            // 1、当前时间 属于下个时间窗口,重置过期窗口
            if(currentTimeMillis - windowInfo.time > windowSize){
                windowInfo.time = currentTimeMillis;
                windowInfo.number.set(0);
            }
            // 1、更新当前窗口计数
            if (curIndex == i && windowInfo.number.get() < qps){
                windowInfo.number.incrementAndGet();
            }
            sum = sum + windowInfo.number.get();
        }
        return sum <= qps;
    }

    private class WindowInfo {
        // 窗口开始时间
        public Long time;
        // 计数器
        public AtomicInteger number;

        public WindowInfo(long time, AtomicInteger number) {
            this.time = time;
            this.number = number;
        }
    }

}
