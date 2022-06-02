package nowcoder;

import java.util.HashMap;
import java.util.Stack;

/**
 * @author luotao
 * @date 2022-2-18  10:01
 */
public class leetcode20 {
    public boolean isValid(String s) {
        HashMap<Character,Character> kh = new HashMap<>();
        kh.put('(',')');
        kh.put('[',']');
        kh.put('{','}');
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if(kh.containsKey(c)){
                stack.push(c);
            }else{
                char zkh = stack.pop();
                char ykh = kh.get(zkh);
                if(ykh!=c){
                    return false;
                }
            }
        }
        return true;
    }
}
