package nowcoder;

/**
 * @author luotao
 * @date 2022-7-3  20:27
 */
public class Offer33 {
    public static boolean checkTree(int[] nums){
       return recur(nums,0,nums.length-1);
    }
    /**
     *  思路，最后一个就是父节点
     *  在（左+右）中找到父节点的左子树（所有节点小于父节点） ，右子树（所有节点大于父节点）
     * @param nums
     * @param left （左+右）的开始位置
     * @param right 父节点
     * @return
     */
    public static boolean recur(int[] nums,int left,int right){
        if(left >= right) return true;
        int p = left;
        while(nums[p]<nums[right]) p++;
        //这里就是左子树的结束位置+1 ,换句话说就是右子树开始的地方
        int rightBegin = p;
        while(nums[p]>nums[right]) p++;
        /**
         * 需要 p==right,如果没有遍历完，说明不是二叉搜索树
         * 然后父节点的 左子树   和 父节点的 右子树 进行同样操作
         */
        return p==right && recur(nums,left,rightBegin-1) && recur(nums,rightBegin,right-1);
    }

    public static void main(String[] args) {
        int[] nums = {1,3,2,6,5};
        int[] nums1 = {1,6,3,2,5};
        System.out.println(checkTree(nums));
        System.out.println(checkTree(nums1));
    }
}
