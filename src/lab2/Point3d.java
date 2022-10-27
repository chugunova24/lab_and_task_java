package lab2;


//Point 3d class
public class Point3d extends Point2d {
    private double zCoord;

    //init
    public Point3d (double x, double y, double z) {
        super(x,y);
        zCoord = z;
    }

    //init default
    public Point3d () {
        this(0, 0, 0);
    }

    public double getZ () {
        return zCoord;
    }


    public void setZ ( double val) {
        zCoord = val;
    }

    // Проверка равны ли координаты точек
    public boolean isEqualTo(Point3d other) {
        return super.getX() == other.getX() && super.getY() == other.getY() && zCoord == other.getZ();
    }

    // Вычисление дистанции между точками
    public double distanceTo(Point3d other) {
        return Math.sqrt(Math.pow(super.getX() - other.getX(),2) + Math.pow(super.getY() - other.getY(),2) + Math.pow(zCoord - other.getZ(),2));
    }
}