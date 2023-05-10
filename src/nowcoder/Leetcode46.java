package nowcoder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luotao
 * @date 2022-11-14  23:25
 * 46. 全排列
 */
public class Leetcode46 {
    static List<List<Integer>> result = new ArrayList<>();
    public static List<List<Integer>> permute(int[] nums) {
        backtrack(0,nums,new ArrayList<>());
        return result;
    }

    /**
     * [[1, 2, 3], [1, 2], [1, 3], [1], [2, 3], [2], [3], []]
     * @param index
     * @param nums
     * @param temp
     */
    static void backtrack(int index,int[] nums,List<Integer> temp){
        if(index == nums.length){
            result.add(new ArrayList(temp));
            return;
        }
        temp.add(nums[index]);
        backtrack(index+1,nums,temp);
        temp.remove(temp.size()-1);
        backtrack(index+1,nums,temp);
    }
    
    static void backTrack(int index,int[] nums,List<Integer> temp){
        if(index == nums.length){
            result.add(new ArrayList(temp));
            return;
        }
        for (int i = index; i < nums.length; i++) {
            temp.add(nums[index]);
            backtrack(index+1,nums,temp);
            temp.remove(temp.size()-1);
            backtrack(index+1,nums,temp);
        }
    }


    public static void main(String[] args) {
        int[] nums = {1,2,3};
        System.out.println(permute(nums));
    }
}
