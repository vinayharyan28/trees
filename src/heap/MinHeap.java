package heap;

import java.util.ArrayList;
import java.util.List;


public class MinHeap {
    private List<Integer> heap;
    public MinHeap(){
        heap = new ArrayList<>();
    }

    public void insert(int value){
        heap.add(value);
        heapifyUp(heap.size()-1);
    }

    public int size(){
        return heap.size();
    }

    public boolean isEmpty(){
        return heap.isEmpty();
    }

    private void swap(int source, int destination){
        int temp = heap.get(source);
        heap.set(source, heap.get(destination));
        heap.set(destination, temp);
    }

    private void heapifyUp(int index){
        int parentIndex = (index-1)/2;
        while (index > 0 && heap.get(index) < heap.get(parentIndex)){
            swap(index, parentIndex);
            index = parentIndex;
            parentIndex = (index - 1) / 2;
        }
    }

    private void heapifyDown(int index){
        int leftChild = 2 * index + 1;
        int rightChild = 2 * index + 2;
        int smallest = index;

        if (leftChild < heap.size() && heap.get(leftChild) < heap.get(smallest)){
            smallest = leftChild;
        }

        if (rightChild < heap.size() && heap.get(rightChild) < heap.get(smallest)){
            smallest = rightChild;
        }

        if (smallest != index){
            swap(index, smallest);
            heapifyDown(smallest);
        }
    }

    public int extractMin(){
        if (heap.isEmpty()){
            throw new IllegalStateException("Heap is empty");
        }

        int min = heap.get(0);
        int lastElement = heap.remove(heap.size()-1);
        if (!heap.isEmpty()){
            heap.set(0, lastElement);
            heapifyDown(0);
        }
        return min;
    }

    public static void main(String[] args) {
        MinHeap minHeap = new MinHeap();
        minHeap.insert(10);
        minHeap.insert(5);
        minHeap.insert(15);
        minHeap.insert(3);

        while (!minHeap.isEmpty()){
            System.out.println(minHeap.extractMin());
        }
    }


}
