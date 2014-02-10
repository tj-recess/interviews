import java.math.*;
import java.util.*;
 
public class Solution extends Questions {

    public static void main(String[] args) {
        Solution s = new Solution();
        int count = args.length;
        Point[] points = new Point[count/2];
        for (int i = 0; i < count/2; i++) {
            points[i] = s.new Point(Integer.parseInt(args[2*i]), Integer.parseInt(args[2*i+1]));
            //debug("points[" + i + "]=" + points[i]);
        }
        long start = System.currentTimeMillis();
        System.out.println("Max points in line = " + s.maxPoints(points));
        long end = System.currentTimeMillis();
        System.out.println("Time = " + (end - start));
    }

    /**
    * Definition for a point.
    */
    private class Point {
        int x;
        int y;
        Point() { x = 0; y = 0; }
        Point(int a, int b) { x = a; y = b; }
        public String toString() {
            return "Point[x = " + x + "][y = " + y + " ]";
        }
    }

    public int maxPoints(Point[] points) {
        if (points.length == 0) {
            return 0;
        }
        Map<BigDecimal, Set<Point>> map = new HashMap<BigDecimal, Set<Point>>();
        MathContext p5 = new MathContext(5);
        Set<Point> infinitySet = new HashSet<Point>();
        for (int i = 0, len = points.length; i < len; i++) {
            Point p = points[i];
            //debug("p = " + p.toString());
            for (int j = i+1; j < len; j++) {
                Point q = points[j];
                //debug("q = " + q.toString());
                if (!isGradInfinity(p, q)) {
                    BigDecimal gradient = new BigDecimal(getGrad(p, q), p5);
                    addPointCount(map, gradient, p, q);
                } else {
                    infinitySet.add(p);
                    infinitySet.add(q);
                }
            }
        }
        int maxPoints = infinitySet.size(); 
        for (Set<Point> set : map.values()) {
            if (set.size() > maxPoints) {
                maxPoints = set.size();
            }
        }
        return maxPoints;
    }
    
    private double getGrad(Point p, Point q) {
        if (isGradInfinity(p, q)) {
            throw new IllegalStateException();
        }
        return ((double)(p.y-q.y))/((double)(p.x-q.x));
    }
    
    private boolean isGradInfinity(Point p, Point q) {
        return ((p.x-q.x) == 0);
    }
    
    private void addPointCount(Map<BigDecimal, Set<Point>> map, BigDecimal gradient, Point p, Point q) {
        Set<Point> set = map.get(gradient);
        if (set == null) {
            set = new HashSet<Point>();
            map.put(gradient, set);
        }
        set.add(p);
        set.add(q);
    }
}
