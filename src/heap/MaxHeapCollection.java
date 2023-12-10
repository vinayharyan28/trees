package heap;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MaxHeapCollection {
    public static void main(String[] args) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        maxHeap.add(10);
        maxHeap.add(5);
        maxHeap.add(15);
        maxHeap.add(3);

        while (!maxHeap.isEmpty()){
            System.out.println(maxHeap.poll());
        }
    }
}
