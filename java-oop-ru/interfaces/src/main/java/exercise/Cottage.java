package exercise;

// BEGIN
public class Cottage implements Home {
    @Override
    public double getArea() {
        return area;
    }

    @Override
    public int compareTo(Home another) {
        if (another.getArea() == getArea()) {
            return 0;
        } else if (another.getArea() > getArea()) {
            return -1;
        } else {
            return 1;
        }
    }

    double area;
    int floorCount;

    public Cottage(double area, int floorCount) {
        this.area = area;
        this.floorCount = floorCount;
    }

    @Override
    public String toString() {
        return floorCount + " этажный коттедж площадью " +
                getArea() + " метров";
    }
}
// END
