import java.util.*;

public class KnapSackDynamic {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int itemCount = scanner.nextInt();
        int[] values = new int[itemCount];
        int[] weights = new int[itemCount];
        for (int i = 0; i < itemCount; i++) {
            weights[i] = scanner.nextInt();
            values[i] = scanner.nextInt();
        }
        int sackCapacity = scanner.nextInt();
        KnapSackDynamic k = new KnapSackDynamic();
        System.out.println("Max value to steal = " + k.solve(weights, values, itemCount, sackCapacity));
    }

    private int solve(int[] weights, int[] values, int itemCount, int maxCapacity) {
        int[][] solution = new int[itemCount+1][maxCapacity+1];

        for (int item = 0; item <= itemCount; item++) {
            for (int capacity = 0; capacity <= maxCapacity; capacity++) {
                if (item == 0 || capacity == 0) {
                    solution[item][capacity] = 0;
                } else if (weights[item-1] <= capacity) {
                    solution[item][capacity] = Math.max(solution[item-1][capacity], solution[item-1][capacity-weights[item-1]] + values[item-1]);
                } else {
                    solution[item][capacity] = solution[item-1][capacity];
                }
            }
        }
        return solution[itemCount][maxCapacity];
    }
}
