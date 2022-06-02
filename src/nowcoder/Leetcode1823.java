package nowcoder;

import java.util.LinkedList;
import java.util.List;

/**
 * @author luotao
 * @date 2022-5-25  23:26
 */
public class Leetcode1823 {

    static List<Integer> list = new LinkedList<>();

    public static int findTheWinner(int n, int k) {
        for(int i = 1;i<=n;i++){
            list.add(i);
        }
        d(n,k,0);
        return list.get(0);
    }

    public static void d(int n,int k,int cur){
        if(n==1){
            return;
        }
        int temp = cur+k-1;
        if(temp>=n){
            temp = temp % n;
        }
        Integer remove = list.remove(temp);
        System.out.println("淘汰之前人数为:"+n+" ,淘汰了"+remove+"，现在情况为:"+list);
        d(n-1,k,temp);
    }

    public static void main(String[] args) {
        findTheWinner(6,5);
    }
}
