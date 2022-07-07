package nowcoder;

/**
 * @author luotao
 * @date 2022-7-3  13:31
 */
public class Offer10_II {
    public static int howWay(int n){
        if(n<=2){
            return n;
        }
        int pre_one = 1,pre_two = 2,r = 0;
        for (int i = 3; i <= n; i++) {
            r= pre_one + pre_two;
            pre_one = pre_two;
            pre_two = r;
        }
        return r;
    }

    public static void main(String[] args) {
        System.out.println(howWay(7));
    }
}
