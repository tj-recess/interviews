import java.util.*;

public class Sets {
    public static void main(String[] args) {
        Sets sets = new Sets();
        List<Integer> set = new ArrayList<Integer>();
        sets.populate(set, Integer.parseInt(args[0]));
        List<List<Integer>> allSubs = sets.findAllSubsets(set);
        System.out.println("All subsets of set: " + set + " are:");
        for(List<Integer> oneSet : allSubs) {
            System.out.println(oneSet);
        }
        System.out.println("Total number of subsets found: " + allSubs.size());

        System.out.println("------------------------------------------------");

        List<List<Integer>> allSubs2 = sets.findAllSubsetsBinary(set);
        System.out.println("All subsets of set: " + set + " are:");
        for(List<Integer> oneSet : allSubs2) {
            System.out.println(oneSet);
        }
        System.out.println("Total number of subsets found: " + allSubs2.size());
    }

    public List<List<Integer>> findAllSubsetsBinary(List<Integer> set) {
        int digits = set.size();    // if set has 3 elements consider only 3 digits of binary string
        int max = (int)Math.pow(2, set.size());
        List<List<Integer>> allSets = new ArrayList<List<Integer>>();

        for (int i = 0; i < max; i++) {
            List<Integer> oneSet = new ArrayList<Integer>();
            for (int j = 0; j < digits; j++) {
                boolean on = ((i & (1 << j)) != 0);
                // System.out.println("DEBUG: i=" + i + ", j=" + j + ", on=" + on);
                if (on) {
                    // take the bits which are ON
                    oneSet.add(set.get(j));
                }
            }
            allSets.add(oneSet);
        }
        return allSets;
    }

    public List<List<Integer>> findAllSubsets(List<Integer> set) {
        List<List<Integer>> allSubs = new ArrayList<List<Integer>>();
        // base case
        if (set.size() == 2) {
            List<Integer> comboSet = new ArrayList<Integer>();
            for (Integer i : set) {
                List<Integer> newSet = new ArrayList<Integer>();
                newSet.add(i);
                allSubs.add(newSet);
                comboSet.add(i);
            }
            allSubs.add(comboSet);
            allSubs.add(new ArrayList<Integer>());    //empty set
        } else {
            int size = set.size();
            int lastInSet = set.get(size - 1);
            // all subsets = lastInSet added to each subset of set(size() - 1)
            List<Integer> smallerSet = new ArrayList<Integer>();
            for (int i = 0; i < size - 1; i++) {
                smallerSet.add(set.get(i));
            }
            List<List<Integer>> subsets = findAllSubsets(smallerSet);
            allSubs.addAll(subsets);
            allSubs.addAll(addElementToAll(lastInSet, subsets));
        }
        return allSubs;
    }

    private List<List<Integer>> addElementToAll(int num, List<List<Integer>> sets) {
        List<List<Integer>> allSets = new ArrayList<List<Integer>>();
        for (List<Integer> set : sets) {
            List<Integer> clone = new ArrayList<Integer>();
            clone.addAll(set);
            clone.add(num);
            allSets.add(clone);
        }
        return allSets;
    }


    private void populate(List<Integer> set, int size) {
        Random r = new Random();
        for (int i = 0; i < size; i++) {
            set.add(r.nextInt(100));
        }
    }
}
