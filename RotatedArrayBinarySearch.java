public class RotatedArrayBinarySearch {

    public static void main(String[] args) {
        RotatedArrayBinarySearch rabs = new RotatedArrayBinarySearch();
        int arrayLength = Integer.parseInt(args[0]);
        int[] arr = new int[arrayLength];
        for (int i = 1; i <= arrayLength; i++) {
            arr[i-1] = Integer.parseInt(args[i]);
        }
        // key = searchCandidate
        int key = Integer.parseInt(args[arrayLength + 1]);

        System.out.println("arry = " + rabs.print(arr) + ", len = " + arr.length + ", key = " + key);
        int foundIndex = rabs.findInRotated(arr, 0, arr.length - 1, key);
        if (foundIndex >=0) {
            System.out.println("Key " + key + " found at position: " + foundIndex);
        } else {
            System.out.println("Key " + key + " NOT found. Index = " + foundIndex);
        }
    }

    public String print(int[] arr) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i : arr) {
            sb.append(i + ", ");
        }
        sb.append("]");
        return sb.toString();
    }

    /**
    * Following algo mentioned at : 
    * http://leetcode.com/2010/04/searching-element-in-rotated-array.html
    */
    public int findInRotated(int[] arr, int start, int end, int key) {
        while (start <= end) {
            System.out.println("DEBUG: start = " + start + ", end = " + end + ", key = " + key);
            if (start > end) {
                return -1; // not found
            }

            // find the mid
            int mid = start + (end-start)/2;
            if (arr[mid] == key) {
                return mid; // found the index;
            }
    
            // If lower half is sorted
            if (arr[start] <= arr[mid]) {
                // if key is within range of lower half
                if ((key < arr[mid]) && (key >= arr[start])) {
                    end = mid-1;
                } else {
                    // search in other half
                    start = mid+1;
                }
            } else {    // upper half is sorted
                if (key > arr[mid] && key <= arr[end]) {
                    // if key is within range of upper half
                    start = mid+1;
                } else {
                    // search in lower half
                    end = mid-1;
                }
            }
        }
        return -1; // not found
    }
}
