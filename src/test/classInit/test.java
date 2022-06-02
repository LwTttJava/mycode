package test.classInit;

/**
 * @author luotao
 * @date 2022-6-2  21:06
 */
public class test {
    public static void main(String[] args) {
        System.out.println(ClassInitTest.cityCode);
        System.out.println("new 之前");
        ClassInitTest test1 = new ClassInitTest();
        ClassInitTest test2 = new ClassInitTest();
    }
}
