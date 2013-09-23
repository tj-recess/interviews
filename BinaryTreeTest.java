import java.util.*;

public class BinaryTreeTest {
    public static void main(String[] args) {
        System.out.println("------------------Binary Tree----------------");
        BinaryTree<Integer> binTree = new BinaryTree<Integer>();
        
        List<Integer> allData = generateData(Integer.parseInt(args[0]));
        populateTree(allData, binTree);
        printPerLevel(binTree);
        System.out.println("Inorder looks like: " + binTree.inorder());
        System.out.println("Preorder looks like: " + binTree.preorder());
        System.out.println("Is Balanced = " + binTree.isBalanced());
        Random random = new Random();
        int x = allData.get(random.nextInt(allData.size()));
        int y = allData.get(random.nextInt(allData.size()));
        System.out.println("Common ancestor of " + x + " and " + y + " is: " + binTree.findCommonAncestor(x, y));

        /*
        System.out.println("\n\nGenerating tree with min height from given input:");
        BinaryTree<String> binTreeS = new BinaryTree<String>();
        binTreeS.createTreeWithMinHeight(args);
        printPerLevel(binTreeS);
        System.out.println("Inorder looks like: " + binTreeS.inorder());
        System.out.println("Preorder looks like: " + binTreeS.preorder());
        System.out.println("Is Balanced = " + binTreeS.isBalanced());
        */
    }
    
    public static void populateTree(int maxNodes, BinaryTree<Integer> tree) {
        Random random = new Random();
        for(int i = 0; i < maxNodes; i++) {
            tree.add(random.nextInt(100));
        }
    }

    public static void populateTree(List<Integer> allData, BinaryTree<Integer> tree) {
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
}
