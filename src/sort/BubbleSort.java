package sort;

import java.util.Arrays;

/**
 * @author luotao
 * @date 2022-4-15  14:32
 */
public class BubbleSort {
    /**
     * 冒泡排序
     * 外循环（趟数） 和 内循环（每趟相邻两个数进行比较）
     * @param array
     */
    public static void bubbleSort(int[] array){
        for (int i = 0; i < array.length; i++) {
            boolean isSwap = false;
            for (int j = 0; j < array.length - i-1; j++) {
                if(array[j]>array[j+1]){
                    //swap(i,j,array);
                    int a = array[j];
                    array[j] = array[j+1];
                    array[j+1] = a;
                    isSwap = true;
                }
            }
            if(!isSwap){
                System.out.println("检测未交换，提前over");
                break;
            }
    }
    }

    public static void main(String[] args) {
        int[] array = {1,3,92,3,4,7,2,5,7292,442,5042,24,54,9888,9999,10000};
        bubbleSort(array);
        System.out.println(Arrays.toString(array));
    }


}
