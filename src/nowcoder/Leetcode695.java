package nowcoder;

/**
 * @author luotao
 * @date 2022-4-29  22:16
 */
public class Leetcode695 {

    public static int maxAreaOfIsland(int[][] grid) {
        int maxIsland = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] != 0) {
                    int num = dfs(grid, i, j);
                    maxIsland = Math.max(maxIsland, num);
                }
            }
        }
        return maxIsland;
    }

    public static boolean checkDuplicateWord(String s){
        int l = s.length();
        for(int i=0;i+2<l;i++){
            String temp = s.substring(i,i+2);
            Character c = 'a';
            //String temp = s.charAt(i)+s.charAt(i+1);
            boolean d = s.substring(i+2).contains(temp);
            if(d){
                return false;
            }
        }
        return true;
    }

    public static int dfs(int[][] grid, int sr, int sc) {
        int num = 0;
        if (grid[sr][sc] != 0) {
            grid[sr][sc] = 0;
            num++;
            // 上
            if (sr - 1 >= 0) {
                num += dfs(grid, sr - 1, sc);
            }
            // 下
            if (sr + 1 < grid.length) {
                num += dfs(grid, sr + 1, sc);
            }
            // 左
            if (sc - 1 >= 0) {
                num += dfs(grid, sr, sc - 1);
            }
            // 右
            if (sc + 1 < grid[0].length) {
                num += dfs(grid, sr, sc + 1);
            }
        }
        return num;
    }

    public static void main(String[] args) {
        int[][] grid = new int[][]{
                {0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}
        };
        System.out.println(maxAreaOfIsland(grid));
    }
}
