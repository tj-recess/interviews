import java.util.*;

public class Fibo {
    
    private HashMap<Integer, Integer> fiboVals = new HashMap<Integer, Integer>();

    public int findNth(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;

        return findNth(n-1) + findNth(n-2);
    }

    public int findNthMemo(int n) {
        Integer retVal = fiboVals.get(n);
        if (retVal != null) {
            return retVal.intValue();
        }

        if (n == 0) {
            fiboVals.put(0, 0);
            return 0;
        }

        if (n == 1) {
            fiboVals.put(1, 1);
            return 1;
        }

        int nthFibo = findNthMemo(n-1) + findNthMemo(n-2);
        fiboVals.put(n, nthFibo);
        return nthFibo;
    }

    public int findNthIter(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;

        int first = 0;
        int second = 1;

        for (int i = 2; i <= n; i++) {
            int total = first + second;
            first = second;
            second = total;
        }
        return second;
    }

    public static void main(String[] args) {
        Fibo fibo = new Fibo();
        int x = Integer.parseInt(args[0]);
        for (int i = 0; i <= x; i++) {
            System.out.println(i + " fibo number (recursive) is: " + fibo.findNth(i));
        }
        for (int i = 0; i <= x; i++) {
            System.out.println(i + " fibo number (memo) is: " + fibo.findNthMemo(i));
        }
        for (int i = 0; i <= x; i++) {
            System.out.println(i + " fibo number (iterative) is: " + fibo.findNthIter(i));
        }
    }
}
