import java.util.*;

public class LinkedListTest {
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<Integer>();
        for(String arg : args) {
            list.append(Integer.parseInt(arg));
        }
        System.out.println("List: " + list.toString());
        System.out.println("Point of loop beginning = " + list.findLoopBegin());
        
        System.out.println("After removing dups: " + list.copy().removeDups().toString());
        int lastParam = Integer.parseInt(args[args.length - 1]);
        System.out.println(lastParam + " from last : " + list.nthToLast(lastParam));

        //generate other linked list
        LinkedList<Integer> otherList = new LinkedList<Integer>();
        Random rand = new Random();
        int len = rand.nextInt(args.length);
        for(int i = 0; i < len; i++) {
            otherList.append(Integer.parseInt(args[rand.nextInt(args.length)]));
        }
        System.out.println("Other List: " + otherList.toString());
        System.out.println("Sum of 2 lists: " + list.sumDigits(otherList).toString());
        
        // create a circular list out of arguments
        LinkedList<Integer> circular = list.makeCircular();
        System.out.println("Circular list = " + circular.toString());
        System.out.println("Point of loop beginning = " + circular.findLoopBegin());
    }
}

