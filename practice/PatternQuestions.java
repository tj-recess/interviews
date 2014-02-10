import java.util.*;

public class PatternQuestions extends Questions {

    public static void main(String[] args) {
        print("Text: " + args[0]);
        print("Pattern: " + args[1]);
        PatternQuestions q = new PatternQuestions();
        print("Min Window: " + q.findMinWindow(args[0], args[1]));
    }
    
    public String findMinWindow(String text, String pattern) {
        Map<Character, Integer> patternCount = new HashMap<Character, Integer>();
        Map<Character, Integer> hasFound = new HashMap<Character, Integer>();
        int len = text.length();
        int runningCount = 0;
        int start = -1;
        String result = "";
        populatePatternCount(pattern, patternCount);
        debug("patternCount=" + patternCount);
        debug("hasFound=" + hasFound);
        for (int i = 0; i < len; i++) {
            char ch = text.charAt(i);
            debug("RunningCount = " + runningCount + ", pattern.length()=" + pattern.length());
            if (isPatternChar(ch, patternCount)) {
                debug("Pattern char found: " + ch);
                addToMap(ch, hasFound);
                debug("1. hasFound=" + hasFound);

                if (patternCount.get(ch) >= hasFound.get(ch)) {
                    runningCount++;
                }

                if (start == -1) {
                    start = i;
                }


                if (runningCount >= pattern.length()) {
                    // found a window, now move start to right
                    debug("--------------->            found a window; start = " + start + ", end = " + i);
                    ch = pattern.charAt(start);
                    while (!isPatternChar(ch, patternCount) || (hasFound.get(ch) > patternCount.get(ch))) {
                        removeFromMap(ch, hasFound);
                        start++;
                        ch = pattern.charAt(start);
                        debug("start = " + start + ", charAt(start) = " + ch);
                    }
                    debug("2. hasFound=" + hasFound + ", start = " + start + ", end = " + i);

                    String localResult = text.substring(start, i+1);
                    debug("localResult = " + localResult);
                    if ((localResult.length() < result.length())
                        || (result.equals(""))) {
                        
                        result = localResult;
                    }
                }
            }
        }
        return result;
    }

    private void populatePatternCount(String str, Map<Character, Integer> map) {
        int len = str.length();
        for (int i = 0; i < len; i++) {
            addToMap(str.charAt(i), map);
        }
    }

    private void addToMap(char ch, Map<Character, Integer> map) {
        Integer value = map.get(ch);
        value = value == null ? 1 : value+1;
        map.put(ch, value);
    }

    private void removeFromMap(char ch, Map<Character, Integer> map) {
        if (map.containsKey(ch)) {
            map.put(ch, map.get(ch) - 1);
        }
    }

    private boolean isPatternChar(char ch, Map<Character, Integer> map) {
        return map.containsKey(ch);
    }
}
