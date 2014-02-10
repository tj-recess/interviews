import java.util.*;

public class UndirectedGraphTest {
    public static void main(String[] args) {
        UndirectedGraph<Integer> ug = new UndirectedGraph<Integer>();
        int numNodes = Integer.parseInt(args[0]);
        populate(ug, numNodes);
        System.out.println("-----------Undirected----------");
        doGraphOperations(ug, numNodes);
        
        DirectedGraph2<Integer> dg2 = new DirectedGraph2<Integer>();
        populate(dg2, numNodes);
        System.out.println("-----------Directed2----------");
        doGraphOperations(dg2, numNodes);
        
        DirectedGraph<Integer> dg = new DirectedGraph<Integer>();
        populate(dg, numNodes);
        System.out.println("-----------Directed----------");
        doGraphOperations(dg, numNodes);
    }

    public static void populate(Graph<Integer> ug, int numNodes) {
        Random random = new Random();
        for (int i = 0; i < numNodes; i++) {
            ug.addNode(random.nextInt(100));
        }
    }

    public static void doGraphOperations(Graph<Integer> ug, int numNodes) {
        ug.makeRandomConnections();
        System.out.println("Graph looks like:\n" + ug.toString());
        System.out.println("BFS:");
        List<Integer> visitedSequence = ug.doBFS();
        for(int i : visitedSequence) {
            System.out.print(i + "-->");
        }
        System.out.println("NULL");

        Random random = new Random();
        int id1 = random.nextInt(numNodes);
        int id2 = random.nextInt(numNodes);
        System.out.println("Path between id1=" + id1 + " and id2=" + id2 + "?? : " + ug.isReachable(id1, id2));
        System.out.println();
        System.out.println("DFS: " + ug.doDFS());
    }
}
