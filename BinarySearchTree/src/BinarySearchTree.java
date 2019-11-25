class Node {
    public int key = 0;
    public int value = 0;
    public Node left = null;
    public Node right = null;

    public Node(int key, int value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return "(" +
                 key +
                ", " + value +
                ')';
    }
}
public class BinarySearchTree {
    public static Node root = null;

    //插入
    public static boolean put(int key, int value) {
        Node node = new Node(key, value);
        if(root == null) {
            root = node;
            return true;
        }
        Node cur = root;
        Node prev = null;
        while(cur != null) {
            if(cur.key > key) {
                prev = cur;
                cur = cur.left;
            } else if(cur.key < key) {
                prev = cur;
                cur = cur.right;
            } else {
                //如果树中已存在key， 则视为插入失败
                return false;
            }
        }
        if(prev.key > key) {
            prev.left = node;
        } else {
            prev.right = node;
        }
        return true;
    }

    //查找
    public static Node find(int key) {
        Node cur = root;
        while(cur != null) {
            if(cur.key == key) {
                return cur;
            } else if(cur.key > key){
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        return null;
    }

    //删除
    public static boolean remove(int key) {
        //1. 要删除的元素cur左子树为空
        //  1.1. cur为root
        //  1.2. cur不是root，cur为parent的左子树
        //  1.3. cur不是root，cur为parent的右子树
        //2. 要删除的元素cur右子树为空
        //  2.1. cur为root
        //  2.2. cur不是root， cur为parent的左子树
        //  2.3. cur不是root， cur为parent有右子树
        //3. 要删除的元素cur既有左子树也有右子树
        //  这时找到cur右子树的最左元素replace将cur覆盖，然后删除replace即可

        Node cur = root;
        Node parent = null;
        while (cur != null) {
            if (cur.key == key) {
                removeNode(cur, parent);
                return true;
            } else if (cur.key > key) {
                parent = cur;
                cur = cur.left;
            } else {
                parent = cur;
                cur = cur.right;
            }
        }
        return false;
    }
    private static void removeNode(Node cur, Node parent) {
        if (cur.left == null) {     //cur的左子树为空
            if (cur == root) {
                root = cur.right;
            } else if (parent.left == cur) {
                parent.left = cur.right;
            } else if (parent.right == cur) {
                parent.right = cur.right;
            }
        } else if (cur.right == null) {     //cur的右子树为空
            if (cur == root) {
                root = cur.left;
            } else if (parent.left == cur) {
                parent.left = cur.left;
            } else if (parent.right == cur) {
                parent.right = cur.left;
            }
        } else {        //cur既有左子树也有右子树
            Node replace = cur.right;
            Node replaceParent = cur;
            while (replace.left != null) {
                replaceParent = replace;
                replace = replace.left;
            }
            cur.key = replace.key;
            if (replaceParent.left == replace) {
               replaceParent.left = replace.right;
            } else if (replaceParent.right == replace) {
                replaceParent.right = replace.right;
            }
        }
    }

    //先序遍历
    public static void prevOrder(Node root) {
        if(root == null) {
            return;
        }
        System.out.print(root.toString() + " ");
        prevOrder(root.left);
        prevOrder(root.right);
    }
    //中序遍历
    public static void inOrder(Node root) {
        if(root == null) {
            return;
        }
        inOrder(root.left);
        System.out.print(root.toString() + " ");
        inOrder(root.right);
    }

    public static void main(String[] args) {
        put(5,55);
        put(1,11);
        put(7,77);
        put(4,44);
        put(9,99);
        put(2,22);
        remove(4);
        prevOrder(root);
        System.out.println();
        //System.out.println(find(9));
    }
}
