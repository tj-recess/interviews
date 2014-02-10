import java.util.*;

public class MyLinkedListTest {
    
    public static void main(String[] args) {
        MyLinkedList<Integer> ll = new MyLinkedList<Integer>();
        populate(ll, Integer.parseInt(args[0]));
        System.out.print("List: ");
        ll.print();
        ll.reverse();
        System.out.print("Reversed: ");
        ll.print();
        System.out.print("Reversed again: ");
        ll.reverse();
        ll.print();
    }

    public static void populate(MyLinkedList<Integer> list, int n) {
        Random r = new Random();
        for (int i = 0; i < n; i++) {
            list.add(r.nextInt(100));
        }
    }
}
