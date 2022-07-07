package nowcoder;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author luotao
 * @date 2022-7-3  12:23
 * 剑指offer 10 通过两个栈 实现队列
 */
public class Offer09 {

}
class CQueue{
    Deque<Integer> inStack = new ArrayDeque<>();
    Deque<Integer> outStack = new ArrayDeque<>();

    public void appendTail(int value){
        inStack.push(value);
    }

    public int deleteHead(){
        if(outStack.isEmpty()){
            if(inStack.isEmpty()){
                return -1;
            }
            in2out();
        }
        return outStack.pop();
    }

    private void in2out() {
        while(!inStack.isEmpty()){
            outStack.push(inStack.pop());
        }
    }

    public static void main(String[] args) {


    }
}