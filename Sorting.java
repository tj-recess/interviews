import java.util.*;

public class Sorting {
    public static void main(String[] args) {
        int[] array = new int[args.length];
        for (int i = 0; i < args.length; i++) {
            array[i] = Integer.parseInt(args[i]);
        }
        
        Sorting s = new Sorting();
        System.out.println("Array: " + s.toString(array));
        System.out.println("Bubble sort: " + s.toString(s.bubbleSort(Arrays.copyOf(array, array.length))));
        System.out.println("Selection sort: " + s.toString(s.selectionSort(Arrays.copyOf(array, array.length))));
        System.out.println("Insertion sort: " + s.toString(s.insertionSort(Arrays.copyOf(array, array.length))));
        System.out.println("Merge sort: " + s.toString(s.mergeSort(Arrays.copyOf(array, array.length))));
        System.out.println("Quick sort: " + s.toString(s.quickSort(Arrays.copyOf(array, array.length))));
    }

    public int[] bubbleSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i; j < array.length; j++) {
                if (array[i] > array[j]) {
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
        return array;
    }

    public int[] selectionSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int minIndex = i;
            for (int j = i+1; j < array.length; j++) {
                if (array[minIndex] > array[j]) {
                    minIndex = j;
                }
            }
            // swap minIndex with i
            int temp = array[i];
            array[i] = array[minIndex];
            array[minIndex] = temp;
        }
        return array;
    }

    public int[] insertionSort(int[] array) {
        for(int i = 0; i < array.length; i++) {
            for (int j = i; j >= 1; j--) {
                if (array[j] < array[j-1]) {
                    // swap
                    int temp = array[j];
                    array[j] = array[j-1];
                    array[j-1] = temp;
                }
            }
        }                
        return array;
    }

    public int[] mergeSort(int[] array) {
        return mergeSort(array, 0, array.length);
    }

    private int[] mergeSort(int[] arr, int start, int end) {
        int len = end-start;
        if (len <= 1) {
            int[] sortedArr = new int[len];
            for(int i = 0; i < len; i++) {
                sortedArr[i] = arr[start++];
            }
            return sortedArr;
        }
        int mid = start + (end-start)/2;
        int[] first = mergeSort(arr, start, mid);
        int[] last = mergeSort(arr, mid, end);
        return merge(first, last);
    }

    private int[] merge(int[] first, int[] last) {
        int[] result = new int[first.length + last.length];
        int i=0, j=0, r=0;
        for(; i < first.length && j < last.length; r++) {
            if(first[i] < last[j]) {
                result[r] = first[i++];
            } else {
                result[r] = last[j++];
            }
        }

        while (i < first.length) {
            result[r++] = first[i++];
        }

        while (j < last.length) {
            result[r++] = last[j++];
        }
        return result;
    }

    public int[] quickSort(int[] array) {
        quickSort(array, 0, array.length-1);
        return array;
    }

    private int[] quickSort(int[] arr, int start, int end) {
        if (start < end) {
            int pivotLocation = partition(arr, start, end);
            quickSort(arr, 0, pivotLocation - 1);
            quickSort(arr, pivotLocation + 1, end);
        }
        return arr;
    }

    private int partition(int[] arr, int start, int end) {
        int pivot = arr[end];
        int swapLoc = start - 1;
        for(int i = start; i <= (end - 1); i++) {
            if (arr[i] <= pivot) {
                swapLoc++;
                swap(arr, i, swapLoc);
            }
        }
        // finally swap pivot with final location
        swapLoc++;
        swap(arr, swapLoc, end);
        return swapLoc;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public String toString(int[] array) {
        String s = "[";
        for (int i : array) {
            s = s + i + ", ";
        }
        s = s.substring(0, s.length() - 2);
        s += "]";
        return s;
    }
}
