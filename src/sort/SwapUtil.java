package sort;

import sun.misc.BASE64Decoder;

/**
 * @author luotao
 * @date 2022-4-15  15:20
 */
public class SwapUtil {

    public static String getDecodeBase64(String str){
        byte[] b = null;
        String result = null;
        if(str != null){
            BASE64Decoder decoder = new BASE64Decoder();
            try {
                b = decoder.decodeBuffer(str);
                //result = new String(b, "utf-8");
                result = new String(b,"UTF-16");
                System.out.println(new String(b,"GB2312"));
                System.out.println(new String(b,"GB18030"));
                System.out.println(new String(b,"GB2312"));
                System.out.println(new String(b,"EUC_CN"));
                System.out.println(new String(b,"GB2312"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static void swap(int i, int j, int[] array) {
        int a = array[i];
        array[i] = array[j];
        array[j] = a;
    }

    public static  void printBinary(int num){
        for(int i = 31;i>=0;i--){
            System.out.print((num & (1<<i)) == 0 ? "0" : "1" );
        }
        System.out.println();
    }

    public static void main(String[] args) {
        String str = "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";
        getDecodeBase64(str);
       /* int c = -5;
        int d = (~c)+1;
        printBinary(c);
        printBinary(~c);
        printBinary(d);
        System.out.println("===========");
        printBinary(0);*/
        //System.out.println(d);
    }
}
