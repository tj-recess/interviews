import java.util.*;

public class BinaryTree<T extends Comparable<T>> extends Questions {
    class Node {
        T data;
        Node left, right;
    }

    private Node root;
    private Random random = new Random();

    private void inorder(Node cur, StringBuffer sb) {
        if (cur == null) return;
        inorder(cur.left,sb);
        sb.append(cur.data + " ");
        inorder(cur.right, sb);
    }
    
    public String inorder() {
        StringBuffer sb = new StringBuffer();
        inorder(this.root, sb);
        return sb.toString();
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

    public void add(T data) {
        Node newNode = new Node();
        newNode.data = data;
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

    // Result class for LargestBSTResult
    class LBR {
        boolean isBST;
        T max;
        int numNodes;
        Node bstRoot;
    }

    public T findLargestBST() {
        LBR result = findLargestBST(root);
        print("LBR.isBST = " + result.isBST);
        print("LBR.max = " + result.max);
        print("LBR.numNodes = " + result.numNodes);
        print("LBR.bstRoot.data = " + result.bstRoot.data);
        return result.bstRoot.data;
    }

    private LBR findLargestBST(Node node) {
        LBR result = new LBR();
        if (node == null) {
            result.bstRoot = null;
            result.numNodes = 0;
            result.max = null;
            result.isBST = true;
            return result;
        }

        LBR leftResult = findLargestBST(node.left);
        LBR rightResult = findLargestBST(node.right);

        if (leftResult.isBST && rightResult.isBST) {
            if ((leftResult.max == null || node.data.compareTo(leftResult.max) >= 0)
                && (rightResult.max == null || node.data.compareTo(rightResult.max) <= 0)) {

                // Happy Case (node is also a BST)
                result.isBST = true;
                result.max = rightResult.max != null ? rightResult.max : node.data;
                result.numNodes = leftResult.numNodes + rightResult.numNodes + 1;
                result.bstRoot = node;
                return result;
            } else {
                return (leftResult.numNodes > rightResult.numNodes) ? leftResult : rightResult;
            }
        } else if (leftResult.isBST) {
            return leftResult;
        } else if (rightResult.isBST) {
            return rightResult;
        }

        result.isBST = false;
        return result;
    }
}
