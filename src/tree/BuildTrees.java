package tree;
import java.util.LinkedList;
import java.util.Queue;

public class BuildTrees {
    static class Node{
        int data;
        Node left, right;

        Node (int data){
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    static int index = -1;
    static Node buildTree(int[] nodes){
        index++;
        if (index == nodes.length - 1){
            index--;
            return null;
        }

        if (nodes[index] == -1){
            return null;
        }

        System.out.println(index + " " + nodes.length);
        Node newNode = new Node(nodes[index]);
        newNode.left = buildTree(nodes);
        newNode.right = buildTree(nodes);

        return newNode;
    }

    static void preorder(Node root){
        if (root == null){
            System.out.print(-1 + " ");
            return;
        }

        System.out.print(root.data + " ");
        preorder(root.left);
        preorder(root.right);
    }

    static void levelOrderTraversal(Node root){
        if (root == null){
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        queue.add(null);

        while (!queue.isEmpty()){
            Node currentNode = queue.remove();

            if (currentNode == null){
                System.out.println();
                if (queue.isEmpty()){
                    break;
                }else {
                    queue.add(null);
                }
            }else {
                System.out.print(currentNode.data + " ");
                if (currentNode.left != null){
                    queue.add(currentNode.left);
                }

                if (currentNode.right != null){
                    queue.add(currentNode.right);
                }
            }
        }
    }

    static int heightOfTree(Node root){
        if (root == null){
            return 0;
        }

        int lefHeight = heightOfTree(root.left);
        int rightHeight = heightOfTree(root.right);
        return Math.max(lefHeight, rightHeight) + 1;
    }

    static int countOfNodes(Node root){
        if (root == null){
            return 0;
        }

        int leftCount = countOfNodes(root.left);
        int rightCount = countOfNodes(root.right);
        return leftCount + rightCount + 1;
    }

    static int sumOfNodes(Node root){
        if (root == null){
            return 0;
        }

        int leftSum = sumOfNodes(root.left);
        int rightSum = sumOfNodes(root.right);
        return leftSum + rightSum + root.data;

    }

    static int diameter(Node root){
        if (root == null){
            return 0;
        }

        int leftDiameter = diameter(root.left);
        int leftHeight = heightOfTree(root.left);
        int rightDiameter = diameter(root.right);
        int rightHeight = heightOfTree(root.right);

        int selfDiameter = leftHeight + rightHeight + 1;
        return Math.max(selfDiameter, Math.max(leftDiameter, rightDiameter));

    }

    static class DiameterHeightInformation {
        int diameter;
        int height;

        DiameterHeightInformation(int diameter, int height){
            this.diameter = diameter;
            this.height = height;
        }
    }

    static DiameterHeightInformation diameterOptimization(Node root){
        if (root == null){
            return new DiameterHeightInformation(0, 0);
        }

        DiameterHeightInformation leftDiameterHeightInformation = diameterOptimization(root.left);
        DiameterHeightInformation rightDiameterHeightInformation = diameterOptimization(root.right);
        int diameter = Math.max(Math.max
                (leftDiameterHeightInformation.diameter, rightDiameterHeightInformation.diameter) ,
                leftDiameterHeightInformation.height + rightDiameterHeightInformation.height + 1);

        int height = Math.max(leftDiameterHeightInformation.height, rightDiameterHeightInformation.height) + 1;

        return new DiameterHeightInformation(diameter, height);
    }

    public static void main(String[] args) {
        int[] nodes = {1, 2, 4, -1, -1, 5, -1, -1, 3, -1, 6, -1 -1};
        Node root = buildTree(nodes);
        preorder(root);
        System.out.println();
        levelOrderTraversal(root);
        System.out.println("Height of tree: " + heightOfTree(root));
        System.out.println("Count of nodes: " + countOfNodes(root));
        System.out.println("Sum of nodes: " + sumOfNodes(root));
        System.out.println("Diameter of Tree: " + diameter(root));
        DiameterHeightInformation diameterHeightInformation = diameterOptimization(root);
        System.out.println("Diameter of tree: " + diameterHeightInformation.diameter + " and Height: " + diameterHeightInformation.height);
    }
}
