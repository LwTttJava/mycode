package nowcoder;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * @author luotao
 * @date 2022-10-30  22:28
 * 题目 https://leetcode.cn/problems/ZL6zAn/
 */
public class LeetCodeJZ105 {
    int[][] direction = new int[][]{{-1,0},{0,1},{1,0},{0,-1}};
    // 方法一 利用栈模拟递归
    public int maxAreaOfIsland(int[][] grid){
        int ans = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                int cur = 0;
                Deque<int[]> stack = new LinkedList<>();
                stack.push(new int[]{i,j});
                while (!stack.isEmpty()) {
                    int[] pop = stack.pop();
                    int cur_i = pop[0], cur_j = pop[1];
                    if (cur_i < 0 || cur_j < 0 || cur_i == grid.length || cur_j == grid[0].length || grid[cur_i][cur_j] != 1) {
                        continue;
                    }
                    ++cur;
                    grid[cur_i][cur_j] = 0;
                    for (int k = 0; k < direction.length; k++) {
                        int ni = cur_i + direction[k][0];
                        int nj = cur_j + direction[k][1];
                        stack.push(new int[]{ni,nj});
                    }
                }
                ans = Math.max(ans, cur);
            }
        }
        return ans;
    }

    // 方法二 dfs 递归
    public int maxAreaOfIsLand01(int[][] grid){
        int ans = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
               ans = Math.max(ans,dfs(grid,i,j));
            }
        }
        return ans;
    }
    public int dfs(int[][] grids,int i,int j){
        if(i<0 || j<0 || i==grids.length || j == grids[0].length || grids[i][j]!=1){
            return 0;
        }
        int ans = 1;
        grids[i][j] = 0;
        int[][] direction = new int[][]{{-1,0},{0,1},{1,0},{0,-1}};
        for (int k = 0; k < direction.length; k++) {
            int ni = i + direction[k][0];
            int nj = j + direction[k][1];
            ans += dfs(grids, ni, nj);
        }
        return ans;
    }

}
