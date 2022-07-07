package nowcoder;

/**
 * @author luotao
 * @date 2022-7-3  13:10
 * 斐波那契数列
 */
public class Offer10 {
    // 未进行1000000007取余
    public static int fib(int n){
        if(n == 1 || n == 2){
            return 1;
        }
        int preOne = 1,preTwo = 1;
        for (int i = 3; i <=n ; i++) {
            int temp = preTwo;
            preTwo = preOne + temp;
            preOne = temp;
        }
        return preTwo;
    }

    public static void main(String[] args) {
        System.out.println(fib(80));
    }
}
