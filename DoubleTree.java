import java.util.*;

public class DoubleTree<T extends Comparable<T>> extends BinaryTree<T> {
    public void doubleIt() {
        doubleIt(this.root);
    }

    private void doubleIt(Node node) {
        if (node == null) {
            return;
        }

        Node dup = new Node(node.data);
        dup.left = node.left;
        node.left = dup;

        doubleIt(node.left.left);
        doubleIt(node.right);
    }

    public static void main(String[] args) {
        DoubleTree<Character> binTree = new DoubleTree<Character>();
        populateTree(Integer.parseInt(args[0]), binTree);
        System.out.println("Initially BinaryTree :");
        printPerLevel(binTree);
        binTree.doubleIt();
        System.out.println("After doubling, BinaryTree :");
        printPerLevel(binTree);
    }
    
    public static void populateTree(int maxNodes, BinaryTree<Character> tree) {
        Random r = new Random();
        for(int i = 0; i < maxNodes; i++) {
            char c = (char)(r.nextInt(26) + 'a');
            tree.add(c);
        }
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
