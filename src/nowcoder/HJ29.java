package nowcoder;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @author luotao
 * @date 2022-5-10  0:38
 * HJ29 字符串加解密
 */
public class HJ29 {
    public static void encrypt(String s){
        char[] arrays = s.toCharArray();
        StringBuffer rs = new StringBuffer();
        for(Character c : arrays){
            if(isDight(c)){
                char tc = c=='9' ? '0' : (char)(c+1);
                /*int t = Integer.parseInt(c+"");
                char tc;
                if(t==9){
                    tc = '0';
                }else{
                    tc = (char) (t - 1);
                }*/
                //char tc = t==9 ? '0' : (char) (t - 1);
                rs.append(tc);
            }else{
                int t = 0;
                if(isLowerCase(c)){
                    if( c == 'z'){
                        t = 'a';
                    }else{
                        t = c + 1;
                    }
                    t = t - 'a' + 'A';
                }else{
                    if( c == 'Z'){
                        t = 'A';
                    }else{
                        t = c + 1;
                    }
                    t = t - 'A' + 'a';
                }
                rs.append((char)t);
            }
        }
        System.out.println(rs);
    }

    public static void decrpyt(String s){
        char[] arrays = s.toCharArray();
        StringBuffer rs = new StringBuffer();
        for(char c : arrays){
            if(isDight(c)){
                char tc = c=='0' ? '9' : (char)(c-1);
                rs.append(tc);
            }else{
                int t = 0;
                if(isLowerCase(c)){
                    if( c == 'a'){
                        t = 'z';
                    }else{
                        t = c - 1;
                    }
                    t = t - 'a' + 'A';
                }else{
                    if( c == 'A'){
                        t = 'Z';
                    }else{
                        t = c - 1;
                    }
                    t = t - 'A' + 'a';
                }
                rs.append((char)t);
            }
        }
        System.out.println(rs);
    }

    public static boolean isDight(char c){
        return '0'<= c && c <= '9';
    }
    public static boolean isLowerCase(char c){
        return 'a'<= c && c<= 'z';
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String es = sc.nextLine();
        String ds = sc.nextLine();
        encrypt(es);
        decrpyt(ds);
        //ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor();
        Set<String> hs = new HashSet<>();

    }
}
