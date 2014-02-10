import java.util.*;

public class ArrayQuestions extends Questions {
    
    public int getK(int[] A, int[] B, int k) {
        if (k > (A.length + B.length)) {
            throw new IllegalArgumentException("K is bigger than arrays combined");
        }
        int count = 0; int startA = 0; int startB = 0, retVal = -1;
        while (k > 0) {
            int a = k/2;
            int b = k - a;
            if (a > 0) {    --a;    }
            if (b > 0) {    --b;    }

            //debug("k = " + k + ", a = " + a + ", b = " + b + ", startA = " + startA + ", startB = " + startB);

            if ((A.length-1) < (startA+a)) {
                return B[startB+k-1];
            }
            if ((B.length-1) < (startB+b)) {
                return A[startA+k-1];
            }

            if (A[startA+a] > B[startB+b]) {
                retVal = B[startB+b];
                k -= (b+1);
                startB += (b+1);
            } else {
                retVal = A[startA+a];
                k -= (a+1);
                startA += (a+1);
            }
        }
        return retVal;
    }

    public static void main(String[] args) {
        ArrayQuestions aq = new ArrayQuestions();
        int[] A;
        int[] B;
        A = aq.getRandomArray(Integer.parseInt(args[0]));
        Arrays.sort(A);
        B = aq.getRandomArray(Integer.parseInt(args[1]));
        Arrays.sort(B);
        int k = Integer.parseInt(args[2]);
        print("A = " + Arrays.toString(A));
        print("B = " + Arrays.toString(B));
        print(k + "th smallest element in union of A and B is : " + aq.getK(A, B, k));
    }

    private int[] getRandomArray(int size) {
        int[] arr = new int[size];
        Random r = new Random();
        for (int i = 0; i < size; i++) {
            arr[i] = r.nextInt(100);
        }
        return arr;
    }
}
