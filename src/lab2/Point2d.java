package lab2;
//Point 2d class

public class Point2d {
    //X coordinate
    private double xCoord;
    //Y coordinate
    private double yCoord;

    //init
    public Point2d ( double x, double y) {
        xCoord = x;
        yCoord = y;
    }

    //init default
    public Point2d () {
        this(0, 0);
    }

    //Get X coord
    public double getX () {
        return xCoord;
    }

    //get Y coord
    public double getY () {
        return yCoord;
    }

    //set X coord
    public void setX ( double val) {
        xCoord = val;
    }

    //set Y coord
    public void setY ( double val) {
        yCoord = val;
    }

    //Checks if two points are equal
    public boolean isEqualTo(Point2d other) {
        if (xCoord == other.getX() && yCoord == other.getY()) {
            return true;
        }
        return false;
    }

    //Calculates distance between points
    public double distanceTo(Point2d other) {
        return Math.sqrt(Math.pow(xCoord - other.getX(),2) + Math.pow(yCoord - other.getY(),2));
    }
}