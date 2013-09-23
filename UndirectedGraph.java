import java.util.*;

public class UndirectedGraph<T> implements Graph<T> {
  
    protected List<Node> nodes = new ArrayList<Node>();
    protected int nextId=0;

    public void addNode(T data) {
        nodes.add(new Node(getNextId(), data));
    }

    protected int getNextId() {
        return nextId++;
    }

    protected class Node implements GraphNode<T> {
        int id;
        T data;
        Map<GraphNode<T>, Integer> neighbors = new HashMap<GraphNode<T>, Integer>();

        public Node(int id, T data) {
            this.id = id;
            this.data = data;
        }

        public T getData() {
            return this.data;
        }

        public Map<GraphNode<T>, Integer> getNeighbors() {
            return this.neighbors;
        }

        public void addNeighbor(GraphNode<T> node, int distance) {
            this.getNeighbors().put(node, distance);
            node.getNeighbors().put(this, distance);
        }

        public int getDistanceFromNeighbor(GraphNode<T> node) {
            Integer distance = neighbors.get(node);
            if(distance != null) {
                return distance.intValue();
            } else {
                throw new IllegalArgumentException("Node " + node + " not connected.");
            }
        }

        public void removeNeighbor(GraphNode<T> node) {
            this.neighbors.remove(node);
        }

        public String toString() {
            return "[Id=" + this.id + ", Data=" + this.data.toString() + "]";
        }
    }

    public List<T> doDFS() {
        //TODO:
        return null;
    }

    public List<T> doBFS() {
        List<GraphNode<T>> visited = new ArrayList<GraphNode<T>>();
        for (GraphNode<T> node : nodes) {
            doBFS(node, visited);
        }
        List<T> visitedData = new ArrayList<T>();
        for(GraphNode<T> node : visited) {
            visitedData.add(node.getData());
        }
        return visitedData;
    }

    public void doBFS(GraphNode<T> node, List<GraphNode<T>> visited) {
        LinkedList<GraphNode<T>> queue = new LinkedList<GraphNode<T>>();
        if(!visited.contains(node)) {
            queue.addLast(node);
        }
        while (!queue.isEmpty()) {
            GraphNode<T> nodeFromQueue = queue.removeFirst();
            visited.add(nodeFromQueue);
            for(GraphNode<T> neighbor : nodeFromQueue.getNeighbors().keySet()) {
                if (!visited.contains(neighbor) && !queue.contains(neighbor)) {
                    queue.addLast(neighbor);
                }
            }
        }
    }

    public void makeRandomConnections() {
        System.out.println("Inside UG's random conn");
        Random random = new Random();
        int total = nodes.size();
        for(GraphNode<T> node : nodes) {
            int numConnections = random.nextInt(total/2 + 1);
            for(int i = 0; i < numConnections; i++) {
                GraphNode<T> peer = nodes.get(random.nextInt(total));
                if(peer == node) {
                    i--;
                    continue;
                }
                node.addNeighbor(peer, random.nextInt(100));
            }
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(Node node : nodes) {
            sb.append("[Node = " + node.toString() + ", neighbors = " + node.neighbors.toString() + "]\n");
        }
        return sb.toString();
    }

    public boolean isReachable(int id1, int id2) {
        GraphNode<T> node1 = nodes.get(id1);
        GraphNode<T> node2 = nodes.get(id2);
        List<GraphNode<T>> visited = new ArrayList<GraphNode<T>>();
        doBFS(node1, visited);
        return visited.contains(node2);
    }
}
