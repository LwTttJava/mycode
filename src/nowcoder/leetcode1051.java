package nowcoder;

import java.util.Arrays;

/**
 * @author luotao
 * @date 2022-7-11  22:49
 */
public class leetcode1051 {

    /**
     *  找出最小值
     * @param heights
     * @return
     */
    public static int heightChecker(int[] heights) {
        int result = 0;
        for ( int i = 0 ; i < heights.length ; i++ ){
            int min = i;
            for ( int j = 0 ; j < heights.length ; j++ ){
                if( heights[j] < heights[min]){
                    min = j;
                }
            }
            if( heights[min] != heights[i] ){
                result++;
            }else{
                int temp = heights[i];
                heights[i] = heights[min];
                heights[min] = temp;
            }
        }
        return result;
    }

    public static int test(int[] heights){
        int[] copy = Arrays.copyOf(heights, heights.length);
        Arrays.sort(copy);
        int count = 0;
        for (int i = 0; i < heights.length; i++) {
            if(copy[i] != heights[i]){
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] arr1 = {5,1,2,3,4};
        System.out.println(test(arr1));
    }
}
