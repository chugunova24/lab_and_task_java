package lab1;

// эта программа определяет, является ли число простым
public class Primes {
    public static void main(String[] args) {

        for (int i = 2; i <= 100; i++) {
            isPrime(i);
        }

    }

    // деление числа на идущие перед ним числа
    public static boolean isPrime(int n) {

        boolean result = true;


        for (int e = 2; e < n; e++) {
            if (n % e == 0) {
                result = false;
//                    System.out.println(i);
            }
        }

        if (result != false) System.out.println(n);
        return result;
    }

}
