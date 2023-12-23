package segmenttree;

import java.util.Arrays;

public class SegmentTreeSubArraySum {
    static int[] segmentTree;

    public static void initialise(int n){
        segmentTree = new int[4*n];
    }


    static int buildSegmentTree(int[] arr, int segmentTreeIndex, int start, int end){
        if (start == end){
            segmentTree[segmentTreeIndex] = arr[start];
            return arr[start];
        }

        int middle = (start + end) / 2;
        buildSegmentTree(arr, 2*segmentTreeIndex+1, start, middle);
        buildSegmentTree(arr, 2*segmentTreeIndex+2, middle+1, end);
        segmentTree[segmentTreeIndex] = segmentTree[2*segmentTreeIndex+1] + segmentTree[2*segmentTreeIndex+2];
        return segmentTree[segmentTreeIndex];
    }

    private static int getSumUtil(int segmentTreeIndex, int segmentTreeIndex1, int segmentTreeIndex2, int queryIndex1, int queryIndex2){
        if (queryIndex2 <= segmentTreeIndex1 || queryIndex1 >= segmentTreeIndex2){ // None overlapping
            return 0;
        }else if(segmentTreeIndex1 >= queryIndex1 && segmentTreeIndex2 <= queryIndex2){ // Complete overlap
            return segmentTree[segmentTreeIndex];
        }else { // Partial overlap
            int middle = (segmentTreeIndex1 + segmentTreeIndex2) / 2;
            int left = getSumUtil(2 * segmentTreeIndex + 1, segmentTreeIndex1, middle, queryIndex1, queryIndex2);
            int right = getSumUtil(2 * segmentTreeIndex + 2, middle + 1, segmentTreeIndex2, queryIndex1, queryIndex2);
            return left + right;
        }
    }

    static int getSum(int[] arr, int queryIndex1, int queryIndex2){
        int n = arr.length;
        return getSumUtil(0,0, n-1, queryIndex1, queryIndex2);
    }

    static private void updateUtil(int i, int startingIndex, int endingIndex, int index, int difference){
        if(index > endingIndex || index < startingIndex){
            return;
        }

        segmentTree[i] += difference;
        if (startingIndex != endingIndex){
            int middle = (startingIndex + endingIndex) / 2;
            updateUtil(2*i+1, startingIndex, middle, index, difference);
            updateUtil(2*i+2, middle+1, endingIndex, index, difference);
        }
    }
    static void update(int[] arr, int index, int newValue){
        int n = arr.length;
        int difference = newValue - arr[index];
        arr[index] = newValue;
        updateUtil(0, 0, n-1, index, difference);
    }


    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8};
        int n = arr.length;
        initialise(n);
        System.out.println(buildSegmentTree(arr, 0, 0, n-1));
        System.out.println(Arrays.toString(segmentTree));

        System.out.println(getSum(arr, 2, 5));
        update(arr, 2, 2);
        System.out.println(getSum(arr, 2, 5));
    }
}
