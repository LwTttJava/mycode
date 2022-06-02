package interview;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author luotao
 * @date 2022-5-11  16:36
 */
public class ScreeningQuestion03 {
    public static void main(String[] args) {

    }

    /**
     * 层序遍历
     * @param node
     */
    public static void DFS(ThreeNode node){
        Queue<ThreeNode> queue = new LinkedBlockingQueue();
        queue.offer(node);
        while(!queue.isEmpty()){
            ThreeNode curr = queue.poll();

            if(curr.left!=null){
                queue.offer(curr.left);
            }
            if(curr.right!=null){
                queue.offer(curr.right);
            }

            System.out.println(curr.value);
        }
    }
}

/**
 * 后序 左 右 父
 * 中序 左 父 右
 */
class ThreeNode{
    public int value;
    public ThreeNode left;
    public ThreeNode right;
    public ThreeNode(int value){
        this.value = value;
    }
}