import java.util.*;

public class LinkedList<T> {
    
    private int len = 0;
    private Node root = null;

    private class Node {
        T data;
        Node next;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }

        public String toString() {
            return data.toString();
        }
    }

    public void append(T data) {
        Node newNode = new Node(data);

        if(root == null) {
            root = newNode;
        } else {
            Node last = root;
            while(last.next != null) {
                last = last.next;
            }
            last.next = newNode;
        }
        len++;
    }

    public LinkedList<T> removeDups() {
        if(root == null) return null;
        Node matcherNode = root;
        while(matcherNode != null) {
            Node curr = matcherNode.next;
            while(curr != null) {
                if(matcherNode.data.equals(curr.data)) {
                    deleteNode(curr);
                }
                curr = curr.next;
            }
            matcherNode = matcherNode.next;
        }
        return this;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        Node temp = root;
        int count = 0;
        while (temp != null) {
            if(count++ >= len + 1) { break; }
            sb.append(temp.toString() + " ");
            temp = temp.next;
        }
        return sb.toString();
    }

    public void deleteNode(Node node) {
        if(node == null || root == null) { return; }
        Node start = root;
        while (start != null && start.next != null) {
            if(start.next.data.equals(node.data)) {
                start.next = start.next.next;
                len--;
            }
            start = start.next;
        }
    }

    public LinkedList<Integer> sumDigits(LinkedList<T> other) {
        LinkedList<Integer> result = new LinkedList<Integer>();
        int carry = 0;
        for(Node curThis = root, curOther = other.root; curThis != null || curOther != null; ) {
            int curThisValue = curThis == null ? 0 : ((Integer)curThis.data).intValue();
            int curOtherValue = curOther == null ? 0: ((Integer)curOther.data).intValue();
            int data = curThisValue + curOtherValue + carry;
            carry = data >= 10 ? 1 : 0;
            if(carry == 1) { data = data - 10; }
            result.append(data);
            if(curThis != null) { curThis = curThis.next; }
            if(curOther != null) { curOther = curOther.next; }
        }
        return result;
    }

    public LinkedList<T> copy() {
        LinkedList<T> newList = new LinkedList<T>();
        for(Node cur = root; cur != null; cur = cur.next) {
            newList.append(cur.data);
        }
        return newList;
    }

    public LinkedList<T> makeCircular() {
        // find a random node and point it to one of the earlier nodes
        LinkedList<T> copy = this.copy();
        Random rand = new Random();
        int loopLocation = rand.nextInt(copy.len - 1);
        Node loopLocationNode = copy.root;
        for (int i = 0; i < loopLocation; i++) {
            loopLocationNode = loopLocationNode.next;
        }
        // find the last node and make it's next as loopLocationNode
        Node cur = copy.root;
        for(; cur.next != null; cur = cur.next);
        cur.next = loopLocationNode;
        return copy;
    }

    public String nthToLast(int n) {
        if(n == 0 || root == null) return null;
        Node startNode = root;
        for(int i = 0; i < n; i++) {
            if (startNode == null) {
                return null;
            }
            startNode = startNode.next;
        }
        Node firstNode = root;
        while(startNode != null) {
            firstNode = firstNode.next;
            startNode = startNode.next;
        }
        return firstNode.data.toString();
    }

    public String findLoopBegin() {
        if(root == null || root.next == null) { return ""; }
        Node slowNode = root;
        Node fastNode = root.next;
        while(slowNode != fastNode) {

            if (slowNode != null) {
                //System.out.println("DEBUG: slow = " + slowNode.data.toString());
            } else {
                break;
            }

            if (fastNode != null) {
                //System.out.println("DEBUG: fast = " + fastNode.data.toString());
            } else {
                break;
            }
            
            slowNode = slowNode.next;
            if (fastNode.next == null) { return ""; }
            fastNode = fastNode.next.next;
        }
        //System.out.println("--------------");
        if (fastNode == null || slowNode == null) { return ""; }
        // if this is a truly circular list, we should have both fast and slow pointing to same node
        slowNode = root;
        fastNode = fastNode.next;
        while (slowNode != fastNode) {
            //System.out.println("DEBUG: slow = " + slowNode.data.toString());
            //System.out.println("DEBUG: fast = " + fastNode.data.toString());
            slowNode = slowNode.next;
            fastNode = fastNode.next;
        }
        return slowNode.data.toString();
    }
}
