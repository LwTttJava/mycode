package sort;

import java.util.Arrays;

/**
 * @author luotao
 * @date 2022-4-15  15:03
 */
public class SelectionSort {
    /**
     * 选择排序
     * 从未排序区域中选择一个最小值，插入到已排序区域末尾
     * @param a
     */
    public static void selection(int[] a) {
        for (int i = 0; i < a.length; i++) {
            int min = i;
            for (int j = i; j < a.length; j++) {
                if(a[j]<a[min]){
                    min = j;
                }
            }
            int temp = a[min];
            a[min] = a [i];
            a[i] = temp;
        }
    }

    public static void main(String[] args) {
        int[] array = {1,3,92,3,4,7,2,5,7292,442,5042,24,54,9888,9999,10000};
        selection(array);
        System.out.println(Arrays.toString(array));
    }
}
