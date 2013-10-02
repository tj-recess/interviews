import java.util.*;

public class HeapTest {
    
    public static void main(String[] args) {
        int numNodes = Integer.parseInt(args[0]);
        Heap<Integer> heap = new Heap<Integer>();
        populate(heap, numNodes);
        System.out.println(heap.toString());
    }

    private static void populate(Heap<Integer> heap, int numNodes) {
        Random rand = new Random();
        for (int i = 0; i < numNodes; i++) {
            heap.add(rand.nextInt(100));
        }
    }
}
