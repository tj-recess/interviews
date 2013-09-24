public class Queens {
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        System.out.println("Queens placed for matrix of size " + N + " are:");
        Queens q = new Queens();
        q.enumerate(N);
    }

    public void enumerate(int N) {
        int[] board = new int[N];
        this.enumerate(board, 0);
    }

    public void enumerate(int[] board, int row) {
        if (row == board.length) {
            print(board);
            return;
        }
        for (int i = 0; i < board.length; i++) {
            board[row] = i;
            if (isConsistent(board, row)) {
                enumerate(board, row+1);
            }
        }
    }

    private boolean isConsistent(int[] board, int row) {
        for (int i = 0; i < row; i++) {
            if (board[i] == board[row]) {
                return false;
            }
            if (board[i] - board[row] == i - row) return false;
            if (board[i] - board[row] == row - i) return false;
        }
        return true;
    }

    private void print(int[] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j =0; j < board.length; j++) {
                if (board[i] == j) {
                    System.out.print("Q ");
                } else {
                    System.out.print("* ");
                }
            }
            System.out.println();
        }
        System.out.println("-----------------");
    }
}
