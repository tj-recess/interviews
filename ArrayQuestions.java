import java.util.*;

public class ArrayQuestions {
    
    public static void main(String[] args) {
        ArrayQuestions aq = new ArrayQuestions();

        int size = Integer.parseInt(args[0]);
        int[] arr = aq.populateArray(size);
        List<Integer> result = aq.getContiguousLargestSum(arr);
        System.out.println("Largest contiguous sum in array " + Arrays.toString(arr) + " is: " + result.get(2) + "; startIndex = " + result.get(0) + "; endIndex = " + result.get(1));
        System.out.println("Max sum with no two contiguous elements in array " + Arrays.toString(arr) + " is: " + aq.maxSumNoTwoContiguous(arr));
    }

    private int[] populateArray(int size) {
        Random r = new Random();
        int[] arr = new int[size];
        for (int i  = 0; i < size; i++) {
            if (r.nextBoolean()) {
                arr[i] = r.nextInt(11);
            } else {
                arr[i] = -1 * r.nextInt(11);
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
