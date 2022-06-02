package function;

import java.util.function.Predicate;

/**
 * @author luotao
 * @date 2022-3-24  15:44
 * 判断是否是5的倍数
 */
public class BPredicate implements Predicate<Integer> {
    @Override
    public boolean test(Integer test) {
        System.out.println("BPredicate");
        return test%5==0;
    }
}
