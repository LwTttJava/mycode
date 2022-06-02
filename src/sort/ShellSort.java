package sort;

import java.util.Arrays;

/**
 * @author luotao
 * @date 2022-4-15  15:44
 *  希尔排序
 */
public class ShellSort {
    public static void shellmy(int[] a) {
        int n = a.length;
        for(int gap = n/2 ; gap > 0; gap/=2){
            for (int i = gap; i<n; i++){
               int temp = a[i];
               int j = i;
               // 插入排序
               while(j>=gap){
                   if (temp < a[j - 1]) { // j-1 是上一个元素索引，如果 > t，后移
                       a[j] = a[j - 1];
                       j--;
                   } else { // 如果 j-1 已经 <= t, 则 j 就是插入位置
                       break;
                   }
               }
               a[j] = temp;
            }
        }
    }
    private static void shell(int[] a) {
        int n = a.length;
        for (int gap = n / 2; gap > 0; gap /= 2) {
            // i 代表待插入元素的索引
            for (int i = gap; i < n; i++) {
                int t = a[i]; // 代表待插入的元素值

                int j = i;
                while (j >= gap) {

                    // 每次与上一个间隙为 gap 的元素进行插入排序
                    if (t < a[j - gap]) { // j-gap 是上一个元素索引，如果 > t，后移
                        a[j] = a[j - gap];
                        j -= gap;
                    } else { // 如果 j-1 已经 <= t, 则 j 就是插入位置
                        break;
                    }
                }
                a[j] = t;
                System.out.println(Arrays.toString(a) + " gap:" + gap);
            }
        }
    }

    public static void main(String[] args) {
        int[] array = {9, 3, 7, 2, 5, 8, 1, 4};
        //int[] array = {1,3,92,3,4,7,2,5,7292,442,5042,24,54,9888,9999,10000};
        shell(array);
        System.out.println(Arrays.toString(array));
    }
}
