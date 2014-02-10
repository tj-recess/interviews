import java.util.*;

public class MinWindowSubstring extends Questions {
    public static void main(String[] args) {
        MinWindowSubstring mws = new MinWindowSubstring();
        print(mws.minWindow(args[0], args[1]));
    }    
    public String minWindow(String S, String T) {
        HashMap<Character, Integer> needed = new HashMap<Character, Integer>();
        HashMap<Character, Integer> found = new HashMap<Character, Integer>();
        // prepare needed chars map
        for (int i = 0, len = T.length(); i < len; i++) {
            char ch = T.charAt(i);
            increment(needed, ch);
        }
        int minStart = -1, minEnd = -1;
        int minLen = Integer.MAX_VALUE;
        int count = 0;
        debug("needed = " + needed);
        for (int start = 0, end = 0, len = S.length(); end < len; end++) {
            debug("found = " + found);
            debug("start = " + start + ", end = " + end);
            Character ch = S.charAt(end);
            if (!needed.containsKey(ch)) {
                continue;
            }
            increment(found, ch);
            if (needed.get(ch) >= found.get(ch)) {
                count++;
            }
            if (count == T.length()) {
                // move start to as right as possible
                while(true) {
                    char ch2 = S.charAt(start);
                    if (!needed.containsKey(ch2)) {
                        start++;
                        continue;
                    }
                    if (found.get(ch2) > needed.get(ch2)) {
                        start++;
                        decrement(found, ch2);
                    } else {
                        break;
                    }
                }
                int curLen = end-start+1;
                if (curLen < minLen) {
                    minLen = curLen;
                    minStart = start;
                    minEnd = end;
                    debug("minStart = " + minStart + ", minEnd = " + minEnd);
                }
            }
        }
        debug("found = " + found);
        if (count == T.length()) {
            debug("minStart = " + minStart + ", minEnd = " + minEnd);
            return S.substring(minStart, minEnd+1);
        }
        return "";
    }
    
    private void increment(Map<Character,Integer> map, Character ch) {
        if (map.containsKey(ch)) {
            map.put(ch, map.get(ch)+1);
        } else {
            map.put(ch, 1);
        }
    }
    
    private void decrement(Map<Character,Integer> map, Character ch) {
        if (map.containsKey(ch)) {
            map.put(ch, map.get(ch)-1);
        }
    }
}
