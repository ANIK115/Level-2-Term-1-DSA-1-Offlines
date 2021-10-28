package divideandconquer;

public class Point {
    public double x;
    public double y;
    public int index;

    public Point(double x, double y, int index) {
        this.x = x;
        this.y = y;
        this.index = index;
    }
    public static double distance(Point a, Point b)
    {
        return Math.sqrt((a.x-b.x)*(a.x-b.x) + (a.y-b.y)*(a.y-b.y));
    }
}
