package sort;

/**
 *
 * 给定一个数组A[L...R]，求中间值可以通过 mid = (L+R)/2，A[mid]即为中间值。
 * 但这会出现一个问题，在极端情况下，当数组A很大时，L+R 可能会大于 int 所能表示的范围，造成结果错误。
 * 我们可以通过以下操作求中间值从而避免 L+R 上溢。
 * mid = L + ((R - L) / 2);
 * 进一步优化可以写成
 * mid = L +((R - L) >> 1);
 *
 * @author luotao
 * @date 2022-4-15  14:16
 *

 */
public class BinarySearch01 {


    /**
     * 二分查找
     * @param target
     * @param array
     * @return
     */
    public static int binarySearch(int target,int[] array){
        int l = 0, r = array.length - 1;
        while(l<=r){
            //int mid = ( l + r ) / 2; // 相加会出现 int 溢出问题
            int mid = l + (r-l)/2;  // 这样就不会溢出了
            if(array[mid]==target){
                return mid;
            }
            if(array[mid]>target){
                r = mid - 1;
            }else {
                l = mid + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {

        int[] array = {1,3,5,7,9,10,23,49,50,70};
        for (int find : array) {
            System.out.println(binarySearch(find, array)+1);
        }
        System.out.println(binarySearch(90, array));
    }
}
