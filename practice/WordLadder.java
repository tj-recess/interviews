import java.util.*;

public class WordLadder extends Questions{
    
    public static void main(String[] args) {
        WordLadder ladder = new WordLadder();
        HashSet<String> dict = new HashSet<String>();
        for (int i = 2; i < args.length; i++) {
            dict.add(args[i]);
        }
        System.out.println("Length of ladder = " + ladder.ladderLength(args[0], args[1], dict));
    }


    public int ladderLength(String start, String end, HashSet<String> dict) {
        String dummy = "dummy";
        Queue<String> queue = new LinkedList<String>();
        dict.add(end);
        queue.add(start);
        queue.add(dummy);
        int len = 1;
        while (!queue.isEmpty()) {
            debug("Queue = " + queue.toString());
            String front = queue.remove();
            if (front.equals(end)) {
                return len;
            }
            if (front == dummy) {
                if (queue.size() == 0) {
                    return 0;
                } else {
                    queue.add(dummy);   //append
                    len++;
                }
            } else {
                // Set<String> oneAway = findOneAway(front, dict);
                Set<String> oneAway = getAllOneAway(front);
                for (String str : oneAway) {
                    if (dict.contains(str)) {
                        dict.remove(str);
                        queue.add(str);
                    }
                }
            }
        }
        return 0;
    }
    
    private Set<String> getAllOneAway(String str) {
        Set<String> oneAway = new HashSet<String>();
        for (int i = 0, len = str.length(); i < len; i++) {
            char[] chars = str.toCharArray();
            for (char ch = 'a'; ch <= 'z'; ch++) {
                chars[i] = ch;
                oneAway.add(new String(chars));
            }
        }
        return oneAway;
    }  
  
    private Set<String> findOneAway(String str, HashSet<String> dict) {
        Set<String> oneAway = new HashSet<String>();
        for (Iterator<String> it = dict.iterator(); it.hasNext(); ) {
            String word = it.next();
            if (areOneDiffAway(str, word)) {
                it.remove();
                oneAway.add(word);
            }
        }
        return oneAway;
    }
    
    private boolean areOneDiffAway(String w1, String w2) {
        boolean oneDiffFound = false;
        for (int i = 0, len = w1.length(); i < len; i++) {
            if (w1.charAt(i) != w2.charAt(i)) {
                if (oneDiffFound) {
                    // one diff found already
                    return false;
                } else {
                    oneDiffFound = true; // mark one diff found
                }
            }
        }
        return oneDiffFound;
    }
}
