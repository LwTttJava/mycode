package function;

import java.util.Random;
import java.util.function.Supplier;

/**
 * @author luotao
 * @date 2022-3-24  16:05
 */
public class SupplierTest {
    static Supplier ranInt = () -> new Random().nextInt(10);
    public static void main(String[] args) {
        System.out.println(ranInt.get());
        System.out.println(ranInt.get());
        System.out.println(ranInt.get());
        System.out.println(ranInt.get());
    }
}
