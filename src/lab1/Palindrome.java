package lab1;

// эта программа определяет, является ли слово полиндромом
public class Palindrome {
    public static void main(String[] args)  {
        for (int i = 0; i < args.length; i++) {
            String s = args[i];
            idPalindrome(s);
        }
    }



    // реверс строки
    public static String reverseString(String s) {
        String zeroS = "";
        for (int i = s.length(); i != 0; i--) {
//             System.out.println(i-1);
             char t = s.charAt(i-1);
//             System.out.println(t);
             zeroS = zeroS + t;

        }
//        System.out.println(zeroS);
        return zeroS;
    }

    // сравнение строк
    public static boolean idPalindrome(String s) {
        boolean result = false;

//        if (s.equals(reverseString(s)) == true) result = true;
//        else result = false;

        System.out.println(s.equals(reverseString(s)));

        if (s.equals(reverseString(s)) == true) return true;
        else return false;


    }



}
