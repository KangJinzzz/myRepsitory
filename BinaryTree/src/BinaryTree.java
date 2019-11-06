import java.util.LinkedList;
import java.util.Queue;

class TreeNode {
    public char val;
    public TreeNode left = null;
    public TreeNode right = null;

    public TreeNode(char val) {
        this.val = val;
    }
}


public class BinaryTree {
    public TreeNode root = null;

    public BinaryTree() {
        root = new TreeNode('A');
        TreeNode B = new TreeNode('B');
        TreeNode C = new TreeNode('C');
        TreeNode D = new TreeNode('D');
        TreeNode E = new TreeNode('E');
        TreeNode F = new TreeNode('F');
        TreeNode G = new TreeNode('G');
        root.left = B;
        root.right = C;
        B.left = D;
        B.right = E;
        E.left = G;
        C.right = F;
    }

    //前序遍历
    public void preOrderTraversal(TreeNode root) {
        if( root == null) {
            return;
        }

        System.out.print(root.val + " ");
        preOrderTraversal(root.left);
        preOrderTraversal(root.right);
    }

    // 中序遍历
    public void inOrderTraversal(TreeNode root){
        if( root == null) {
            return;
        }

        inOrderTraversal(root.left);
        System.out.print(root.val + " ");
        inOrderTraversal(root.right);
    }

    // 后序遍历
    public void postOrderTraversal(TreeNode root){
        if( root == null) {
            return;
        }

        postOrderTraversal(root.left);
        postOrderTraversal(root.right);
        System.out.print(root.val + " ");
    }

    //求结点个数
    public int getSize(TreeNode root) {
        if(root == null) {
            return 0;
        }
        return 1 + getSize(root.left) + getSize(root.right);
    }
    //求叶子结点个数
    public int getLeafSize(TreeNode root) {
        if(root == null) {
            return 0;
        }
        if(root.left == null && root.right == null) {
            return 1;
        }
        return getLeafSize(root.left) + getLeafSize(root.right);
    }

    // 子问题思路-求第 k 层结点个数
    public int getKLevelSize(TreeNode root, int k) {
        if(root == null || k < 1) {
            return 0;
        }
        if(k == 1) {
            return 1;
        }
        // 求第 k 层节点的个数,
        // 求左子树的第 k - 1 层节点的个数 + 右子树的 k - 1 层
        return getKLevelSize(root.left, k - 1) + getKLevelSize(root.right, k - 1);
    }
    // 查找 val 所在结点，没有找到返回 null
    // 按照 根 -> 左子树 -> 右子树的顺序进行查找
    // 一旦找到，立即返回，不需要继续在其他位置找
    public TreeNode find(TreeNode root, char val) {
        if(root == null) {
            return null;
        }
        //如果是当前节点，返回
        if(root.val == val) {
            return root;
        }

        TreeNode ret = find(root.left, val);
        if(ret != null) {
            return ret;
        }
        return find(root.right, val);
    }

    // 层序遍历
    public void levelOrderTraversal(TreeNode root) {
        if(root == null) {
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        //把根节点插入队列
        queue.offer(root);
        while (!queue.isEmpty()) {
            // 2. 循环取队首元素. 访问这个元素.
            TreeNode cur = queue.poll();
            System.out.print(cur.val + " ");
            // 3. 把当前这个队首元素左子树和右子树都插入队列中.
            if (cur.left != null) {
                queue.offer(cur.left);
            }
            if (cur.right != null) {
                queue.offer(cur.right);
            }
        }

    }


}
