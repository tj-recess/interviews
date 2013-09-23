import java.util.*;

public class StringQuestions {
    
    public static void main(String[] args) {
        StringQuestions sq = new StringQuestions();
        for(String s : args) {
            System.out.println("All chars unique in " + s + " : " + sq.areAllUnique(s));
            System.out.println("Reverse of " + s + " is : " + sq.reverse(s.toCharArray()));
            System.out.println("Non-duplicates of " + s + " are : " + sq.removeDups(s.toCharArray()));
            System.out.println("All perms of " + s + " are: " + sq.allPerms(s));
        }
    }

    /**
    * Generates all possible permutations of a given string.
    */
    public List<String> allPerms(String str) {
        List<String> permutations = new ArrayList<String>();
        int length = str.length();
        if(str.equals("")) {
            permutations.add("");
            return permutations;
        }

        char first = str.charAt(0);
        String remainder = str.substring(1);
        List<String> words = allPerms(remainder);
        for (String word : words) {
            permutations.addAll(insertCharAt(first, word));
        }
        return permutations;
    }

    private List<String> insertCharAt(char ch, String str) {
        List<String> perms = new ArrayList<String>();
        int len = str.length();
        for (int i = 0; i <= len; i++) {
            perms.add(str.substring(0, i) + ch + str.substring(i));
        }
        return perms;
    }

    private List<String> allPerms(char ch, String str) {
        List<String> permutations = new ArrayList<String>();
        int len = str.length();
        if (len == 1) {
            permutations.add(ch + str);
            permutations.add(str + ch);
        } else {
            // add ch as first char of all perms for str - (first char of str)
            List<String> innerPerms = allPerms(str.charAt(0), str.substring(1, len));
            for (String s : innerPerms) {
                permutations.add(ch + s);
            }
        }
        return permutations;
    }

    public boolean areAllUnique(String s) {
        HashMap<Character, Boolean> charCounter = new HashMap<Character, Boolean>();
        for(Character ch : s.toCharArray()) {
            Boolean presence = charCounter.get(ch);
            if(presence == null) {
                charCounter.put(ch, true);
            } else {
                return false;
            }
        }
        return true;
    }

    public String reverse(char[] str) {
        int start = 0;
        int end = str.length - 1;
        for(; start < end; start++, end--) {
            char temp = str[start];
            str[start] = str[end];
            str[end] = temp;
        }
        return new String(str);
    }

    /**
    * Returns the length of char[] for which non-duplicate chars will be found
    */
    public String removeDups(char[] str) {
        int pos = -1;
        for (int i = 0; i < str.length; i++) {
            char current = str[i];
            boolean duplicate = false;
            for(int j = 0; j < i; j++) {
                if (current == str[j]) {
                    duplicate = true;
                    break;
                }
            }
            if(!duplicate) {
                str[++pos] = current;
            }
        }
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i <= pos; i++) {
            sb.append(str[i]);
        }
        return sb.toString();
    }
}
