package lab4;

import java.awt.geom.Rectangle2D;

//Этот класс предназначен для работы с множеством Мандельброта в программе.
public class Mandelbrot extends FractalGenerator {
    // Установим лимит итераций. Для эффективной работы программы
    public static final int MAX_ITERATIONS = 2000;

    //Некоторые значения по умолчанию для расположения изображения
    @Override
    public void getInitialRange(Rectangle2D.Double range) {
        range.x = -2;
        range.y = -1.5;
        range.height = 3;
        range.width = 3;
    }

    //Проверяет конкретное местоположение на то, находится ли оно в наборе или нет.
    @Override
    public int numIterations(double x, double y) {
        double r = x;
        double i = y;
        int counter = 0;
        while (counter < MAX_ITERATIONS) {
            counter++;
            double k = r * r - i*i+x;
            double m = 2 * r * i + y;
            r = k;
            i = m;
            if (r*r+i*i > 4)
                break;
        }
        if (counter == MAX_ITERATIONS)
            return -1;
        return counter;
    }
}
