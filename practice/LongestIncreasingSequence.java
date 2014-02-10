import java.util.*;

public class LongestIncreasingSequence {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int count = scanner.nextInt();
        int[] nums = new int[count];
        for (int i = 0; i < count; i++) {
            nums[i] = scanner.nextInt();
        }
        LongestIncreasingSequence lis = new LongestIncreasingSequence();
        System.out.println("Longest increasing sequence is: " + lis.solve(nums));
    }
    
    public List<Integer> solve(int[] nums) {
        int[] sol = new int[nums.length];
        int[] len = new int[nums.length];

        for (int i = 0, length = nums.length; i < length; i++) {
            sol[i] = i;
            len[i] = 1;
            for (int j = i-1; j >= 0; j--) {
                if (nums[j] < nums[i]) {
                    if (len[i] <= len[j]) {
                        len[i] = len[j] + 1;
                        sol[i] = j;
                    }
                }
            }
        }

        List<Integer> list = new ArrayList<Integer>();
        int maxIndex = findIndexOfMax(len);
        list.add(nums[maxIndex]);
        while (maxIndex != sol[maxIndex]) {
            list.add(nums[sol[maxIndex]]);
            maxIndex = sol[maxIndex];
        }
        Collections.sort(list);
        return list;
    }

    public int findIndexOfMax(int[] arr) {
        int maxVal = arr[arr.length-1];
        int maxIndex = arr.length-1;
        for (int i = maxIndex - 1; i >= 0; i--) {
            if (arr[i] > maxVal) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }
}
