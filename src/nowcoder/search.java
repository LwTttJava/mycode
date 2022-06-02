package nowcoder;

/**
 * @author luotao
 * @date 2020/11/5  16:39
 *
 * 在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
 * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 */
public class search {
    /**
     * {{1,2,8,9},
     *  {2,4,9,12},
     *  {4,7,10,13},
     *  {6,8,11,15}}
     *
     *  解法，从右上角看，某个位置的数，小于它的数一定在其左边，大于它的数一定在其下方
     * @param target
     * @param array
     * @return
     */
    public static boolean Find(int target, int[][] array) {
      int r=0,c=array[0].length-1;
      while(r<array.length-1 && c>=0){
          if(array[r][c]==target) return true;
          if(array[r][c]<target)
              r++;
          else
              c--;
      }
      return false;
    }
    public static int compare(int i,int j){
        if(i==j)
            return 0;
        return i<j?-1:1;
    }

    public void test(){
        int[][] array = {{1,2,8,9}, {2,4,9,12}, {4,7,10,13}, {6,8,11,15}};
        for(int i=1;i<=15;i++){
            System.out.println(i+"----"+Find(i,array));
        }
    }

/*    public static void main(String[] args) {
        int[][] array = {{1,2,8,9}, {2,4,9,12}, {4,7,10,13}, {6,8,11,15}};
        for(int i=1;i<=15;i++){
            System.out.println(i+"----"+Find(i,array));
        }
    }*/

}
