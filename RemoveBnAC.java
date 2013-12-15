public class RemoveBnAC {
    public static void main(String[] args) {
        RemoveBnAC rabc = new RemoveBnAC();
        for (String arg : args) {
            System.out.println("Removing b and ac from " + arg + " results in: " + rabc.removeBnAC(arg.toCharArray()));
        }
    }

    public String removeBnAC(char[] s) {
        int j = -1;
        boolean foundA = false;
        for (int i = 0; i < s.length; i++) {
            switch (s[i]) {
                case 'a':
                    foundA = true;
                    break;
                default:
                    // must move to jth position, if this char is not 'c'
                    if (foundA) {
                        foundA = false;
                        // check if this char is 'c'
                        if (s[i] != 'c') {
                            // first copy 'a'
                            moveToJ(s, ++j, 'a');
                        } else {
                            continue;
                        }
                    } 
                    if (s[i] == 'b') {
                        foundA = false;
                        continue;
                    }
                    moveToJ(s, ++j, s[i]);
            }
            //System.out.println("DEBUG: i=" + i + ", j=" + j + ", s[i]=" + s[i]);
        }
        if (foundA) {
            moveToJ(s, ++j, 'a');
        }
        if (j == -1) return "";
        return new String(s, 0, j+1);
    }

    private void moveToJ(char[] s, int moveTo, char ch) {
        s[moveTo] = ch;
    }
}
