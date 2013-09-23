import java.util.*;

public class BinaryTree<T extends Comparable<T>> {
    private class Node {
        T data;
        Node left = null;
        Node right = null;

        public Node(T data) {
            this.data = data;
        }
    }

    private Node root;
    private Random random = new Random();

    public boolean isBalanced() {
        return isBalanced(root);
    }

/*
    public List<List<T>> getAllPaths(int sum) {
        List<List<T>> result = new ArrayList<List<T>>();
        List<T> myPath = new ArrayList<T>();
        getAllPaths(this.root, sum, myPath, result);
        return result;
    }

    private void getAllPaths(Node node, int sum, List<T> myPath, List<List<T>> result) {
        myPath.add(node.data);
        int localSum = 0;
        for(int i = myPath.size() - 1; i >= 0; i++) {
            localSum = localSum + myPath.get(i);
            if (sum == localSum) {
                List<
    }
*/
    public T findCommonAncestor(T node1, T node2) {
        return findCommonAncestor(this.root, node1, node2);
    }

    private T findCommonAncestor(Node root, T node1, T node2) {
        if (root == null) {
            return null;
        }

        if(covers(root.left, node1) && covers(root.left, node2)) {
            return findCommonAncestor(root.left, node1, node2);
        } else if (covers(root.right, node1) && covers(root.right, node2)) {
            return findCommonAncestor(root.right, node1, node2);
        }
        return root.data;
    }

    private boolean covers(Node node, T data) {
        if (node == null) {
            return false;
        }

        if (node.data.equals(data)) {
            return true;
        }

        return covers(node.left, data) || covers(node.right, data);
    }

    private boolean isBalanced(Node node) {
        if (node == null) { return true; }
        if (Math.abs(height(node.left) - height(node.right)) <= 1) {
            return isBalanced(node.right) && isBalanced(node.left);
        } else {
            return false;
        }
    }

    private int height(Node node) {
        if(node == null) { return 0; }
        return Math.max(height(node.left), height(node.right)) + 1;
    }

    public void add(T data) {
        Node newNode = new Node(data);
        if(root == null) {
            System.out.println("Root added: " + data);
            root = newNode;
        } else {
            Node cur = root;
            Node prvs = null;
            boolean lastMove = false;   //right
            while(cur != null) {
                prvs = cur;
                if(random.nextBoolean()) {
                    //System.out.println("DEBUG: Moving Left...");
                    lastMove = true;
                    cur = cur.left;
                } else {
                    //System.out.println("DEBUG: Moving Right...");
                    lastMove = false;
                    cur = cur.right;
                }
            }
            if(lastMove) {
                //System.out.println("DEBUG: Adding to left: " + data);
                prvs.left = newNode;
            } else {
                //System.out.println("DEBUG: Adding to right: " + data);
                prvs.right = newNode;
            }
        }
    }


    public String inorder() {
        StringBuffer sb = new StringBuffer();
        inorder(this.root, sb);
        return sb.toString();
    }

    public String preorder() {
        StringBuffer sb = new StringBuffer();
        preorder(this.root, sb);
        return sb.toString();
    }
    
    private void preorder(Node cur, StringBuffer sb) {
        if (cur == null) return;
        sb.append(cur.data + " ");
        preorder(cur.left,sb);
        preorder(cur.right, sb);
    }
    
    private void inorder(Node cur, StringBuffer sb) {
        if (cur == null) return;
        inorder(cur.left,sb);
        sb.append(cur.data + " ");
        inorder(cur.right, sb);
    }

    public Map<Integer, List<T>> traversePerLevel() {
        Map<Integer, List<T>> nodesPerLevel = new HashMap<Integer, List<T>>();
        traversePerLevel(this.root, 0, nodesPerLevel);
        return nodesPerLevel;
    }

    private void traversePerLevel(Node node, int level, Map<Integer, List<T>> nodesPerLevel) {
        if(node == null) {  return; }
        List<T> curLevelList = nodesPerLevel.get(level);
        if(curLevelList == null) {
            curLevelList = new LinkedList<T>();
            nodesPerLevel.put(level, curLevelList);
        }
        curLevelList.add(node.data);
        traversePerLevel(node.left, level + 1, nodesPerLevel);
        traversePerLevel(node.right, level + 1, nodesPerLevel);
    }

    public void createTreeWithMinHeight(T[] values) {
        this.root = new Node(null);
        this.createTreeWithMinHeight(values, 0, values.length - 1, this.root);
    }

    public void createTreeWithMinHeight(T[] values, int start, int end, Node root) {
        if (root == null) { throw new IllegalStateException(); }
        
        int mid = start + (end - start) / 2;
        root.data = values[mid];

        //System.out.println("DEBUG: start=" + start + ", end=" + end + ", mid=" + mid);
        //System.out.println("DEBUG: root.data=" + root.data);


        if(mid - start > 0) {
            root.left = new Node(null);
            createTreeWithMinHeight(values, start, mid - 1, root.left);
        }

        if(end - mid > 0) {
            root.right = new Node(null);
            createTreeWithMinHeight(values, mid + 1, end, root.right);
        }
    }
}
