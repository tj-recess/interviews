import java.util.*;

public class Change {
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        Change c = new Change();
        System.out.println("Number of ways in which " + n + " cents can be represented are: " + c.numWays(n));
        System.out.println("Number of ways in which " + n + " cents can be represented are: " + makeChange(n, 25));
    }

    enum Den { P, N, D, Q; };   // Penny, Nickel, Dime, Quarter
    
    public int numWays(int n) {
        if (n >= 0 && n <= 4) {
            return 1;
        }
        return numWays(n, Den.Q)
            +  numWays(n, Den.D)
            +  numWays(n, Den.N)
            +  numWays(n, Den.P);
    }

    private int numWays(int n, Den denom) {
        switch (denom) {
            case Q:
                if (n >= 25) {
                    return 1*numWays(n-25);
                }
                break;
            case D:
                if (n >= 10) {
                    return 1*numWays(n-10);
                }
                break;
            case N:
                if (n >=5) {
                    return 1*numWays(n-5);
                }
                break;
            case P:
                if (n >=1) {
                    return 1;
                }
                break;
        }
        return 0;
    }


    public static int makeChange(int n, int denom) {
        int next_denom = 0;
        switch (denom) {
        case 25:
            next_denom = 10;
            break;
        case 10:
            next_denom = 5;
            break;
         case 5:
             next_denom = 1;
             break;
         case 1:
            return 1;
        }
        int ways = 0;
        for (int i = 0; i * denom <= n; i++) {
            ways += makeChange(n - i * denom, next_denom);
        }
        return ways;
     }
}
