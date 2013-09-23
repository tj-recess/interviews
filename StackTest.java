public class StackTest {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<Integer>();
        for(String arg : args) {
            stack.push(Integer.parseInt(arg));
        }
        System.out.println("Stack:\n" + stack.toString());
        System.out.println("Min: " + stack.min());
    }
}
