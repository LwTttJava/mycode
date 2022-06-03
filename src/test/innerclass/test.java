package test.innerclass;


import builderPattern.Packing;

/**
 * @author luotao
 * @date 2022-6-2  21:09
 */
public class test {
    public static void main(String[] args) {
        // 静态内部类
        Outter.Inner inner = new Outter.Inner();
        Outter.Inner inner1 = new Outter.Inner();
//        inner.noStaticMethod();
//        inner.staticMethod();
//        // 成员内部类
//        Outter.NoStaticInner noStaticInner = new Outter().new NoStaticInner();
//        // 局部内部类
        new Outter().getLocalInnerClass().myName();
        //  匿名内部类
        Outter anonymousInnerClass = new Outter(){
            @Override
            public void myName(){
                System.out.println(args);
                System.out.println("我是匿名内部类".intern());
            }
        };
        anonymousInnerClass.myName();
        /**
         *    public interface Packing {
         *        public String pack();
         *    }
         */
        // 匿名类 实现接口
        Packing packing = new Packing() {
            @Override
            public String pack() {
                return "匿名内部类实现接口";
            }
        };
        System.out.println(packing.pack());
    }
}
