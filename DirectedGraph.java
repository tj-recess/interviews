import java.util.*;

public class DirectedGraph<T> implements Graph<T>{
  
    protected List<Node> nodes = new ArrayList<Node>();
    protected int nextId=0;

    public void addNode(T data) {
        nodes.add(new Node(getNextId(), data));
    }

    protected int getNextId() {
        return nextId++;
    }

    protected class Node {
        int id;
        T data;
        Map<Node, Integer> neighbors = new HashMap<Node, Integer>();

        public Node(int id, T data) {
            this.id = id;
            this.data = data;
        }

        public T getData() {
            return this.data;
        }

        public Set<Node> getNeighbors() {
            return this.neighbors.keySet();
        }

        public void addNeighbor(Node node, int distance) {
            this.neighbors.put(node, distance);
        }

        public int getDistanceFromNeighbor(Node node) {
            Integer distance = neighbors.get(node);
            if(distance != null) {
                return distance.intValue();
            } else {
                throw new IllegalArgumentException("Node " + node + " not connected.");
            }
        }

        public void removeNeighbor(Node node) {
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
        List<Node> visited = new ArrayList<Node>();
        LinkedList<Node> queue = new LinkedList<Node>();
        for (Node node : nodes) {
            doBFS(node, visited, queue);
        }
        List<T> visitedData = new ArrayList<T>();
        for(Node node : visited) {
            visitedData.add(node.getData());
        }
        return visitedData;
    }

    public void doBFS(Node node, List<Node> visited, LinkedList<Node> queue) {
        if(!visited.contains(node)) {
            queue.addLast(node);
        }
        while (!queue.isEmpty()) {
            Node nodeFromQueue = queue.removeFirst();
            visited.add(nodeFromQueue);
            for(Node neighbor : nodeFromQueue.getNeighbors()) {
                if (!visited.contains(neighbor) && !queue.contains(neighbor)) {
                    queue.addLast(neighbor);
                }
            }
        }
    }

    public void makeRandomConnections() {
        Random random = new Random();
        int total = nodes.size();
        for(Node node : nodes) {
            int numConnections = random.nextInt(total/2 + 1);
            for(int i = 0; i < numConnections; i++) {
                Node peer = nodes.get(random.nextInt(total));
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
        return false;
    }
}
