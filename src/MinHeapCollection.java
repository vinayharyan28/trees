import java.util.PriorityQueue;

public class MinHeapCollection {
    public static void main(String[] args){
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        minHeap.add(10);
        minHeap.add(5);
        minHeap.add(15);
        minHeap.add(3);

        while (!minHeap.isEmpty()){
            System.out.println(minHeap.poll());
        }
    }
}
