public class Test {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        //tree.preOrderTraversal(tree.root);
        // tree.inOrderTraversal(tree.root);
        //tree.postOrderTraversal(tree.root);
        //System.out.println(tree.find(tree.root, 'B'));
        tree.levelOrderTraversal(tree.root);
    }
}
