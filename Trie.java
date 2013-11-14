import java.util.*;

public class Trie {
    static class Node {
        Map<Character, Node> edgeMap = new HashMap<Character, Node>();
        Character data = null;
    }

    private static final Node BLANK = new Node();
    private Node root = new Node();  // root never contains any data

    public void insert(String str) {
        //System.out.println("DEBUG: inserting " + str);
        this.insert(this.root, str, 0);
    }

    private void insert(Node node, String str, int index) {
        //System.out.println("DEBUG: str=" + str + ", index=" + index + ", node.data=" + node.data);
        if (str.equals("") || index >= str.length()) {
            return;
        }
        Character ch = str.charAt(index);
        //System.out.println("DEBUG: ch=" + ch);
        Node next = node.edgeMap.get(ch);
        if (next == null) {
            next = new Node();
            next.data = ch;
            node.edgeMap.put(ch, next);
        } 
        //System.out.println("DEBUG: node.data = " + node.data + ", node.edgeMap=" + node.edgeMap);
        index++;
        //System.out.println("DEBUG: index=" + index);
        insert(next, str, index);
    }

    public List<String> match(String word) {
        if (word.equals("")) {
            return new ArrayList<String>();
        }
        return match(root, word, 0);
    }

    private List<String> match(Node node, String word, int index) {
        //System.out.println("DEBUG: node.data=" + node.data + ", word=" + word + ", index=" + index);
        if (index >= word.length()) {
            // word has finished so return everything starting from this node
            String prvsWord = "";
            if (index-1 >= 0) {
                prvsWord = word.substring(0, index-1);
            }
            return getAllFromNode(node, prvsWord);
        } else {
            // continue matching further
            Character ch = word.charAt(index);
            Node next = node.edgeMap.get(ch);
            if (next == null) {
                // return empty list as word has a char which doesn't match anything in trie
                return new ArrayList<String>(); 
            } else {
                return match(next, word, index+1);
            }
        }
    }

    private List<String> getAllFromNode(Node node, String soFar) {
        List<String> allFromHere = new ArrayList<String>();
        soFar = soFar + node.data;
        // if no new nodes, return whatever you got + your data
        //System.out.println("DEBUG: soFar=" + soFar);
        //System.out.println("DEBUG: node.data=" + node.data + ", node.edgeMap=" + node.edgeMap);
        if (node.edgeMap.size() == 0) {
            allFromHere.add(soFar);
        } else {
            for (Node next: node.edgeMap.values()) {
                allFromHere.addAll(getAllFromNode(next, soFar));
            }
        }
        return allFromHere;
    }

    public void print() {
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(root);
        queue.add(BLANK);
        Node node = null;
        while (true) {
            node = queue.poll();
            if (node == null) {
                break;
            }
            if (node == BLANK) {
                System.out.println();
            } else {
                System.out.print(node.data + " ");
                for (Node next : node.edgeMap.values()) {
                    queue.add(next);
                }
                queue.add(BLANK);
            }
        }
    }
}        
