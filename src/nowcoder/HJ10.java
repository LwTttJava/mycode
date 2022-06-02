package nowcoder;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @author luotao
 * @date 2022-5-10  20:18
 */
public class HJ10 {
    public static void main(String[] args){
        Set<String> hs = new HashSet<>();
        Scanner sc = new Scanner(System.in);
        String wrods = sc.nextLine();
        for(char c: wrods.toCharArray()){
            hs.add(c+"");
        }
        System.out.println(hs.size());
    }
}
