public class BSTInt extends BST<Integer> {
    
    public void addAllGreater() {
        addAllGreater(root, 0);
    }

    private int addAllGreater(Node node, int sum) {
        if (node == null) return sum;

        int runningSum = addAllGreater(node.right, sum);
        node.data += runningSum;
        addAllGreater(node.left, node.data);
        return node.data;
    }
}
