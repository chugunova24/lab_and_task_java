package lab3;

/**
 * Этот класс представляет определенное место на 2D-карте.
 * Координаты являются целочисленными значениями.
 **/
public class Location
{
    /** X coordinate of this location. **/
    public int xCoord;

    /** Y coordinate of this location. **/
    public int yCoord;


    /** Создает новое местоположение с указанными целочисленными координатами. **/
    public Location(int x, int y)
    {
        xCoord = x;
        yCoord = y;
    }

    /** Создает новую локацию с координатами (0, 0). **/
    public Location()
    {
        this(0, 0);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (obj == null || obj.getClass() != this.getClass())
            return false;

        Location location = (Location) obj;
        return xCoord == location.xCoord && yCoord == location.yCoord;
    }

    @Override
    public int hashCode() {
        int result = 17; // Простое число

        // используется другое простое число для перемножения
//        result = 37 * result + xCoord;
        result = 37 * result + yCoord + xCoord;
        return result;
    }
}
