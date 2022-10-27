package task1;

public class task1 {
    static int chickens_legs = 2;
    static int cows_legs = 4;
    static int pigs_legs = 4;


    public static void main(String[] args) {
//        System.out.println(remainder(1, 3));
//        System.out.println(remainder(3, 4));
//        System.out.println(remainder(-9, 45) );
//        System.out.println(remainder(5, 5) );

//        System.out.println(triArea(3, 2) );
//        System.out.println(triArea(7, 4));
//        System.out.println(triArea(10, 10) );

//        System.out.println(animals(2, 3, 5));
//        System.out.println(animals(1, 2, 3));
//        System.out.println(animals(5, 2, 8) );

//        System.out.println(profitableGamble(0.2, 50, 9));
//        System.out.println(profitableGamble(0.9, 1, 2));
//        System.out.println(profitableGamble(0.9, 3, 2));

//        System.out.println(operation(24, 15, 9));
//        System.out.println(operation(24, 26, 2));
//        System.out.println(operation(15, 11, 11) );

//        System.out.println(ctoa('A'));
//        System.out.println(ctoa('m'));
//        System.out.println(ctoa('['));

        System.out.println(addUpTo(3));
        System.out.println(addUpTo(10));
        System.out.println(addUpTo(7));

//        System.out.println(sumOfCubes(new int[]{1, 5, 9}));
//        System.out.println(sumOfCubes(new int[]{3, 4, 5}));
//        System.out.println(sumOfCubes(new int[]{2}));
//        System.out.println(sumOfCubes(new int[]{}));
//
//        System.out.println(abcmath(new int[]{42, 5, 10}));
//        System.out.println(abcmath(new int[]{5, 2, 1}));
//        System.out.println(abcmath(new int[]{1, 2, 3}));
//
//        System.out.println(nextEdge(8, 10));
//        System.out.println(nextEdge(5, 7));
//        System.out.println(nextEdge(9, 2));





    }

    public static int remainder(int a, int b) {
        return a % b;
    }

    public static double triArea(int a, int b) {
        return 0.5 * a * b;
    }

    public static int animals(int chickens, int cows, int pigs) {
        return (chickens_legs * chickens) + (cows_legs*cows) + (pigs_legs * pigs);
    }

    public static boolean profitableGamble(double prob, int prize, int pay) {
        return prob * prize > pay;
    }

    public static String operation(int N, int a, int b) {
        if ((a + b) == N) return "added";
        else if ((a - b) == N) return "subtracted";
        else if ((a * b) == N) return "multiplication";
        else if (a / b == N) return "division";
        else return "None";
    }

    public static int ctoa(char character) {
        return (int) character;
    }

    public static int addUpTo(int a) {
        int result = 0;

        for (int i = 0; i <= a; i++) {
            result += i;
        }
        return result;
    }

    public static int nextEdge(int a, int b) {
        return (int) Math.sqrt(a*a + b*b - 2*a*b*Math.cos(179));
    }

    public static int sumOfCubes(int[] array) {
        int result = 0;

        for (int i = 0; i < array.length; i++) {
            int a = array[i];
            result += a*a*a;
        }
        return result;

    }

    public static boolean abcmath(int a, int b, int c) {

        for (int i = 1; i <= b; i++) a += a;

        return a % c == 0;
    }




}
