import java.util.*;

public class BSTTest {
    public static void main(String[] args) {

        System.out.println("------------------BST----------------");
        BST<Integer> bst = new BST<Integer>();
        List<Integer> allData = generateData(Integer.parseInt(args[0]));
        populateBST(allData, bst);
        printPerLevel(bst);
        System.out.println("Inorder looks like: " + bst.inorder());
        System.out.println("Preorder looks like: " + bst.preorder());
        System.out.println("Is Balanced = " + bst.isBalanced());
        Random random = new Random();
        int x = allData.get(random.nextInt(allData.size()));
        System.out.println("Successor of " + x + " is: " + bst.findSuccessor(x));
        System.out.println("Predecessor of " + x + " is: " + bst.findPredecessor(x));
    }
    
    public static void populateBST(List<Integer> allData, BST<Integer> tree) {
        for(Integer data : allData) {
            tree.add(data);
        }
    }

    public static List<Integer> generateData(int maxNodes) {
        Random random = new Random();
        List<Integer> allData = new ArrayList<Integer>();
        for(int i = 0; i < maxNodes; i++) {
            allData.add(random.nextInt(100));
        }
        return allData;
    }

    public static <U extends Comparable<U>> void printPerLevel(BST<U> binTree) {
        Map<Integer, List<U>> listsPerLevel = binTree.traversePerLevel();
        for (Map.Entry<Integer, List<U>> levelEntry : listsPerLevel.entrySet()) {
            System.out.print("Level: " + levelEntry.getKey() + "---->     ");
            for (U data : levelEntry.getValue()) {
                System.out.print(data.toString() + " ");
            }
            System.out.println();
        }
    }
}
