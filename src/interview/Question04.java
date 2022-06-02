package interview;

/**
 * @author luotao
 * @date 2022-5-26  19:07
 */
public class Question04 {

    /**
     * 四面，算法题
     * 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，
     * 写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1。
     *
     *
     * 示例 1:
     *
     * 输入: nums = [-1,0,3,5,9,12], target = 9
     * 输出: 4
     * 解释: 9 出现在 nums 中并且下标为 4
     * 示例 2:
     *
     * 输入: nums = [-1,0,3,5,9,12], target = 2
     * 输出: -1
     * 解释: 2 不存在 nums 中因此返回 -1
     *
     *
     * 说明：
     *
     * 1、假设 nums 中的所有元素是不重复的。
     * 2、n 将在 [1, 10^9]之间，需要考虑性能。
     * 3、nums 的每个元素都将在 [-9999, 9999]之间。
     */
    public static int judegeTargetExist(int[] nums,int target){
        int left = 0;
        int right = nums.length-1;
        while(left<=right){
            int mid = left + (right-left)/2;
            if(nums[mid]==target){
                return mid;
            }
            if(nums[mid]<target){
                left = mid + 1;
            }else{
                right = mid -1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {-1,0,3,5,9,12};
        System.out.println(judegeTargetExist(nums, -1));
        System.out.println(judegeTargetExist(nums, 12));
        System.out.println(judegeTargetExist(nums, 5));
        System.out.println(judegeTargetExist(nums, 9));
        System.out.println(judegeTargetExist(nums, 13));
        System.out.println(judegeTargetExist(nums, -9));
    }
}
