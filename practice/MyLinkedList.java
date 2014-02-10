public class MyLinkedList<T> {
    class Node {
        T data;
        Node next;

        public Node (T data) {
            this.data = data;
            this.next = null;
        }

        public void print() {
            System.out.print(this.data + ", ");
        }
    }

    private Node head;

    public void add (T data) {
        Node node = new Node(data);

        if (head == null) {
            head = node;
            return;
        }

        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = node;
    }

    public void print() {
        Node node = head;
        System.out.print("[ ");
        while (node != null) {
            node.print();
            node = node.next;
        }
        System.out.println("]");
    }

    public void reverse() {
        Node prvs, cur, fwd;
        prvs = null;
        cur = head;
        while (cur != null) {
            fwd = cur.next; // dont' lose the linked list
            cur.next = prvs;
            prvs = cur;
            cur = fwd;
        }
        head = prvs;
    }
}
