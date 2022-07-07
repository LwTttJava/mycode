package nowcoder;

/**
 * @author luotao
 * @date 2022-7-2  22:33
 * 岛屿的最大面积 https://leetcode.cn/problems/ZL6zAn/
 *
 */
public class Offer105 {

    public static int maxAreaOfIsland(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        int max = 0;
        for (int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++){
                max = Math.max(max,dfs(grid,i,j));
            }
        }
        return max;
    }
    public static int dfs(int[][] grid,int row,int col){
        if(row<0 || col<0 || row>= grid.length || col>=grid[0].length || grid[row][col]==0){
            return 0;
        }
        // 扩展一点，当岛屿面积不是为1时
        int curValue = grid[row][col];
        // 要么采用这种在原来的数组上进行修改，要么就选取一个新的二维数组作为visit的标记
        grid[row][col] = 0;
        return curValue + dfs(grid,row-1,col)+dfs(grid,row+1,col) + dfs(grid,row,col-1)+dfs(grid, row, col+1);
    }


    public static void main(String[] args) {
        int[][] grid =
                {
                        {0,0,1,0,0,0,0,1,0,0,0,0,0},
                        {0,0,0,0,0,0,0,1,1,1,0,0,0},
                        {0,1,1,0,1,0,0,0,0,0,0,0,0},
                        {0,1,0,0,1,1,0,0,1,0,1,0,0},
                        {0,1,0,0,1,1,0,0,1,1,1,0,0},
                        {0,0,0,0,0,0,0,0,0,0,1,0,0},
                        {0,0,0,0,0,0,0,1,1,1,0,0,0},
                        {0,0,0,0,0,0,0,1,5,0,0,0,0}
                };
        System.out.println(maxAreaOfIsland(grid));
    }
}
