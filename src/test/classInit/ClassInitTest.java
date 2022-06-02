package test.classInit;

/**
 * @author luotao
 * @date 2022-3-5  18:42
 */
public class ClassInitTest {
    private static final int areaCode = 16;
    private final int b;
    public static int cityCode = 10;
    public ClassInitTest(){
        b = 30;
    }
    static {
        System.out.println("静态代码块");
    }
    {
        System.out.println("代码块");
    }

    private final void testFinal(){
        System.out.println("父类final方法");
    }

    public static void main(String[] args) {
        new son();
    }
}

class son extends ClassInitTest{
    public int  testFinal(int a){
        return a;
    }
    private final void testFinal(){
        System.out.println("子类final方法");
    }
}
