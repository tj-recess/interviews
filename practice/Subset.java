import java.util.*;

public class Subset {
    public List<List<String>> findAllSubsets(List<String> set) {
        return getSubsets(set, 0);
    }

    private List<List<String>> getSubsets(List<String> set, int index) {
        if (index == set.size()) {
            List<List<String>> allSets = new ArrayList<List<String>>();
            allSets.add(new ArrayList<String>());
            return allSets;
        }

        List<List<String>> allSubsets = getSubsets(set, index+1);
        String s = set.get(index);
        int total = allSubsets.size();
        for (int i = 0; i < total; i++) { 
            List<String> newSet = new ArrayList<String>();
            newSet.addAll(allSubsets.get(i));
            newSet.add(s);
            allSubsets.add(newSet);
        }
        return allSubsets;
    }

    public static void main(String[] args) {
        int num = Integer.parseInt(args[0]);
        List<String> set = new ArrayList<String>();
        for (int i = 1; i <= num; i++) {
            set.add(args[i]);
        }
        Subset question = new Subset();
        System.out.println("All subsets: " + question.findAllSubsets(set));
    }
}
