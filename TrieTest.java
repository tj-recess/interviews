public class TrieTest {
    public static void main(String[] args) {
        Trie trie = new Trie();
        int i = 0;
        for(; i < args.length-1; i++) {
            trie.insert(args[i]);
        }
        System.out.println("Trie looks like:");
        trie.print();
        System.out.println("Matching " + args[i] + " with entries in trie.........");
        System.out.println("Results: " + trie.match(args[i]));
    }
}
