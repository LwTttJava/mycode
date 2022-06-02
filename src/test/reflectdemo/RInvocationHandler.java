package test.reflectdemo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class RInvocationHandler implements InvocationHandler {
    private Object target;
    public RInvocationHandler(Object target){
        this.target = target;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before invoke");
        Object invoke = method.invoke(target, args);
        System.out.println("after invoke");
        return invoke;
    }
}