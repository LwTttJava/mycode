package concurrent.kill;



import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author luotao
 * @date 2022-5-28  21:17
 * 参照极海代码 通知改成 Condition 进行唤醒
 * 合并请求，然后统一去更改数据库
 */
public class MyKillDemo {

    /**
     * 模拟数据库行
     */
    private final AtomicInteger stock = new AtomicInteger(70);

    private final static int REQUEST_COUNT = 2000;

    public BlockingQueue<RequestPromise> queue = new LinkedBlockingQueue<>(1000);

    private final ExecutorService executorService = Executors.newCachedThreadPool();

    public static void main(String[] args) throws InterruptedException {
        MyKillDemo killDemo = new MyKillDemo();

        new Thread(killDemo::megreRequestToPlaceOrder).start();

        Thread.sleep(2000);

        List<Future<RequestPromise>> futureList = new ArrayList<>();

        for (int i = 0; i < REQUEST_COUNT; i++) {
            final Long orderId = i + 100L;
            final Long userId = Long.valueOf(i);
            Future<RequestPromise> future = killDemo.executorService.submit(() -> {
                RequestPromise operate = killDemo.orderSecondKill(new UserRequest(orderId, userId, 1));
                return operate;
            });
            futureList.add(future);
        }
        AtomicInteger success  = new AtomicInteger();
        futureList.forEach(future -> {
            try {
                RequestPromise result = future.get(300, TimeUnit.MILLISECONDS);
                if(result.getResult().isSuccess()){
                    success.getAndIncrement();
                }
                System.out.println(Thread.currentThread().getName() + ":客户端请求响应:" + result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        System.out.println("最终统计成功数据"+success);
    }

    public RequestPromise orderSecondKill(UserRequest userRequest) throws InterruptedException {
        RequestPromise requestPromise = new RequestPromise(userRequest);
        // 加入到请求缓存池中，由合并任务去执行
        boolean enqueueSuccess = queue.offer(requestPromise, 100, TimeUnit.MILLISECONDS);
        if (!enqueueSuccess) {
            requestPromise.setResult(new Result(false, "系统繁忙"));
            return requestPromise;
        }
        requestPromise.await();
        return requestPromise;
    }

    public void megreRequestToPlaceOrder(){

        while (true) {
            if (queue.isEmpty()) {
                try {
                    Thread.sleep(10);
                    continue;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            List<RequestPromise> list = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                list.add(queue.poll());
            }

            System.out.println(Thread.currentThread().getName() + ":合并扣减库存:" + list);

            int sum = list.stream().mapToInt(e -> e.getUserRequest().getCount()).sum();
            // 两种情况
            if (sum <= stock.get()) {
                stock.set(stock.get()-sum);
                //stock -= sum;
                // notify user
                list.forEach(requestPromise -> {
                    requestPromise.setResult(new Result(true, "ok"));
                    requestPromise.signal();
                });
                continue;
            }
            for (RequestPromise requestPromise : list) {
                int count = requestPromise.getUserRequest().getCount();
                if (count <= stock.get()) {
                    stock.set(stock.get()-count);
                    requestPromise.setResult(new Result(true, "扣减ok，现在数量"+stock));
                } else {
                    requestPromise.setResult(new Result(false, "库存不足,现在数量"+stock));
                }
                requestPromise.signal();
            }
            list.clear();
        }
    }
}
