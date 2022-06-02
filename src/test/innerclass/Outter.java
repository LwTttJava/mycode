package test.innerclass;

public class Outter{
    int a = 6;
    static int b = 9;
    static {
        System.out.println("静态代码块");
    }
    {
        System.out.println("代码块");
    }

    public  void outNoStatic(){
        Inner.staticMethod();
        System.out.println(Inner.d);

        NoStaticInner noStaticInner = new NoStaticInner();
        System.out.println(noStaticInner.c);
        noStaticInner.method1();
    }
    public static void outStatic(){ }

    public  void myName(){
        System.out.println("我外部类");
    }
    // 局部内部类
    public  Outter getLocalInnerClass(){
        class OutterWowen extends Outter{
            int a =12;
            public OutterWowen(){

            }
            @Override
            public void myName(){
                System.out.println("局部内部类a="+a+"\t super.a="+super.a+"\t this.a="+this.a);
                //System.out.println("局部内部类"+"a:"+a+"b:"+b+"c:"+c);
            }
        }
        return new OutterWowen();
    }
    // 静态内部类
    static class Inner{
        int c = 12;
        static int d = 15;
        static void staticMethod(){
            //System.out.println(a); // 不允许
            System.out.println(b);
            //System.out.println(c); // 不允许
            System.out.println(d);
        }
        void noStaticMethod(){
            //System.out.println(a); // 不允许
            System.out.println(b);
            System.out.println(c);
            System.out.println(d);
        }
    }
    // 成员内部类
    class NoStaticInner {
        int c = 12;
        //static int d = 15;  //不允许成员内部类声明静态属性和方法
        void method1(){
            System.out.println(a);
            System.out.println(b);
        }
        public NoStaticInner(){}
        //static void method2(){} //不允许
    }
}