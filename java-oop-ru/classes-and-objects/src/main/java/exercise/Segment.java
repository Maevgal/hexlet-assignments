package exercise;

// BEGIN
public class Segment {
    Point point1;
    Point point2;

    public Segment(Point point1, Point point2) {
        this.point1 = point1;
        this.point2 = point2;
    }

    public Point getBeginPoint() {
        return point1;
    }

    public Point getEndPoint() {
        return point2;
    }

    public Point getMidPoint() {
        int mp1 = (point1.getX() + point2.getX()) / 2;
        int mp2 = (point1.getY() + point2.getY()) / 2;
        return new Point(mp1, mp2);
    }
}
// END
