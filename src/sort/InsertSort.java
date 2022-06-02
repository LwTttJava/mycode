package sort;

import java.util.Arrays;

/**
 * @author luotao
 * @date 2022-4-15  15:13
 */
public class InsertSort {
    /**
     *  插入排序 （斗地主，抓牌过程，摸一张牌，插入到手牌中）
     *  分为未排序区，和基本排序区（有序度较高）
     *  从未排序区中，选择第一个，在基本排序区中寻找位置（前一个小于它，后一个大于它）
     *
     *  和选择排序的区别 （斗地主，牌都发完了，一把全部抓起，一张一张的牌从大到小，插入左侧有序区尾部）
     *
     * @param a
     */
    public static void insert(int[] a) {
        for (int i = 1; i < a.length; i++) {
            int temp = i;
            while(temp>0){
                if(a[temp-1]>a[temp]){
                    SwapUtil.swap(temp-1,temp,a);
                }else{
                    break;
                }
                temp--;
            }
        }
    }

    public static void main(String[] args) {
        int[] array = {1,3,92,3,4,7,2,5,7292,442,5042,24,54,9888,9999,10000};
        insert(array);
        System.out.println(Arrays.toString(array));
    }
}
