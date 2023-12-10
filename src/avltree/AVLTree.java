package avltree;

public class AVLTree {
    private static class Node{
        private int value, height;
        private Node left, right;
        public Node(int value){
            this.value = value;
        }
    }

    private Node root;

    public int height(Node node){
        if (node == null){
            return -1;
        }
        return node.height;
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

        return rotate(node);
    }

    public Node rotate(Node node){
        if (height(node.left) - height(node.right) > 1){
            //left heavy
            if (height(node.left.left) - height(node.left.right) > 0){
                // left of left case
                return rightRotate(node);
            }
            if (height(node.left.left) - height(node.left.right) < 0){
                // left right case
                node.left = leftRotate(node.left);
                return rightRotate(node);
            }
        }

        if (height(node.left) - height(node.right) < -1){
            // Right heavy
            if (height(node.right.left) - height(node.right.right) < 0){
                // Right of Right case
                return leftRotate(node);
            }

            if (height(node.right.left) - height(node.right.right) > 0){
                // Left right case
                node.right = rightRotate(node.right);
                return leftRotate(node);
            }
        }
        return node;
    }

    public Node rightRotate(Node parent){
        Node child = parent.left;
        Node terminal = child.right;
        child.right = parent;
        parent.left = terminal;
        parent.height = Math.max(height(parent.left), height(parent.right) + 1);
        child.height = Math.max(height(child.left), height(child.right) + 1);
        return child;
    }

    public Node leftRotate(Node child){
        Node parent = child.right;
        Node terminal = parent.left;
        parent.left = child;
        child.right = terminal;
        parent.height = Math.max(height(parent.left), height(parent.right) + 1);
        child.height = Math.max(height(child.left), height(child.right) + 1);
        return parent;
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


}
