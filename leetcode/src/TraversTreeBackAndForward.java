import java.util.Stack;

/**
 * Created by zhangying on 7/18/18.
 * 正反向遍历二叉树
 */
public class TraversTreeBackAndForward {

    class TreeNode {
        TreeNode leftChild;
        TreeNode rightChild;
        int val;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public void test(int[] data) {
        traverse(buildTree(data, 0));
    }

    private void traverse(TreeNode root) {
        Stack<TreeNode> target = new Stack();
        Stack<TreeNode> backUp = new Stack();
        target.add(root);
        boolean isBackVerse = false;
        while (!target.isEmpty() || !backUp.isEmpty()) {
            TreeNode node = target.pop();
            System.out.print(node.val + ", ");
            if (isBackVerse) {
                if (node.rightChild != null) {
                    backUp.push(node.rightChild);
                }
                if (node.leftChild != null) {
                    backUp.push(node.leftChild);
                }
            } else {
                if (node.leftChild != null) {
                    backUp.push(node.leftChild);
                }
                if (node.rightChild != null) {
                    backUp.push(node.rightChild);
                }
            }
            if (target.isEmpty()) {
                Stack temp = target;
                target = backUp;
                backUp = temp;
                isBackVerse = !isBackVerse;
            }
        }
    }


    private TreeNode buildTree(int[] data, int index) {
        int length = data.length;
        if (length == 0) return null;
        if (index >= length) return null;
        TreeNode node = new TreeNode(data[index]);
        int leftChildIndex = 2 * index + 1;
        if (leftChildIndex < length) {
            if (data[leftChildIndex] != -1) {
                TreeNode leftChild = buildTree(data, leftChildIndex);
                node.leftChild = leftChild;
            }
        }
        int rightChildIndex = 2 * index + 2;
        if (rightChildIndex < length) {
            if (data[rightChildIndex] != -1) {
                TreeNode rightChild = buildTree(data, rightChildIndex);
                node.rightChild = rightChild;
            }
        }
        return node;
    }
}
