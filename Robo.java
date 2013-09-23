import java.util.*;

public class Robo {

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        Robo robo = new Robo(N);
        List<List<Integer>> allPaths = robo.getAllPaths();

        System.out.println("All paths in matrix of " + N + "x" + N + " are:");

        for (List<Integer> path : allPaths) {
            System.out.println(path);
        }
    }

    public Robo(int N) {
        this.N = N;
        this.matrix = new int[N][N];
        int id = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                matrix[i][j] = id++;
            }
        }
    }

    public List<List<Integer>> getAllPaths() {
        return getAllPaths(0, 0, new ArrayList<Integer>());
    }

    private int N;
    private int[][] matrix;

    public List<List<Integer>> getAllPaths(int startx, int starty, List<Integer> pathToMe) {
        List<List<Integer>> allPaths = new ArrayList<List<Integer>>();

        if (startx < N && starty < N) {
            pathToMe.add(matrix[startx][starty]);
        }
        
        if (startx == N - 1 && starty == N -1) {
            allPaths.add(pathToMe);
        }

        if (startx + 1 < N) {
            List<Integer> copy = new ArrayList<Integer>(pathToMe);
            allPaths.addAll(getAllPaths(startx + 1, starty, copy));
        }
        if (starty + 1 < N) {
            List<Integer> copy = new ArrayList<Integer>(pathToMe);
            allPaths.addAll(getAllPaths(startx, starty + 1, copy));
        }

        return allPaths;
    }
}


