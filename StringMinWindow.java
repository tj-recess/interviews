import java.util.*;

public class StringMinWindow {
    private HashMap<Character, Integer> runningCountMap = new HashMap<Character, Integer>();
    private HashMap<Character, Integer> totalCountMap = new HashMap<Character, Integer>();
    public static void main(String[] args) {
        String source = args[0];
        String target = args[1];

        StringMinWindow smw = new StringMinWindow();
        System.out.println("source = " + source);
        System.out.println("target = " + target);
        System.out.println("Min window = " + smw.findMinWindow(source, target));
    }

    public String findMinWindow(String source, String target) {
        populateMaps(target);
        int startLoc = -1;
        int endLoc = -1;
        int minStartLoc = -1;
        int minEndLoc = -1;
        int windowCounter = 0;
        int minWindowLength = Integer.MAX_VALUE;
        for (int i = 0; i < source.length(); i++) {
            endLoc = i;
            
            Character ch = source.charAt(i);
            // System.out.println("DEBUG: ch = " + ch);
            if (!isTargetChar(ch)) {
                continue;
            }
            
            if (startLoc == -1) {   startLoc = endLoc;  }
            
            incRunningCount(ch);

            // increase total count if applicable
            if (shouldIncrementWindowCounter(ch)) {
                windowCounter++;
            }

            if (windowCounter == target.length()) {
                // this means we should move start pointer ahead as our window is complete
                // we'll keep moving start pointer as long as don't violate counter constraint 
                for (int j = startLoc; j <= endLoc; j++) {
                    Character startChar = source.charAt(j);
                    if (isOkToMoveStartPointer(startChar)) {
                        startLoc++;
                        decrementRunningCount(startChar);
                    } else {
                       break;
                   }
                }
                int windowLength = endLoc - startLoc + 1;
                if (windowLength < minWindowLength) {
                    minStartLoc = startLoc;
                    minEndLoc = endLoc;
                    minWindowLength = windowLength;
                }
            }
            // System.out.println("DEBUG: TotalCountMap = " + totalCountMap);
            // System.out.println("DEBUG: RunningCountMap = " + runningCountMap);
            // System.out.println("DEBUG: Start Location = " + startLoc);
            // System.out.println("DEBUG: End Location = " + endLoc);
            // System.out.println("DEBUG: -------------------------------------");
        }
        System.out.println("Start Location = " + minStartLoc);
        System.out.println("End Location = " + minEndLoc);
        System.out.println("MinWindowLength = " + minWindowLength);
        return source.substring(minStartLoc, minEndLoc + 1);
    }

    private boolean isOkToMoveStartPointer(Character ch) {
        // ok to move under following conditions
        // 1. either ch is not in targetMap
        // 2. or running count for ch is strictly > total count
        if (!isTargetChar(ch)) {    return true;   }
        return runningCountMap.get(ch) > totalCountMap.get(ch);
    }

    private boolean shouldIncrementWindowCounter(Character ch) {
        if (!isTargetChar(ch)) {    return false;   }
        return runningCountMap.get(ch) <= totalCountMap.get(ch);
    }

    private void incRunningCount(Character ch) {
        runningCountMap.put(ch, runningCountMap.get(ch) + 1);
    }

    private void decrementRunningCount(Character ch) {
        Integer count = runningCountMap.get(ch);
        if (count != null) {
            runningCountMap.put(ch, count-1);
        }
    }

    private boolean isTargetChar(Character ch) {
        return totalCountMap.containsKey(ch);
    }

    private void populateMaps(String target) { 
        int targetLen = target.length();
        for (int i = 0; i < targetLen; i++) {
            Character ch = target.charAt(i);
            runningCountMap.put(ch, 0);
            Integer targetCount = totalCountMap.get(ch);
            if (targetCount == null) {
                targetCount = 1;
            } else {
                targetCount++;
            }
            totalCountMap.put(ch, targetCount);
        }
    }
}
