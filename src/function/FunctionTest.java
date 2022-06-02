package function;

import java.util.function.BinaryOperator;
import java.util.function.Function;

/**
 * @author luotao
 * @date 2022-3-24  22:12
 */
public class FunctionTest {
    public static void main(String[] args) {
        BinaryOperator<Integer> plus = (t,u)->t+u;
        BinaryOperator<Integer> sub = (t,u)->t-u;
        BinaryOperator<Integer> mutiply = (t,u)->t*u;
        BinaryOperator<Integer> devide = (t,u)->t/u;
        // (5*6+6-10)/2
        System.out.println(devide.apply(sub.apply(plus.apply(mutiply.apply(5, 6), 6), 10), 2));


        Function<Integer,Integer> plus1 = (s)->s+6;
        Function<Integer,Integer> sub1 = (s)->s-2;
        Function<Integer,Integer> mutiply1 = (s)->s*5;
        Function<Integer,Integer> devide1 = (s)->s/2;
        // componse(before)  andThen(after)
        // 4/2*5-2+6
        System.out.println(plus1.compose(sub1.compose(mutiply1.compose(devide1))).apply(4));
        // (4+6-2)*5/2
        System.out.println(plus1.andThen(sub1.andThen(mutiply1.andThen(devide1))).apply(4));
    }
    public static Integer testFunction01(Function<Integer[],Integer> function,Integer[] params){
        return function.apply(params);
    }

}
