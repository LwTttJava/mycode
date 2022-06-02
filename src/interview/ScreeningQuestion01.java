package interview;

import java.util.Scanner;

/**
 * @author luotao
 * @date 2022-5-11  15:50
 */
public class ScreeningQuestion01 {
    /**
     * 笔试算法一，
     * 每个工人可以从A,B,C中进行抉择
     * 相邻工人不能做出相同选择
     * 求最大值
     * @param args
     */
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        int[][] arr = new int[n][3];
        int j = 0;
        for(j = 0;j<n;j++){
            String temp = sc.nextLine();

            String[] tempArr = temp.split(" ");
            for(int i=0;i<tempArr.length;i++){
                int t =  Integer.parseInt(tempArr[i]);
                arr[j][i] = t;
            }
        }
        // 上一个所选策略
        int LastStrategy = -1;
        int sum = 0;
        for(int i = 0;i<n;i++){
            int min = Integer.MAX_VALUE;
            // 当前循环所选策略
            int curStrategy = -1;
            for(j=0;j<3;j++){
                if(arr[i][j]<min && LastStrategy!=j){
                    min = arr[i][j];
                    curStrategy = j;
                }
            }
            LastStrategy = curStrategy;
            sum+=min;
        }
        System.out.println(sum);
    }
}
