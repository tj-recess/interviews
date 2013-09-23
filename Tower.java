public class Tower {
    private Stack<Integer> first = new Stack<Integer>();
    private Stack<Integer> second = new Stack<Integer>();
    private Stack<Integer> third = new Stack<Integer>();
    private int total = 0;

    public static void main(String[] args) {
        Tower tower = new Tower(args);
        tower.print();
        tower.moveTo3();
        tower.print();
    }

    public void print() {
        System.out.println("Tower looks like this:\n");
        System.out.println("First:\n" + first.toString());
        System.out.println("Second:\n" + second.toString());
        System.out.println("Thrid:\n" + third.toString());
    }

    public Tower(String[] args) {
        this.total = args.length;
        for(String arg : args) {
            first.push(Integer.parseInt(arg));
        }
    }

    public void moveTo3() {
        System.out.println("-----------Shuffling around--------");
        move(total, first, third, second);
    }

    public void move(int total, Stack<Integer> source, Stack<Integer> destination, Stack<Integer> buffer) {
        if(total > 0) {
            move(total - 1, source, buffer, destination);
            Integer last = source.pop();
            destination.push(last);
            move(total - 1, buffer, destination, source);
        }
    }
}
