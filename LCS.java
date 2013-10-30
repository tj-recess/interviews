public class LCS {
    public static void main(String[] args) {
        LCS lcs = new LCS();
        System.out.println("LCS of " + args[0] + " and " + args[1] + " is: " + lcs.findLcs(args[0], args[1]));
    }

    public String findLcs(String s1, String s2) {
        int l1 = s1.length();
        int l2 = s2.length();
        String[][] paths = new String[l1][l2];
        Integer[][] lens = new Integer[l1][l2];
        computeLcs(s1.toCharArray(), l1-1, s2.toCharArray(), l2-1, paths, lens);
        System.out.println("lens: ");
        print(lens);
        System.out.println("paths: ");
        print(paths);
        // complete LCS here
        return null;    //TODO
    }

    private <T> void print(T[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    private Integer computeLcs(char[] s1, int i1, char[] s2, int i2, String[][] paths, Integer[][] lens) {
        System.out.println("DEBUG: s1=" + new String(s1) + ",i1=" + i1 + ",s2=" + new String(s2) + ",i2=" + i2);

        if (i1 < 0 || i2 < 0) {
            return 0;
        }

        if (paths[i1][i2] != null) {
            return lens[i1][i2]; // already computed this step, which means all subproblems are solved already.
        }

        if (s1[i1] == s2[i2]) {
            int diagLen = computeLcs(s1, i1-1, s2, i2-1, paths, lens);
            paths[i1][i2] = "\\";   // will print '\'
            lens[i1][i2] = diagLen + 1;
        } else {    // chars don't match
            // compute both up and left and then update our position with the maximum
            Integer leftLen = computeLcs(s1, i1-1, s2, i2, paths, lens);
            Integer topLen = computeLcs(s1, i1, s2, i2-1, paths, lens);
            if (leftLen < topLen) {
                lens[i1][i2] = topLen;
                paths[i1][i2] = "|";
            } else {
                lens[i1][i2] = leftLen;
                paths[i1][i2] = "-";
            }
        }
        return lens[i1][i2];
    }

    private String max(String s1, String s2) {
        if (s1.length() < s2.length()) {
            return s2;
        }
        return s1;
    }
}
