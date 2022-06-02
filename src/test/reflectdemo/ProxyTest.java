package test.reflectdemo;

import java.lang.reflect.Proxy;

/**
 * @author luotao
 * @date 2022-6-2  22:21
 */
public class ProxyTest {
    /**
     * 使用jdk 动态代理
     *  InvocationHandler 增加类
     *
     */
    public static void testProxy(){
        Search search1 = new LowToUpSearch();
        RInvocationHandler invocation = new RInvocationHandler(search1);
        /* *
         *  ClassLoader loader, 类加载器
         *  Class<?>[] interfaces, 被代理类的接口
         *  InvocationHandler h 增强器
         * */
        Search search = (Search) Proxy.newProxyInstance(RInvocationHandler.class.getClassLoader(),
                LowToUpSearch.class.getInterfaces(),
                invocation);

        search.search();
    }

    public static void main(String[] args) {
        testProxy();
    }
}
