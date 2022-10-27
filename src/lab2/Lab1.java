package lab2;
import lab2.Point3d;
public class Lab1 {
    //Start
    public static void main(String[] args) {
        Point3d A = getPointFromConsole();
        Point3d B = getPointFromConsole();
        Point3d C = getPointFromConsole();
        System.out.println(computeArea(A,B,C));
    }

    //вычисляем площадь треугольника
    public static double computeArea(Point3d A, Point3d B, Point3d C) {
        if (A.isEqualTo(B) || B.isEqualTo(C) || C.isEqualTo(A)) {
            System.out.println("Точки не должны быть равны");
            return -1;
        }
        double AB = A.distanceTo(B);
        double BC = B.distanceTo(C);
        double CA = C.distanceTo(A);
        double p = (AB+BC+CA)/2.0;
        return Math.sqrt(p*(p-AB)*(p-BC)*(p-CA));
    }

    //обработчик консольного ввода
    private static Point3d getPointFromConsole() {
        System.out.println("Введите координаты(x y z): ");
        String input = System.console().readLine();
        String inpArr[] = input.split(" ");
        double x = Double.parseDouble(inpArr[0]);
        double y = Double.parseDouble(inpArr[1]);
        double z = Double.parseDouble(inpArr[2]);
        return new Point3d(x,y,z);
    }
}