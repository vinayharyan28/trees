package segmenttree;

public class SegmentTree {
    private static class Node {
        int data, startInterval, endInterval;
        Node left, right;
        public Node(int startInterval, int endInterval){
            this.startInterval = startInterval;
            this.endInterval = endInterval;
        }
    }
    
    Node root;
    
    public SegmentTree(int[] arr){
        this.root = constructTree(arr, 0, arr.length-1);
    }

    private Node constructTree(int[] arr, int start, int end) {
        if (start==end){
            //Leaf node
            Node leaf = new Node(start, end);
            leaf.data = arr[start];
            return leaf;
        }

        // Create new node with index you are at
        Node node = new Node(start, end);
        int mid = (start+end)/2;
        node.left = constructTree(arr, start, mid);
        node.right = constructTree(arr, mid+1, end);
        node.data = node.left.data + node.right.data;
        return node;
    }

    public void display(){
        display(root);
    }

    private void display(Node node) {
        String str = "";
        if (node.left != null) {
            str = str + "Interval=[" + node.left.startInterval + "-" + node.left.endInterval + "] and data: " + node.left.data + " --> ";
        } else {
            str = str + "No left child";
        }

        //for current node
        str = str + "Interval=[ " + node.startInterval + "-" + node.endInterval + "] and data: "+node.data + " --> ";

        if (node.right != null){
            str = str + "Interval=[" + node.right.startInterval + "-" + node.right.endInterval + "] and data: " + node.right.data + " --> ";
        }else {
            str = str + "No Right child";
        }
        System.out.println(str);

        // call recursion
        if(node.left != null){
            display(node.left);
        }

        if (node.right != null){
            display(node.right);
        }
    }

    public int query(int queryStartIndex, int queryEndIndex){
        return query(root, queryStartIndex, queryEndIndex);
    }

    private int query(Node node, int queryStartIndex, int queryEndIndex){
        if(node.startInterval >= queryStartIndex && node.endInterval <= queryEndIndex){
            //Node is completely lying inside query
            return node.data;
        } else if (node.startInterval > queryEndIndex || node.endInterval < queryStartIndex) {
            return 0;
        } else{
            return query(node.left, queryStartIndex, queryEndIndex) + query(node.right, queryStartIndex, queryEndIndex);
        }
    }

    // update
    public int update(Node node, int index, int value){
        if (index >= node.startInterval && index <= node.endInterval){
            if (index == node.startInterval && index == node.endInterval){
                node.data = value;
            }else {
                int leftAns = update(node.left, index, value);
                int rightAns = update(node.right, index, value);
                node.data = leftAns + rightAns;
            }
            return node.data;
        }
        return node.data;
    }

    public static void main(String[] args) {
        int[] arr = {3, 8, 6, 7, -2, -8, 4, 9};
        SegmentTree segmentTree = new SegmentTree(arr);
        segmentTree.display();
        System.out.println(segmentTree.query(1, 6));

    }
}
