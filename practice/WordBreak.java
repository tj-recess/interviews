import java.util.*;

public class WordBreak extends Questions {
    
    public static void main(String[] args) {
        String word = args[0];
        Set<String> dict = new HashSet<String>();
        for (int i = 1, len = args.length; i < len; i++) {
            dict.add(args[i]);
        }

        WordBreak wb = new WordBreak();
        System.out.println("Word = " + word);
        System.out.println("Dict = " + dict);
        System.out.println("is possible: " + wb.wordBreak(word, dict));
    }

    public boolean wordBreak(String s, Set<String> dict) {
        Set<String> notFound = new HashSet<String>();
        return wordBreakHelper(s, dict, notFound);
    }
    
    private boolean wordBreakHelper(String s, Set<String> dict, Set<String> notFound) {
        if (notFound.contains(s)) {
            return false;
        }
        if (dict.contains(s)) {
            return true;
        }
        for (int i = 0, len = s.length(); i < len; i++) {
            String s1 = s.substring(0, i+1);
            if (dict.contains(s1)) {
                if (i+1 == len) {
                    return true;
                }
                String s2 = s.substring(i+1);
                if (wordBreakHelper(s2, dict, notFound)) {
                    return true;
                } else {
                    notFound.add(s2);
                }
            }
        }
        return false;
    }

}
