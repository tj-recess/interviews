import java.util.*;

public class DirectedGraph2<T> extends UndirectedGraph<T> implements Graph<T> {

    public void addNode(T data) {
        nodes.add(new Node(getNextId(), data));
    }
    
    protected class Node extends UndirectedGraph<T>.Node implements GraphNode<T> {
        public Node(int id, T data) {
            super(id, data);
        }
        
        public void addNeighbor(GraphNode<T> node, int distance) {
            this.getNeighbors().put(node, distance);
        }
    }
}
