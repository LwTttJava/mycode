package nowcoder;


public class Leetcode2196 {

    /*public TreeNode createBinaryTree(int[][] descriptions) {
        Map<Integer,TreeNode> allNode = new HashMap<>();
        HashSet<Integer> allChildren = new HashSet<>();
        int l = descriptions.length;
        for(int i = 0; i<l;i++){
            int v = descriptions[i][0];
            int c = descriptions[i][1];
            int lor = descriptions[i][2];
            if(!allNode.containsKey(v)){
                allNode.put(new TreeNode(v));
            }
            if(!allNode.containsKey(c)){
                allNode.put(new TreeNode(c));
            }
            if(lor==1){
                allNode.get(v).left = allNode.get(c);
            }else{
                allNode.get(v).right = allNode.get(c);
            }
            allChildren.add(c);
        }
        for(Integer key : allNode.keySet()){
            if(!allChildren.contains(key)){
                return allNode.get(key);
            }
        }
        return null;
    }*/
}
