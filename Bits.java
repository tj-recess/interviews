public class Bits {
    public static void main(String[] args) {
        for (String arg : args) {
            Bits bits = new Bits();
            int value = Integer.parseInt(arg);
            System.out.println("Binary of " + arg + " is : " +  bits.intToBinary(value));
            int next = bits.getNextWithSame1s(value);
            System.out.println( arg + "'s next number with same 1s is: " + next + 
                    ". Binary: " + Integer.toBinaryString(next));
        }
    }

    public String intToBinary(int num) {
        String binary = "";
        while (num > 0) {
            int remainder = num % 2;
            binary = remainder + binary;
            num = num >> 1;
        }
        return binary;
    }

    public void showInteger(String[] args) {
        for (String arg : args) {
            System.out.println("Integer rep of " + arg + " is: " + Integer.parseInt(arg, 2));
        }
    }

    public int getNextWithSame1s(int num) {
        int i = 0;
        for (; !getBit(num, i) && (i < 32); i++);
        // i is now 1;
        //System.out.println("DEBUG: first 1 location: " + i);
        int countOnes = 1;
        i++;    // start from next bit
        for (; getBit(num, i) && (i < 32); i++) {
            countOnes++;
        }
        //System.out.println("DEBUG: first 0 location after 1: " + i);
        //System.out.println("DEBUG: number of ones: " + countOnes);

        // i is now pointing at first 0 after 1s
        // set this bit to 1
        num = setBit(num, true, i);
        // set previous bit to 0
        i--;
        num = setBit(num, false, i);
        i--;

        // now set all 0s except when digits left = ( countOnes - 1 )
        --countOnes;
        for (; i > (countOnes - 1); i--) {
            num = setBit(num, false, i);
        }

        // set remaining bits to 1
        for (; i >= 0; i--) {
            num = setBit(num, true, i);
        }

        return num;
    }

    private int setBit(int num, boolean isOne, int index) {
        if (isOne) {
            //System.out.println("DEBUG: Setting bit " + index + " to 1");
            return ( num | (1 << index));
        } else {
            //System.out.println("DEBUG: Setting bit " + index + " to 0");
            return ( num & ( ~0 ^ (1 << index)));
        }
    }

    private boolean getBit(int num, int index) {
        if ( (num & (1 << index)) == 0) return false;
        return true;
    }
}
