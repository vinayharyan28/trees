package binarysearchtree;

public class BinarySearchTree {
    private static class Node{
        private int value, height;
        private Node left, right;
        public Node(int value){
            this.value = value;
        }
    }

    private Node root;

    public BinarySearchTree(){

    }


    public int height(Node node){
        if (node == null){
            return -1;
        }
        return node.height;
    }

    public boolean isEmpty(){
        return root==null;
    }

    public void display(){
        display(root, "Root Node: ");
    }
    private void display(Node node, String details){
        if (node == null){
            return;
        }
        System.out.println(details+node.value);
        display(node.left, "Left child of " + node.value + " : ");
        display(node.right, "Right child of " + node.value + " : ");
    }

    public void insert(int value){
        root = insert(value, root);
    }

    private Node insert(int value, Node node){
        if (node == null){
            return new Node(value);
        }

        if (value < node.value){
            node.left = insert(value, node.left);
        }else {
            node.right = insert(value, node.right);
        }

        node.height = Math.max(height(node.left), height(node.right));
        return node;
    }

    public boolean balanced(){
        return balanced(root);
    }

    private boolean balanced(Node node){
        if (node==null){
            return true;
        }
        return Math.abs(height(node.left) - height(node.right)) <= 1 && balanced(node.left) && balanced(node.right);
    }

    public void populate(int[] numbers){
        for (int number : numbers) {
            insert(number);
        }
    }

    public void preorder(){
        preorder(root);
    }

    public void preorder(Node node){
        if (node == null){
            return;
        }

        System.out.print(node.value + " ");
        preorder(node.left);
        preorder(node.right);
    }

    public void inorder(){
        inorder(root);
    }

    public void inorder(Node node){
        if (node == null){
            return;
        }
        inorder(node.left);
        System.out.print(node.value + " ");
        inorder(node.right);
    }

    public void postorder(){
        postorder(root);
    }

    public void postorder(Node node){
        if (node == null){
            return;
        }
        postorder(node.left);
        postorder(node.right);
        System.out.print(node.value + " ");
    }

    public static void main(String[] args){
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        int[] numbers = {10, 11, 2, 3, 5, 28, 34};
        binarySearchTree.populate(numbers);
        binarySearchTree.display();
        System.out.println();
        binarySearchTree.preorder();
        System.out.println();
        binarySearchTree.inorder();
        System.out.println();
        binarySearchTree.postorder();

    }

}
