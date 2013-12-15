import java.util.*;

public class BinaryTree<T extends Comparable<T>> {
    protected class Node {
        T data;
        Node left = null;
        Node right = null;

        public Node(T data) {
            this.data = data;
        }
        
        public void print() {
            System.out.print(data + " ");
        }
    }

    protected Node root;
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

    /**
    * Constructs a binary tree from it's preorder and inorder traversal.
    */
    public BinaryTree<T> construct(List<T> inorder, List<T> preorder) {
        BinaryTree<T> tree = new BinaryTree<T>();
        tree.root = construct(inorder, 0, inorder.size()-1, preorder, 0);
        return tree;
    }

    private Node construct(List<T> inorder, int startI, int endI, List<T> preorder, int pIndex) {
        // find index of this node in inorder and divide list accordingly
        Node node = new Node(preorder.get(pIndex));
        int index = findInList(inorder, startI, endI, node.data);
        if (index != -1) {
            node.left = construct(inorder, 0, index-1, preorder, pIndex+1);
            node.right = construct(inorder, index+1, inorder.size()-1, preorder, pIndex+1);
        }
        return node;
    }

    private int findInList(List<T> list, int start, int end, T data) {
        for (int i = start; i < end; i++) {
            if (list.get(i).equals(data)) {
                return i;
            }
        }
        return -1;
    }

    public List<T> inorderNoStack() {
        List<T> sequence = new ArrayList<T>();

        Node node = root;
        while (node != null) {
            if (node.left == null) {
                //if no left, then print this node and move to right
                sequence.add(node.data);
                node = node.right;
            } else {
                // find predecessor of this node i.e. 
                // right most node in left subtree of this node
                Node temp = node.left;
                while (temp.right != null && temp.right != node) {
                    temp = temp.right;
                }
                // make myself rightMost child of right node
                if (temp.right == null) {
                    temp.right = node;
                    node = node.left;
                } else if (temp.right == node) {
                    // reset the tree
                    temp.right = null;
                    sequence.add(node.data);
                    node = node.right;
                }
            }
        }
        return sequence;
    }


    public String inorder() {
        StringBuffer sb = new StringBuffer();
        inorder(this.root, sb);
        return sb.toString();
    }

    public List<T> inorderIterative() {
        List<T> nodesList = new ArrayList<T>();
        if (root == null) {
            return nodesList;
        }
        // create a stack
        Stack<Node> stack = new Stack<Node>();
        Node node = root;
        // move to extreme left, saving root every time
        while (true) {
            if (node != null) {
                stack.push(node);
                node = node.left;
                continue;
            }
            // now node must be null, so we should pop from stack
            if (stack.empty()) {
                break;
            }
            node = stack.pop();
            nodesList.add(node.data);
            // now move to right of node following same procedure
            node = node.right;
        }
        return nodesList;
    }

    public List<T> preorderIterative() {
        List<T> nodesList =new ArrayList<T>();
        Stack<Node> stack = new Stack<Node>();
        Node node = root;
        while (true) {
            if (node != null) {
                nodesList.add(node.data);
                stack.push(node);
                node = node.left;
                continue;
            }
            if (stack.empty()) {
                break;
            }
            node = stack.pop().right;
        }
        return nodesList;
    }

    public List<T> postorderIterative() {
        List<T> nodesList =new ArrayList<T>();
        Stack<Node> stack = new Stack<Node>();
        Node node = root;
        while (true) {
            if (node != null) {
                if (node.right != null) {
                    stack.push(node.right);
                }
                stack.push(node);
                node = node.left;
                continue;
            }
            if (stack.empty()) {
                break;
            }
            // now our node is null, so pop something from stack
            node = stack.pop();
            // if node's right child == stack.peek() then take 
            // out the right child and push this node instead.
            if (!stack.empty() && node.right != null && node.right == stack.peek()) {
                Node temp = node;
                node = stack.pop();
                stack.push(temp);
            } else {
                nodesList.add(node.data);
                node = null;
            }
        }
        return nodesList;
    }

    public String preorder() {
        StringBuffer sb = new StringBuffer();
        preorder(this.root, sb);
        return sb.toString();
    }
    
    private void preorder(Node cur, StringBuffer sb) {
        if (cur == null) {
            sb.append("_");
            return;
        }
        sb.append(cur.data + "{");
        preorder(cur.left,sb);
        sb.append(",");
        preorder(cur.right, sb);
        sb.append("}");
    }
    
    public String postorder() {
        StringBuffer sb = new StringBuffer();
        postorder(this.root, sb);
        return sb.toString();
    }
    
    private void postorder(Node cur, StringBuffer sb) {
        if (cur == null) return;
        postorder(cur.left,sb);
        postorder(cur.right, sb);
        sb.append(cur.data + " ");
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

    public void printPerLevel() {
        Queue<Node> queue = new LinkedList<Node>();
        int nodesInCurrentLevel = 0, nodesInNextLevel = 0;
        queue.add(this.root);
        nodesInCurrentLevel = 1;

        while (nodesInCurrentLevel > 0 || nodesInNextLevel > 0) {
            if (nodesInCurrentLevel >0) {
                Node node = queue.remove();
                nodesInCurrentLevel--;
                if (node.left != null) {
                    queue.add(node.left);
                    nodesInNextLevel++;
                }
                if (node.right != null) {
                    queue.add(node.right);
                    nodesInNextLevel++;
                }
                System.out.print(node.data + " ");
            } else {
                nodesInCurrentLevel = nodesInNextLevel;
                nodesInNextLevel = 0;
                System.out.println();
            }
        }
        System.out.println();
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

    public void printEdgeNodes() {
        if (root == null) {
            return;
        }
        root.print();
        printLeftEdge(root.left, true);
        printRightEdge(root.right, true);
    }

    private void printLeftEdge(Node node, boolean print) {
        if (node == null) {
            return;
        }
        if (print) {
            node.print();
        } else if (node.left == null || node.right == null) {
            node.print();
        }
        printLeftEdge(node.left, true);
        printLeftEdge(node.right, false);
    }

    private void printRightEdge(Node node, boolean print) {
        if (node == null) {
            return;
        }

        if (print) {
            node.print();
        } else if (node.left == null || node.right == null) {
            node.print();
        }
        printRightEdge(node.right, true);
        printRightEdge(node.left, false);
    } 
}
