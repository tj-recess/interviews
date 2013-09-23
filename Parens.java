import java.util.*;

public class Parens {
    public static void main(String[] args) {
        int num = Integer.parseInt(args[0]);
        Parens p = new Parens();
        System.out.println("All combinations for " + num + " set of parenthesis are: " +  p.generateCombinations(num));
    }

    public List<String> generateCombinations(int num) {
        String parens = "";
        for (int i = 0; i < num; i++) {
            parens += "()";
        }

        return generateCombinations(parens);
    }

    private List<String> generateCombinations(String parens) {
        int len = parens.length();
        if (len % 2 != 0) {
            throw new IllegalArgumentException("Invalid set of parens: " + parens);
        }
        List<String> allCombis = new ArrayList<String>();
        if (len == 0) {
            allCombis.add("");
            return allCombis;
        }
        if (len == 2) {
            allCombis.add("()");
            return allCombis;
        }

        String first = parens.substring(0, 2);
        String remainder = parens.substring(2);

        List<String> remainderCombis = generateCombinations(remainder);
        for (String oneCombi : remainderCombis) {
            allCombis.addAll(insertFirstIntoRest(first, oneCombi));
        }
        return allCombis;
    }

    private List<String> insertFirstIntoRest(String first, String rest) {
        List<String> combis = new ArrayList<String>();
        for(int i = 0; i < rest.length(); i++) {
            if (i > 0 && rest.charAt(i-1) == ')') {
                break;
            }
            combis.add(rest.substring(0, i) + first + rest.substring(i));
        }
        return combis;
    }
}
