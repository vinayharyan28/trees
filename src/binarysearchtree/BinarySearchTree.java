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

    private Node findInorderSuccessor(Node root){
        while (root.left != null){
            root = root.left;
        }
        return root;
    }

    private Node delete(Node root, int value){
        if (root.value < value){
            root.right = delete(root.right, value);
        }else if (root.value > value){
            root.left = delete(root.left, value);
        }else {
            // Case1 No child
            if (root.left == null && root.right == null){
                return null;
            }

            // Case2 Single child
            if (root.left == null){
                return root.right;
            }else if (root.right == null){
                return root.left;
            }

            // Case3 Both children
            Node inOrderSuccessor = findInorderSuccessor(root.right);
            root.value = inOrderSuccessor.value;
            root.right = delete(root.right, inOrderSuccessor.value);
        }

        return root;
    }

    private Node createBalancedBinarySearchTree(int[] arr, int startIndex, int endIndex){
        if (startIndex > endIndex){
            return null;
        }

        int middle = (startIndex + endIndex) / 2;
        Node root = new Node(arr[middle]);
        root.left = createBalancedBinarySearchTree(arr, startIndex, middle-1);
        root.right = createBalancedBinarySearchTree(arr, middle+1, endIndex);
        return root;
    }

    public void binaryBalancedSearchTree(int[] arr, int startIndex, int endIndex){
        root = createBalancedBinarySearchTree(arr, startIndex, endIndex);
    }

    public void deleteNode(int value){
         root = delete(root, value);
    }

    public static void main(String[] args){
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        int[] numbers = {8, 5, 3, 1, 4, 6, 10, 11, 14};
        binarySearchTree.populate(numbers);
        binarySearchTree.display();
        System.out.println("Preorder: ");
        binarySearchTree.preorder();
        System.out.println("\nInorder: ");
        binarySearchTree.inorder();
        System.out.println("\nPostorder: ");
        binarySearchTree.postorder();

        System.out.println("\nDelete: ");
        binarySearchTree.deleteNode(5);
        binarySearchTree.inorder();

        System.out.println();
        int[] arr = {3, 5, 6, 8, 10, 11, 12};
        binarySearchTree.binaryBalancedSearchTree(arr, 0, arr.length-1);
        System.out.println("Preorder: ");
        binarySearchTree.preorder();

    }

}
