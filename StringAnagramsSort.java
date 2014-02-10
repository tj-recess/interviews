import java.util.*;

public class StringAnagramsSort {
    public static void main(String[] args) {
        System.out.println("Input: " + Arrays.toString(args));
        Arrays.sort(args, new AnagramComparator());
        System.out.println("Output: " + Arrays.toString(args));
    }
}

class AnagramComparator implements Comparator<String> {
    
    public int compare(String s, String t) {
        char[] sChars = s.toCharArray();
        char[] tChars = s.toCharArray();
        Arrays.sort(sChars);
        Arrays.sort(tChars);
        return (new String(sChars)).compareTo(new String(tChars));
    }
}
