package concurrent.kill;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class RequestPromise {

    Lock lock = new ReentrantLock();

    Condition condition = lock.newCondition();

    public void await(){
        lock.lock();
        try {
            condition.await(200, TimeUnit.MILLISECONDS);
            if (result == null) {
                result = new Result(false, "等待超时");
            }
        }catch (InterruptedException e) {
            result = new Result(false, "被中断");
        }finally {
            lock.unlock();
        }
    }

    public void signal(){
        lock.lock();
        try {
            condition.signal();
        }finally {
            lock.unlock();
        }
    }

    private UserRequest userRequest;

    private Result result;

    public RequestPromise(UserRequest userRequest) {
        this.userRequest = userRequest;
    }

    public RequestPromise(UserRequest userRequest, Result result) {
        this.userRequest = userRequest;
        this.result = result;
    }

    public UserRequest getUserRequest() {
        return userRequest;
    }

    public void setUserRequest(UserRequest userRequest) {
        this.userRequest = userRequest;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "RequestPromise{" +
                "userRequest=" + userRequest +
                ", result=" + result +
                '}';
    }
}