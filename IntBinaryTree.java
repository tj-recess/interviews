import java.util.*;

public class IntBinaryTree extends BinaryTree<Integer> { 

    /**
    * This removes all the nodes which doesn't fall in the path 
    * of a tree (path = root-to-leaf) with given sum.
    */
    public void removeNodesNotInPathWithGivenSum(int sum) {
        if (root == null) {
            return;
        }
        // pass current sum to every node and decide if it should 
        // be deleted or not.
        removeNodeIfLessThanSum(null, this.root, 0, sum);
    }

    public boolean removeNodeIfLessThanSum(Node parent, Node node, int runningSum, int bigSum) {
        if (node == null) {
            return true;
        }
        int mySum = runningSum + this.root.data.intValue();
        // first remove left and right node then check about myself
        boolean leftRemoved = removeNodeIfLessThanSum(node, node.left, mySum, bigSum);
        boolean rightRemoved = removeNodeIfLessThanSum(node, node.right, mySum, bigSum);
        if (leftRemoved && rightRemoved) {
            // check if runningSum + me is < bigSum
            if (mySum < bigSum) {
                if (parent == null) {
                    this.root = null;
                } else {
                    // remove me and return true
                    if (parent.left == node) {
                        parent.left = null;
                    } else {
                        parent.right = null;
                    }
                }
                return true;
            }
        }
        return false;
    }
}
