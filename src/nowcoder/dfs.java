package nowcoder;

import java.util.Stack;

/**
 * @author luotao
 * @date 2022-2-21  14:54
 * 迷宫判断是否存在出口
 */
public class dfs {
    int[] B = {4,4};
    // 上 右 下 左
    int[] dx = {-1,0,1,0};
    int[] dy = {0,1,0,-1};

    boolean isSafe(int[][] maze, int i, int j) {
        if(i<0 || j<0 || i>=maze.length || j>=maze[i].length) {
            return false;
        }
        return maze[i][j] != 1;
    }
    void tagAccessed(int maze[][], int x, int y){
        maze[x][y] = 1;
    }


    boolean dfs1(int maze[][], int x, int y){
        Stack<Integer[]> stack = new Stack<>();
        stack.push(new Integer[] {x, y});
        //maze[x][y] = 1;
        tagAccessed(maze,x,y);

        while (!stack.isEmpty()){
            Integer[] cur = stack.pop();

            if(cur[0] ==B[0] && cur[1]==B[1]){
                return true;
            }

            for (int d=0;d<4;d++){
                int x1 = dx[d]+cur[0];
                int y1 = dy[d]+cur[1];
                if(isSafe(maze,x1,y1)){
                    stack.push(new Integer[]{x1,y1});
                    //maze[x1][y1] = 1;
                    tagAccessed(maze,x1,y1);
                }
            }
        }
        return false;
    }

    boolean dfs(int maze[][], int x, int y) {
        // 创建一个Stack
        Stack<Integer[]> stack = new Stack<>();

        // 将起始点压入栈，标记它访问过
        stack.push(new Integer[] {x, y});
        System.out.println("起始点入栈:（"+x+","+y+");");
        maze[x][y] = 1;

        while (!stack.isEmpty()) {
            // 取出当前点
            Integer[] pos = stack.pop();
            x = pos[0]; y = pos[1];
            System.out.println("遍历点出栈:（"+x+","+y+");");
            // 判断是否找到了目的地
            if (x == B[0] && y == B[1]) {
                System.out.println("找到目标点:（"+x+","+y+");");
                return true;
            }

            // 在四个方向上尝试
            for (int d = 0; d < 4; d++) {
                int i = x + dx[d], j = y + dy[d];

                if (isSafe(maze, i, j)) {
                    System.out.println("入栈:（"+i+","+j+");");
                    stack.push(new Integer[] {i, j});
                    maze[i][j] = 1;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        dfs d = new dfs();
        int maze[][] = {
                {1,0,0,0,1},
                {1,0,0,1,1},
                {0,0,0,0,1},
                {0,1,1,0,1},
                {0,1,0,0,0}
        };


        System.out.println(d.dfs1(maze, 0, 1));
    }

}
