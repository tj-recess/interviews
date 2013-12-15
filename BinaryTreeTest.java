import java.util.*;

public class BinaryTreeTest {
    public static void main(String[] args) {
        
        System.out.println("------------------Binary Tree----------------");
        BinaryTree<Integer> binTree = new BinaryTree<Integer>();
        
        List<Integer> allData = generateData(Integer.parseInt(args[0]));
        populateTree(allData, binTree);
        printPerLevel(binTree);
        System.out.println("-----new print per level------");
        binTree.printPerLevel();
        System.out.println("Inorder(R) looks like: " + binTree.inorder());
        System.out.println("Inorder(I) looks like: " + binTree.inorderIterative());
        System.out.println("Preorder(R) looks like: " + binTree.preorder());
        System.out.println("Preorder(I) looks like: " + binTree.preorderIterative());
        System.out.println("Postorder(R) looks like: " + binTree.postorder());
        System.out.println("Postorder(I) looks like: " + binTree.postorderIterative());
        
        System.out.print("Printing only edge nodes: ");
        binTree.printEdgeNodes();
        System.out.println();
        System.out.println("Is Balanced = " + binTree.isBalanced());
        System.out.println("Reconstructing tree from preorder and inorder...");
        System.out.println("New Tree: ");
        BinaryTree<Integer> newTree = new BinaryTree<Integer>();
        printPerLevel(newTree.construct(binTree.inorderIterative(), binTree.preorderIterative()));
        // Random random = new Random();
        // int x = allData.get(random.nextInt(allData.size()));
        // int y = allData.get(random.nextInt(allData.size()));
        // System.out.println("Common ancestor of " + x + " and " + y + " is: " + binTree.findCommonAncestor(x, y));
        

        /*
        System.out.println("\n\nGenerating tree with min height from given input:");
        BinaryTree<String> binTreeS = new BinaryTree<String>();
        binTreeS.createTreeWithMinHeight(args);
        printPerLevel(binTreeS);
        System.out.println("Inorder looks like: " + binTreeS.inorder());
        System.out.println("Preorder looks like: " + binTreeS.preorder());
        System.out.println("Is Balanced = " + binTreeS.isBalanced());
        
        
        System.out.println("-----------Int Binary Tree----------------");
        IntBinaryTree binTree = new IntBinaryTree();
        
        List<Integer> allData = generateData(Integer.parseInt(args[0]));
        populateTree(allData, binTree);
        printPerLevel(binTree);
        System.out.println("Inorder(R) looks like: " + binTree.inorder());
        System.out.println("Inorder(No Stack) looks like: " + binTree.inorderNoStack());
        System.out.println("Preorder(R) looks like: " + binTree.preorder());
        System.out.println("Postorder(R) looks like: " + binTree.postorder());
        System.out.println("----Removing nodes with path len > " + args[1] + "-------------");
        binTree.removeNodesNotInPathWithGivenSum(Integer.parseInt(args[1]));
        printPerLevel(binTree);
        System.out.println("Inorder(R) looks like: " + binTree.inorder());
        System.out.println("Preorder(R) looks like: " + binTree.preorder());
        System.out.println("Postorder(R) looks like: " + binTree.postorder());*/
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
}
