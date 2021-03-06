import java.util.*;

public class MultiplyStrings {

    public static void main(String[] args) {
        MultiplyStrings ms = new MultiplyStrings();
        System.out.println("Result = " + ms.multiply(args[0], args[1]));
    }

    public String multiply(String num1, String num2) {

        int len1 = num1.length();

        int len2 = num2.length();

        List<String> toSum = new ArrayList<String>();

        int maxLen = 0;

        

        for (int i = 0; i < len1; i++) {

            int x = Character.getNumericValue(num1.charAt(i));

            int carry = 0;

            StringBuffer total = new StringBuffer();

            for (int j = len2-1; j >= 0; j--) {

                int y = Character.getNumericValue(num2.charAt(j));

                int interim = x*y + carry;

                int value = interim % 10;

                carry = interim / 10;
                total.append(value);

            }

            if (carry > 0) {
                total.append(carry);
            }

            int numZero = len1 - 1 - i;

            for (int k = 0; k < numZero; k++) {

                total.insert(0, "0");

            }

            if (total.length() > maxLen) {

                maxLen = total.length();

            }

            toSum.add(total.toString());

        }

        return sum(toSum, maxLen);

    }

    

    private String sum(List<String> toSum, int maxLen) {

        StringBuffer res = new StringBuffer();

        int carry = 0;

        

        for (int i = 0; i < maxLen; i++) {

            int total = carry;

            for (String s : toSum) {

                if (i < s.length()) {

                    total += Character.getNumericValue(s.charAt(i));

                }

            }

            int value = total % 10;

            res.append(value);

            carry = total / 10;

        }

        String finalRes = res.reverse().toString();
        if (carry > 0) {
            finalRes = carry + finalRes;
        }

        return (finalRes.matches("^0*$")) ? "0" : finalRes;

    }

}
