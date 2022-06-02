package nowcoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author luotao
 * @date 2022-5-10  20:22
 */
public class HJ3 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        int[] arrays = new int[num];
        int i = 0;
        while(sc.hasNext()){
            arrays[i++] = sc.nextInt();
        }
        Arrays.sort(arrays);
        ArrayList list = new ArrayList(num);
        list.add(arrays[0]);

    }
}
