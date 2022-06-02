package function;

import java.util.function.Predicate;

/**
 * @author luotao
 * @date 2022-3-24  15:42
 * 判断是否是偶数
 */
public class APredicate implements Predicate<Integer> {
/*    private Integer test;
    public APredicate(Integer test){
        this.test = test;
    }*/
    @Override
    public boolean test(Integer o) {
        System.out.println("APredicate");
        return o%2==0;
    }
}
