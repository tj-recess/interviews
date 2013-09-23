import java.util.*;

public interface GraphNode<T> {
    T getData();
    Map<GraphNode<T>, Integer> getNeighbors();
    void addNeighbor(GraphNode<T> node, int distance);
    int getDistanceFromNeighbor(GraphNode<T> node);
    void removeNeighbor(GraphNode<T> node);
}
