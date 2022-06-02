package nowcoder;

import java.util.Arrays;

/**
 * @author luotao
 * @date 2022-3-18  1:26
 */
public class test {


    public static void moveZeroes(int[] nums) {
        int n = nums.length;
        int zi = 0;
        int j = 0;
        while(zi<n){
            if(nums[zi] == 0){
                int curNoZ = zi + 1;
                while(curNoZ<n){
                    if(nums[curNoZ]!=0){
                        int temp = nums[curNoZ];
                        nums[curNoZ] = nums[zi];
                        nums[zi] = temp;
                        break;
                    }
                    curNoZ++;
                }
            }
            zi++;
        }
    }

    public static void main(String[] args) {
        int[] nums = {0,1,0,3,12};
        moveZeroes(nums);
        System.out.println(Arrays.toString(nums));
    }
}
