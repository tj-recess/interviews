import java.util.*;

public class ArrayQuestions {
    
    public static void main(String[] args) {
        ArrayQuestions aq = new ArrayQuestions();

        int size = Integer.parseInt(args[0]);
        int[] arr = aq.populateArray(size, false, 5);
        Arrays.sort(arr);
        System.out.println("Array: " + Arrays.toString(arr));
        int x = Integer.parseInt(args[1]);
        System.out.println(x + " occurs " + aq.countOccurrences(arr, x) + " times.");
        // List<Integer> result = aq.getContiguousLargestSum(arr);
        // System.out.println("Largest contiguous sum in array " + Arrays.toString(arr) + 
            // " is: " + result.get(2) + "; startIndex = " + result.get(0) + "; endIndex = " + result.get(1));
        // System.out.println("Max sum with no two contiguous elements in array " + Arrays.toString(arr) + 
            // " is: " + aq.maxSumNoTwoContiguous(arr));

    }

    /**
    * Finds occurences of given integer in a sorted array in O(log n) time
    */
    public int countOccurrences(int[] arr, int x) {
        // perform binary search to get first location of x,
        // perform binary search to get last location of x
        // return last - first + 1;
        int first = binarySearchFirst(arr, x);
        int last = binarySearchLast(arr, x);
        return first == -1 ? -1 : (last - first + 1);
    }

    private int binarySearchLast(int[] arr, int x) {
        int start = 0;
        int end = arr.length-1;

        while (start <= end) {
            int mid = start + (end-start)/2;
            if (arr[mid] == x && (mid == arr.length-1 || arr[mid+1] > x)) {
                return mid;
            } 
            // go right
            else if (arr[mid] < x || arr[mid+1] == x) {
                start = mid+1;
            }
            // go left
            else {
                end = mid-1;
            }
        }
        // not found
        return -1;
    }

    private int binarySearchFirst(int[] arr, int x) {
        int start = 0;
        int end = arr.length-1;

        while (start <= end) {
            int mid = start + (end-start)/2;
            if (arr[mid] == x && (mid == 0 || arr[mid-1] < x)) {
                return mid;
            } 
            // go left
            else if (arr[mid] > x || arr[mid-1] == x) {
                end = mid-1;
            }
            // go right
            else {
                start = mid+1;
            }
        }
        // not found
        return -1;
    }

    private int[] populateArray(int size, boolean allowNegative, int maxValue) {
        Random r = new Random();
        int[] arr = new int[size];
        for (int i  = 0; i < size; i++) {
            arr[i] = r.nextInt(maxValue);
            if (allowNegative && r.nextBoolean()) {
                arr[i] = -1 * r.nextInt(maxValue);
            }
        }
        return arr;
    }

    public int maxSumNoTwoContiguous(int[] arr) {
        int lastIncl = arr[0];
        int lastExcl = 0;

        for (int i = 1; i < arr.length; i++) {
            int excl = Math.max(lastIncl, lastExcl);
            int incl = lastExcl + arr[i];
            lastExcl = excl;
            lastIncl = incl;
        }
        return Math.max(lastExcl, lastIncl);
    }

    /**
    * 0th element in list is startIndex,
    * 1st element is endIndex,
    * 2nd element is sum.
    */
    public List<Integer> getContiguousLargestSum(int[] arr) {
        int globalStart = 0, localStart = 0;
        int globalEnd = 0, localEnd = 0;
        int globalSum = 0, localSum = 0;

        for (int i = 0; i < arr.length; i++) {
            if (localSum >= 0 && (localSum + arr[i] >= 0)) {
                // add this element as sum is still >= 0
                localSum = localSum + arr[i];
            } else {
                localStart = i;
                localSum = arr[i];
            }
            localEnd = i;
            if (localSum > globalSum) {
                globalSum = localSum;
                globalStart = localStart;
                globalEnd = localEnd;
            }
        }

        List<Integer> result = new ArrayList<Integer>();
        result.add(globalStart);
        result.add(globalEnd);
        result.add(globalSum);
        return result;
    }
}
