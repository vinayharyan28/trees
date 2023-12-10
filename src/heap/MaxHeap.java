package heap;

import java.util.ArrayList;
import java.util.List;

public class MaxHeap {
    private List<Integer> heap;

    public MaxHeap(){
        heap = new ArrayList<>();
    }

    private void swap(int source, int destination){
        int temp = heap.get(source);
        heap.set(source, heap.get(destination));
        heap.set(destination, temp);
    }

    private void heapifyUp(int index){
        int parentIndex = (index - 1) / 2;
        while (index > 0 && heap.get(index) > heap.get(parentIndex)){
            swap(index, parentIndex);
            index = parentIndex;
            parentIndex = (index-1) / 2;
        }
    }

    private void heapifyDown(int index){
        int leftChild = 2*index+1;
        int rightChild = 2*index+2;
        int largest = index;

        if (leftChild < heap.size() && heap.get(leftChild) > heap.get(largest)){
            largest = leftChild;
        }

        if (rightChild < heap.size() && heap.get(rightChild) > heap.get(largest)){
            largest = rightChild;
        }

        if (largest != index){
            swap(index, largest);
            heapifyDown(largest);
        }
    }

    public void insert(int value){
        heap.add(value);
        heapifyUp(heap.size()-1);
    }

    private int extractMax(){
        if (heap.isEmpty()){
            throw new IllegalStateException("Heap is empty");
        }

        int max = heap.get(0);
        int lastElement = heap.remove(heap.size() - 1);
        if (!heap.isEmpty()){
            heap.set(0, lastElement);
            heapifyDown(0);
        }

        return max;
    }

    public boolean isEmpty(){
        return heap.isEmpty();
    }

    public static void main(String[] args) {
        MaxHeap maxHeap = new MaxHeap();
        maxHeap.insert(10);
        maxHeap.insert(5);
        maxHeap.insert(15);
        maxHeap.insert(3);

        while (!maxHeap.isEmpty()){
            System.out.println(maxHeap.extractMax());
        }
    }
}
