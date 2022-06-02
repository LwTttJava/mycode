package interview;

import java.util.Scanner;

/**
 * @author luotao
 * @date 2022-5-11  16:03
 */
public class ScreeningQuestion02 {
    public static String dealSubString(String s){
        int lower = 0;
        int upper = 0;
        for (char c : s.toCharArray()) {
            if('a'<= c && c<='z'){
                lower++;
            }else if('A'<=c && c<='Z'){
                upper++;
            }
        }
        if(lower==upper){
            return s;
        }else if(lower<upper){
            return s.toUpperCase();
        }else{
            return s.toLowerCase();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = Integer.parseInt(sc.nextLine());
        String line = sc.nextLine();
        String[] split = line.split("-");
        StringBuilder sb = new StringBuilder();
        sb.append(split[0]);
        String other = line.substring(split[0].length()+1).replaceAll("-","");
        int i = 0;
        for (i = 0; i+k < other.length(); i+=k) {
            String s = other.substring(i,i+k);
            sb.append("-").append(dealSubString(s));
        }
        if(i<other.length()){
            sb.append("-").append(dealSubString(other.substring(i)));
        }
        // 12abc-abCABc-4aB@
        // 12abc-abc-ABC-4aB-@
        /*for (int i = 1; i < split.length; i++) {
            int j = 0;
            String temp = split[i];
            for(j = 0;j+k<temp.length();j+=k){
                String s = temp.substring(0,k);
                sb.append("-").append(dealSubString(s));
            }
            if(j<temp.length()){
                sb.append("-").append(dealSubString(temp.substring(j)));
            }
        }*/
        System.out.println(sb);
    }
}
