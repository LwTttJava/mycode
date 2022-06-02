package nowcoder;

/**
 * @author luotao
 * @date 2022-6-2  22:43
 */
public class FloodFillTest {
    public static int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if(image[sr][sc]!=newColor){
            dfs(image,sr,sc,image[sr][sc],newColor);
        }
        return  image;
    }
    /**
     * 深度优先算法
     * @param image
     * @param sr
     * @param sc
     * @param targetColor
     * @param newColor
     */
    public static void dfs(int[][] image,int sr,int sc,int targetColor,int newColor){
        if(image[sr][sc]==targetColor ){
            image[sr][sc] = newColor;
            // 上
            if(sr-1>=0){
                dfs(image,sr-1,sc,targetColor,newColor);
            }
            // 下
            if(sr+1<image.length){
                dfs(image,sr+1,sc,targetColor,newColor);
            }
            // 左
            if(sc-1>=0){
                dfs(image,sr,sc-1,targetColor,newColor);
            }
            // 右
            if(sc+1<image[0].length){
                dfs(image,sr,sc+1,targetColor,newColor);
            }
        }
    }

    public static void main(String[] args) {
        int[][] image = {{0,0,0},{0,1,1}};
        int sr = 1, sc = 1, newColor = 1;
        int[][] ints = floodFill(image, sr, sc, newColor);
    }
}
