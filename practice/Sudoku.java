import java.util.*;

public class Sudoku extends Questions {

    public static void main(String[] args) {
        char[][] board = new char[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board[i][j] = args[i].charAt(j);
            }
        }
        Sudoku s = new Sudoku();
        s.solveSudoku(board);
        System.out.println("After solving: " + s.print(board));
    }

    public String print(char[][] board) {
        StringBuilder sb = new StringBuilder();
        for (char[] row : board) {
            sb.append("\"");
            sb.append(new String(row) + "\" ");
        }
        return sb.toString();
    }

    public void solveSudoku(char[][] board) {
        int rows = board.length;
        if (rows == 0) {
            return;
        }
        int cols = board[0].length;
        if (cols == 0) {
            return;
        }
        
        solveSudoku(board, 0, 0, rows, cols);
    }
    
    private boolean solveSudoku(char[][] board, int i, int j, int rows, int cols) {
        if (j >= cols) {
            if (i >= rows-1) {
                return true;
            } else {
                return solveSudoku(board, i+1, 0, rows, cols);
            }
        }
        // current row is less than total rows
        if (board[i][j] == '.') {
            // try to fill in all values from 1-9 as long as they are safe. 
            for (char k = '1'; k <= '9'; k++) {
                if (isSafe(board, i, j, k, rows, cols)) {
                    board[i][j] = k;
                    debug(print(board));
                    if (solveSudoku(board, i, j+1, rows, cols)) {
                        return true;
                    }
                }
            }
            board[i][j] = '.';
        } else {
            return solveSudoku(board, i, j+1, rows, cols);
        }
        return false;
    }
    
    private boolean isSafe(char[][] board, int row, int col, char val, int rows, int cols) {
        // check for row
        for (int i = 0; i < cols; i++) {
            if (board[row][i] == val) {
                return false;
            }
        }
        
        // check for col
        for (int i = 0; i < rows; i++) {
            if (board[i][col] == val) {
                return false;
            }
        }
        
        // check for box
        // find the row start
        int rowStart = row/3;
        int colStart = col/3;
        for (int r = rowStart*3, rlen = (rowStart+1)*3; r < rlen; r++) {
            for (int c = colStart*3, clen = (colStart+1)*3; c < clen; c++) {
                if (board[r][c] == val) {
                    return false;
                }
            }
        }
        return true;
    }
}
