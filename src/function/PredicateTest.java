package function;

import sun.reflect.CallerSensitive;
import sun.reflect.Reflection;

import java.util.function.Predicate;

/**
 * @author luotao
 * @date 2022-3-24  15:45
 */
public class PredicateTest {
    static Predicate<Integer> BigSeven = x -> x>7;

    public static void main(String[] args) {
        Integer test = 10;
        APredicate aPredicate = new APredicate();
        BPredicate bPredicate = new BPredicate();
        boolean test1 = aPredicate.negate()
                .and(bPredicate.negate())
                .and(BigSeven)
                .test(15);
        System.out.println(test1);
    }

    @CallerSensitive
    public static void test(String name){
        System.out.println(name+"调用方:"+ Reflection.getCallerClass());
    }
}
