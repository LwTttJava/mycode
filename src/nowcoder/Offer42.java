package nowcoder;

/**
 * @author luotao
 * @date 2022-7-3  19:59
 */
public class Offer42 {
    public static void main(String[] args) {
        int[] nums = {-2,5,-3,4,-1,2,1,-5,4,10,-9,11};
        System.out.println(maxValue2(nums));
    }
    public static int maxValue(int[] arr){
        int[] dp = new int[arr.length];
        dp[0] = arr[0];
        int res = 0;
        for(int i = 1;i<arr.length;i++){
            dp[i] = Math.max(dp[i-1]+arr[i],arr[i]);
            res = Math.max(res,dp[i]);
        }
        return res;
    }

    public static int maxValue2(int[] arr){
        int one = arr[0],two = 0;
        int res = 0;
        for(int i = 1;i<arr.length;i++){
            // Math.max(one+arr[i],arr[i]) 这段含义就是 当前一个子数组之和小于当前值，就舍弃之前的子数组之和
            two = Math.max(one+arr[i],arr[i]);
            res = Math.max(res,two);
            one = two;
        }
        return res;
    }
}
