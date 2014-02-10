import java.util.*;

public class BinaryTreeTest {
    public static void main(String[] args) {
        
        System.out.println("------------------Binary Tree----------------");
        BinaryTree<Integer> binTree = new BinaryTree<Integer>();
        
        List<Integer> allData = generateData(Integer.parseInt(args[0]));
        populateTree(allData, binTree);
        binTree.printPerLevel();
        System.out.println("Inorder(R) looks like: " + binTree.inorder());
        System.out.println("BinTree.findLargestBST() : " + binTree.findLargestBST());
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

}
