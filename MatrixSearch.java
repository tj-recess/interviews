import java.util.*;

public class MatrixSearch {
    public static void main(String[] args) {
        int[][] matrix = populateMatrix(5, 5);
        System.out.println("Matrix:");
        print(matrix);
        System.out.println("Is " + args[0] + " present in matrix: " + search(Integer.parseInt(args[0]), matrix, 5, 5));
    }

    public static void print(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static int[][] populateMatrix(int rows, int cols) {
        int[][] matrix = new int[rows][cols];
        Random rand = new Random();
        for(int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int prvs = 0;
                if (i > 0) prvs += matrix[i-1][j];
                if (j > 0) prvs += matrix[i][j-1];
                matrix[i][j] = rand.nextInt(100) + prvs;
            }
        }
        return matrix;
    }

    public static boolean search(int x, int[][] matrix, int rows, int cols) {
        int row = 0, col = cols-1;
        while (row < rows && col >= 0) {
            if (matrix[row][col] == x) return true;
            else if (matrix[row][col] > x) col--;
            else row++;
        }
        return false;
    }
}
