package nowcoder;

/**
 * @author luotao
 * @date 2022-2-16  17:21
 */
public class offer001 {
    // a 除数， b 被除数
    public static int divide(int a, int b) {
        int res = 0;
        int shift = 0;
        int sign = (a>0) ^ (b>0) ? -1 : 1;
        Math.abs(a);
        Math.abs(b);
        while(a>=b){
            shift = getMaxShift(a,b);
            a = a- (b<<shift);
            res += 1<<shift;
        }
        return res;
    }
    // a 除数， b 被除数
    public static int getMaxShift(int a,int b){
        int pos = 0;
        if(a<b){
            return 0;
        }
        while(b<<pos <=a){
            pos++;
        }
        return pos-1;
    }

    public static void main(String[] args) {
        //int a = -1<<31, b = 1<<31;
        //System.out.println(divide(a, b ));
        //System.out.println(getMaxShift(16, 16));
        //System.out.println(1<<2);
        int status = 4;
        if( !(status==2 || status==3) ){
            System.out.println("not");
        }
    }

    public void reverseString(char[] s) {
        int first = 0;
        int end = s.length-1;
        while(first<=end){
            swap(s,first++,end--);
        }
    }
    public void swap(char[] s,int a,int b){
        char temp = s[a];
        s[a] = s[b];
        s[b] = temp;
    }




}
