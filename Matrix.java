import java.util.List;
import java.util.ArrayList;

public class Matrix {

    public int[][] rotate(int[][] matrix) {
        int numRows = matrix.length;
        int numCols = 0;
        if(numRows > 0) { numCols = matrix[0].length; }
        int[][] rotated = new int[numCols][numRows];

        for(int col = 0; col < numCols; col++) {
            for(int row = 0; row < numRows; row++) {
                int rotateCol = numRows - row - 1;
                rotated[col][rotateCol] = matrix[row][col];
            }
        }
        return rotated;
    }

    public static void main(String[] args) {
        Matrix rm = new Matrix();
        int[][] matrix = rm.parseMatrix(args);
        System.out.println("Current matrix:");
        rm.print(matrix);
        System.out.println("Rotated matrix:");
        rm.print(rm.rotate(matrix));
        System.out.println("Zeroed matrix:");
        rm.print(rm.zerofy(matrix));
    }

    public int[][] parseMatrix(String[] args) {
        int rows = Integer.parseInt(args[0]);
        int cols = Integer.parseInt(args[1]);
        int index = 2;
        int[][] matrix = new int[rows][cols];
        for(int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                matrix[row][col] = Integer.parseInt(args[index++]);
            }
        }
        return matrix;
    }

    public void print(int[][] matrix) {
        int rows = matrix.length;
        int cols = 0;
        if(rows > 0) { cols = matrix[0].length; } 
        for(int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                System.out.print(matrix[row][col] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public int[][] zerofy(int[][] matrix) {
        int rows = matrix.length;
        int cols = 0;
        if(rows > 0) { cols = matrix[0].length; } 
        
        List<Integer> zeroRows = new ArrayList<Integer>();
        List<Integer> zeroCols = new ArrayList<Integer>();

        for(int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if(matrix[i][j] == 0) {
                    zeroRows.add(i);
                    zeroCols.add(j);
                }
            }
        }

        for(int row : zeroRows) {
            for(int col = 0; col < cols; col++) {
                matrix[row][col] = 0;
            }
        }

        for(int col : zeroCols) {
            for(int row = 0; row < rows; row++) {
                matrix[row][col] = 0;
            }
        }
        return matrix;
    }
}
