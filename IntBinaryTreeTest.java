import java.util.*;

public class IntBinaryTreeTest {
    
    public static void main(String[] args) {
        System.out.println("-----------Int Binary Tree----------------");
        IntBinaryTree binTree = new IntBinaryTree();
        
        List<Integer> allData = generateData(Integer.parseInt(args[0]));
        populateTree(allData, binTree);
        printPerLevel(binTree);
        System.out.println("----Removing nodes with path len > " + args[1] + "-------------");
        binTree.removeNodesNotInPathWithGivenSum(Integer.parseInt(args[1]));
        printPerLevel(binTree);
    }

    public static List<Integer> generateData(int maxNodes) {
        Random random = new Random();
        List<Integer> allData = new ArrayList<Integer>();
        for(int i = 0; i < maxNodes; i++) {
            allData.add(random.nextInt(10));
        }
        return allData;
    }

    public static <U extends Comparable<U>> void printPerLevel(BinaryTree<U> binTree) {
        Map<Integer, List<U>> listsPerLevel = binTree.traversePerLevel();
        for (Map.Entry<Integer, List<U>> levelEntry : listsPerLevel.entrySet()) {
            System.out.print("Level: " + levelEntry.getKey() + "---->     ");
            for (U data : levelEntry.getValue()) {
                System.out.print(data.toString() + " ");
            }
            System.out.println();
        }
    }
    
    public static void populateTree(int maxNodes, BinaryTree<Integer> tree) {
        Random random = new Random();
        for(int i = 0; i < maxNodes; i++) {
            tree.add(random.nextInt(10));
        }
    }

    public static void populateTree(List<Integer> allData, BinaryTree<Integer> tree) {
        for(Integer data : allData) {
            tree.add(data);
        }
    }
}
