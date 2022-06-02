package interview;

/**
 * @author luotao
 * @date 2022-5-19  20:30
 */
public class Question03 {

    /**
     * [
     *   [1,  4,  7, 11, 15],
     *   [2,  5,  8, 12, 19],
     *   [3,  6,  9, 16, 22],
     *   [10, 13, 14, 17, 24],
     *   [18, 21, 23, 26, 30]
     * ]
     */

    /**
     *  	编写一个算法来搜索 m x n 矩阵 matrix 中的一个目标值 target。该矩阵具有以下特性：
     * 	每行的元素从左到右升序排列。
     * 	每列的元素从上到下升序排列。
     *
     * @param matrix
     * @param target
     * @return
     */
    public static boolean searchMatrix(int[][] matrix, int target) {
        if(matrix==null || matrix.length<=0){
            return false;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int row = 0,col = n-1;
        // 同行最后一列，寻找小于当前值的位置（当前值小于目标值，右边值大于当前值），然后向下
        while(row<m && col>=0){
            if(matrix[row][col]==target){
                return true;
            }
            if(matrix[row][col]>target){
                col--;
            }else{
                row++;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1,  4,  7, 11, 15},
                {2,  5,  8, 12, 19},
                {3,  6,  9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        };
        //int[][] matrix = null;
        //int[][] matrix = {{}};
        System.out.println("下面是不存在测试");
        System.out.println(searchMatrix(matrix, 31));
        System.out.println(searchMatrix(matrix, 27));
        System.out.println(searchMatrix(matrix, 29));
        System.out.println(searchMatrix(matrix, 40));
        System.out.println(searchMatrix(matrix, 20));
        System.out.println(searchMatrix(matrix, 0));
        System.out.println("下面是存在的");
        System.out.println(searchMatrix(matrix, 5));
        System.out.println(searchMatrix(matrix, 16));
        System.out.println(searchMatrix(matrix, 3));

    }

}
