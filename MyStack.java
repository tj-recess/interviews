public class MyStack<T extends Comparable<T>> {
    private class Node {
        T data;
        Node next = null;
        T min = null;
        public Node(T data) {
            this.data = data;
        }
    }
    private Node top;
    private T globalMin = null;

    public void push(T data) {
        Node newNode = new Node(data);
        newNode.next = top;
        top = newNode;

        if(globalMin == null) {
            globalMin = data;
        } else if (globalMin.compareTo(data) > 0) {
            globalMin = data;
        }
        newNode.min = globalMin;
    }

    public T pop() {
        if (top == null) { throw new RuntimeException("stack underflow"); }
        T returnVal = this.top.data;
        top = top.next;
        if (top == null) {
            globalMin = null;
        } else {
            globalMin = top.min;
        }
        return returnVal;
    }

    public T min() {
        return globalMin;
    }
        

    public String toString() {
        StringBuffer sb = new StringBuffer();
        Node cur = top;
        while (cur != null) {
            sb.append(cur.data + "\n");
            cur = cur.next;
        }
        return sb.toString();
    }
}
