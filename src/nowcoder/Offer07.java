package nowcoder;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author luotao
 * @date 2022-7-3  10:06
 * 剑指Offer 07 重建二叉树
 */
public class Offer07 {
    public TreeNode builderTreeBuilderTre(int[] preorder, int[] inorder) {

        return null;
    }

    public static void printPre(TreeNode node) {
        if (node == null) {
            return;
        }
        System.out.print(node.val + ",");
        printPre(node.left);
        printPre(node.right);
    }

    public static void printIn(TreeNode node) {
        if (node == null) {
            return;
        }
        printIn(node.left);
        System.out.print(node.val + ",");
        printIn(node.right);
    }

    /**
     * 迭代
     *
     * @param preorder
     * @param inorder
     * @return
     */
    public static TreeNode builderTree(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }

        TreeNode root = new TreeNode(preorder[0]);
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        //中序索引
        int inorderIndex = 0;
        //遍历先序
        for (int i = 1; i < preorder.length; i++) {
            int preorderVal = preorder[i];
            TreeNode node = stack.peek();
            // 父 左 右
            // 左 父 右
            // 当没有左子树时，两者都是 父，右， 那么必然node.val 会等于 inorder[inorderIndex]
            if (node.val != inorder[inorderIndex]) {
                node.left = new TreeNode(preorderVal);
                stack.push(node.left);
            } else { //在先序遍历中找到栈底节点的最左节点，
                while (!stack.isEmpty() && stack.peek().val == inorder[inorderIndex]) {
                    node = stack.pop();
                    inorderIndex++;
                }
                node.right = new TreeNode(preorderVal);
                stack.push(node.right);
            }
        }
        return root;
    }

    private static Map<Integer, Integer> indexMap;

    public static TreeNode buildtreedg(int[] preorder, int[] inorder) {
        int n = preorder.length;
        indexMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            indexMap.put(inorder[i], i);
        }
        return myBuildTree(preorder, inorder, 0, n - 1, 0, n - 1);
    }

    //        int[] pre = {3, 9, 8, 5, 4, 10, 20, 15, 7};
    //        int[] in = {4, 5, 8, 10, 9, 3, 15, 20, 7};
    public static TreeNode myBuildTree(int[] preorder, int[] inorder,
                                       int preorder_left, int preorder_right,
                                       int inorder_left, int inorder_right) {
        if (preorder_left > preorder_right) {
            return null;
        }

        int preorder_root = preorder_left;

        TreeNode root = new TreeNode(preorder[preorder_root]);
        // 父节点在中序遍历中的位置
        int inorder_root = indexMap.get(root.val);
        // 父节点 第一个左节点和旗下的所有子节点个数
        int size_left_subtree = inorder_root - inorder_left;

        root.left = myBuildTree(preorder, inorder,
                preorder_left + 1, preorder_left + size_left_subtree,
                inorder_left, inorder_root - 1);

        root.right = myBuildTree(preorder, inorder,
                preorder_left + size_left_subtree+1, preorder_right, inorder_root + 1, inorder_right);
        return root;
    }


    public static void main(String[] args) {
        int[] pre = {3, 9, 8, 5, 4, 10, 20, 15, 7};
        int[] in = {4, 5, 8, 10, 9, 3, 15, 20, 7};
        TreeNode root = buildtreedg(pre, in);
        printPre(root);
        System.out.println();
        printIn(root);
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }
}