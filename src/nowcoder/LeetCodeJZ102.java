package nowcoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author luotao
 * @date 2022-10-30  23:00
 * https://leetcode.cn/problems/binary-tree-level-order-traversal/?favorite=2cktkvj
 */
public class LeetCodeJZ102 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root==null){
            return result;
        }
        Queue<TreeNode> queue = new LinkedBlockingDeque<>();
        queue.add(root);
        while (!queue.isEmpty()){
            List<Integer> list = new ArrayList<>();
            int curNode = queue.size();
            for (int i = 0; i < curNode; i++) {
                TreeNode poll = queue.poll();
                list.add(poll.val);
                if(poll.left!=null){
                    queue.add(poll.left);
                }
                if(poll.right!=null){
                    queue.add(poll.right);
                }
            }
            result.add(list);
        }
        return result;
    }
}