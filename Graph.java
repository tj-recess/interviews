import java.util.*;

public interface Graph<T> {
    List<T> doBFS();
    List<T> doDFS();
    String toString();
    void makeRandomConnections();
    void addNode(T data);
    boolean isReachable(int id1, int id2);
}
