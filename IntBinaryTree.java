import java.util.*;

public class IntBinaryTree extends BinaryTree<Integer> { 

    public List<List<Integer>> getRootToLeafPathsWithGivenSum(Integer sum) {
        // check if any of left or right has sum = (given sum - my value) 
        // and do this recursively.
        return getRootToLeafPathsWithGivenSum(sum, this.root);
    }

    private List<List<Integer>> getRootToLeafPathsWithGivenSum(Integer sum, Node node) {
        // System.out.print("DEBUG: sum = " + sum);
        if (node == null) {
            // return an empty list
            List<List<Integer>> listOfLists = new ArrayList<List<Integer>>();
            if (sum == 0) {
                listOfLists.add(new ArrayList<Integer>());
                // System.out.println("DEBUG: node = null and sum = 0, so returned: " + listOfLists);
            }
            return listOfLists;
        }

        // System.out.println(", node.data = " + node.data);
        // if both right and left are null, dont' call for both as they will result in identical lists
        List<List<Integer>> fromLeft = getRootToLeafPathsWithGivenSum(sum - node.data, node.left);
        List<List<Integer>> fromRight = null;
        // call for right data IFF at least one of left or right is not null
        if (node.left != null || node.right != null) {
            getRootToLeafPathsWithGivenSum(sum - node.data, node.right);
        }
        List<List<Integer>> finalList = new ArrayList<List<Integer>>();
        if (fromLeft != null) {
            // System.out.println("DEBUG: From left : " + fromLeft);
            for (List<Integer> list : fromLeft) {
                // add myself to every list and add to final list
                list.add(0, node.data);
                finalList.add(list);
            }
        }

        if (fromRight != null) {
            // System.out.println("DEBUG: From right : " + fromRight);
            for (List<Integer> list : fromRight) {
                // add myself to every list and add to final list
                list.add(0, node.data);
                finalList.add(list);
            }
        }
        // System.out.println("DEBUG: final List: " + finalList);
        return finalList;
    }


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
