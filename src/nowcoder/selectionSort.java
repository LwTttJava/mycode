package nowcoder;

/**
 * @author luotao
 * @date 2020/11/6  9:30
 * 选择排序
 * 从数组中选择最小元素，将它与数组的第一个元素交换位置。再从数组剩下的元素中选择出最小的元素，将它与数组的第二个元素交换位置。
 * 不断进行这样的操作，直到将整个数组排序。
 */
public class selectionSort {

    /**
     * 从数组中选择最小元素，将它与数组的第一个元素交换位置。再从数组剩下的元素中选择出最小的元素，将它与数组的第二个元素交换位置。
     * 不断进行这样的操作，直到将整个数组排序。
     * @param array
     * @return
     */
    public static int[] selectionSort(int[] array){
        for(int i=0;i<array.length;i++){
            int min = i;
            for(int j=i+1;j<array.length;j++){
                if(array[min]>array[j])
                    min=j;
            }
            int temp = array[i];
            array[i]=array[min];
            array[min]=temp;
        }
        return array;
    }

    public static void swap(int[] array,int i,int min){
        int temp = array[i];
        array[i]=array[min];
        array[min]=temp;
    }

    /**
     * 每次都将当前元素插入到左侧已经排序的数组中，使得插入之后左侧数组依然有序。
     * 1.遍历每个元素
     * 2.当前元素 和 左侧已经有序数组进行比较
     * 3.如果当前元素i小于左侧元素j, 那就进行当前元素的值和左侧元素下一个进行比较。
     * @param array
     * @return
     */
    public static int[] insertion(int[] array){
        for(int i=1;i<array.length;i++){
            for(int j=i;j>0;j--){
                if(array[j]<array[j-1]){
                    swap(array,j,j-1);
                }else {
                    break;
                }
            }
        }
        return array;
    }


    public static int[] maopao(int[] array){
        boolean isSwap = false;
        for (int i = 1;i<array.length-1 & isSwap;i++){
            isSwap = false;
            for (int j=0;j<array.length-1-i;j++){
                if(array[j]>array[j+1]){
//                    array[i]=array[i]^array[j];
//                    array[j]=array[i]^array[j];
//                    array[i]=array[j]^array[i];
                    int temp =  array[j+1];
                    array[j] = array[j];
                    array[j] = temp;
                    isSwap = true;
                }
            }
        }
        return array;
    }

    public static void main(String[] args) {
        int[] array = {1,6,5,8,3,9,3,6};
/*        for (int i : selectionSort(array)) {
            System.out.print(i+"  ");
        }*/

        array = maopao(array);
        System.out.println(array);
        for (int i : array) {
            System.out.print(i+"  ");
        }

    }
}
