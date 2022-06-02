package sort;

/**
 * @author luotao
 * @date 2022-4-15  15:20
 */
public class SwapUtil {

    public static void swap(int i, int j, int[] array) {
        int a = array[i];
        array[i] = array[j];
        array[j] = a;
    }

}
