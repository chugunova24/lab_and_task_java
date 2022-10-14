package lab2;

public class Point3d {

    private double xCoord;
    private double yCoord;
    private double zCoord;


    // создание объекта с тремя значениями с плавающей точкой
    public Point3d ( double x, double y, double z) {
        xCoord = x;
        yCoord = y;
        zCoord = z;
    }

    // новый объект со значениями 0.0 по умолчанию
    public Point3d () {
        this(0.0, 0.0, 0.0);
    }

    // получить значение х
    public double getX () {
        return xCoord;
    }

    // получить значение y
    public double getY () {
        return yCoord;
    }

    // получить значение z
    public double getZ () {
        return zCoord;
    }

    // установить значение х
    public void setX (double val) {
        xCoord = val;
    }

    // установить значение y
    public void setY (double val) {
        yCoord = val;
    }

    // установить значение z
    public void setZ (double val) {
        zCoord = val;
    }

    // сравнение значений двух объектов
    public boolean equals(Point3d obj){
        return xCoord==obj.xCoord && yCoord==obj.yCoord &&
                zCoord==obj.zCoord;
    }

    // вычиcляет pаccтoяние междy двyмя тoчками,
    public double distanceTo(Point3d obj) {
        return Math.sqrt(Math.pow(xCoord - obj.xCoord, 2) + Math.pow(yCoord -
                obj.yCoord, 2) + Math.pow(zCoord - obj.zCoord, 2));
    }

}

