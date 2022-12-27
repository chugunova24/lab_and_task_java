package task3;

import java.util.*;


public class task3 {

    public static void main(String[] args) {
        System.out.println(solutions(0,0,1));
        System.out.println(findZip("all zip files are zipped"));
        System.out.println(checkPerfect(496));
        System.out.println(flipEndChars("Cat, dog, and mouse."));
        System.out.println(isValidHexCode("#CD5C5C"));
        System.out.println(same(new Integer[]{9,8,7,6},new Integer[]{4,4,3,1}));
        System.out.println(isKaprekar(22222));
        System.out.println(longestZero("01100001011000"));
        System.out.println(nextPrime(100));
        System.out.println(rightTriangle(145,105,100));
    }

    //№1: Эта функция считает количество действительных решений квадратного уравнения ax^2+bx+c=0 , где a,b,с - коэффы
    public static int solutions(int a, int b, int c) {
        int D = b*b-4*a*c;
        if(D > 0) return 2;
        else if (D == 0) return 1;
        return 0;
    }

    //№2: Эта функция возвращает позицию второго вхождения "zip" в строку, или -1 если
    // второго вхождения не происходит
    public static int findZip(String s) {
        String temp[] = s.split("zip");
        String temp2 = s.replace("zip", "");
//        System.out.println(Arrays.toString(temp));
//        System.out.println(temp2);
        if((s.length() - temp2.length()) / 3 > 1) {
            if(temp.length > 1) {
                return temp[0].length()+temp[1].length()+3;
            } else {
                return temp[0].length()+3;
            }
        }
        return -1;
    }

    //№3: Эта функция проверяет, является ли целое число совершенным(его можно записать через сумму его
    // множителей)
    public static boolean checkPerfect(int n) {
        int sum = 0;
        for(int i = 2; i*i <= n; i++) {
            if(n%i == 0) {
                sum += i;
                sum += n/i;
            }
        }
        if(sum+1 == n || n == 0) {
            return true;
        }
        return false;
    }

    // №4: Эта функция возвращает строку, меняя первый и последний символ местами,
    // Если символов <2, возвращает несовместимо, если символы совпадают, то два - это пара
    public static String flipEndChars(String s) {
        if(s.length() < 2) return "Несовместимо.";
        if(s.charAt(0) == s.charAt(s.length()-1)) return "два-это пара.";
        return s.charAt(s.length()-1) + s.substring(1,s.length()-1) + s.charAt(0);
    }

    // №5: Эта функция проверяет, является ли строка допустимым шестнадцатеричным кодом(начинается с #,
    // имеет длину 6 символов, и допустимые символы 0-9, и буквы A-F, буквы могут быть прописными и строчными
    public static boolean isValidHexCode(String num) {
        if(num.length() != 7) return false;
        for(int i = 0; i < num.length(); i++) {
            char c = num.charAt(i);
            if(i == 0) {if(c != '#') return false;}
            else if(!(c >= '0' && c <= '9' || c >= 'a' && c <= 'f' || c >= 'A' && c <= 'F')) {
                return false;
            }
        }
        return true;
    }

    // №6: Эта функция проверяет равны ли кол-ва уникальных элементов в двух массивах
    public static boolean same(Integer[] arr1, Integer[] arr2) {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        for (int i : arr1) {
            if (!list1.contains(i)) {
                list1.add(i);
            }
        }
        for (int i : arr2) {
            if (!list2.contains(i)) {
                list2.add(i);
            }
        }
        return list1.size() == list2.size();
    }

    // №7: Эта функция проверяет, является ли положительное целое число n числом Капрекара, то есть число,
    // которое
    // после возведения в квадрат и разбиения на две лексикографические части равно сумме
    // двух полученных новых чисел
    public static boolean isKaprekar(int x) {
        String s = Integer.toString(x*x);
        if(s.length() == 1) {
            return Integer.parseInt(s) == x;
        }
        return Integer.parseInt(s.substring(0, s.length()/2))+Integer.parseInt(s.substring(s.length()/2, s.length())) == x;
    }

    // №8: Эта функция возвращает самую длинную последовательность последовательных нулей в двоичной строке
    public static String longestZero(String s) {
        char[] carr = s.toCharArray();
        int max = 0;
        int curr = 0;
        for(char c : carr) {
            if(c == '0') {
                curr++;
            } else {
                if(curr > max) {
                    max = curr;
                }
                curr = 0;
            }
        }
        if(curr > max) {
            max = curr;
        }
        String temp = "";
        for(int i = 0; i < max; i++) {
            temp += '0';
        }
        return temp;
    }


    // №9: Эта функция возвращает следующее простое число, если заданное число не простое, если число простое вернуть его
    public static int nextPrime(int n) {
        while(!isPrime(n)) {
            n++;
        }
        return n;
    }
    private static boolean isPrime(int x) {
        for(int i = 2; i*i <= x; i++) {
            if(x%i == 0) {
                return false;
            }
        }
        return true;
    }


    // №10: Эта функция определяет, являются ли три числа x,y,z ребрами прямоугольного треугольника
    public static boolean rightTriangle(int a, int b, int c) {
        int [] arr = {a,b,c};
        Arrays.sort(arr); // по возрастанию
//        System.out.println(Arrays.toString(arr));
        a = arr[0];
        b = arr[1];
        c = arr[2];
        return a*a+b*b == c*c;
    }

//    //Converts an array of string to an array of integers
//    protected static int[] strArrToIntArr(String array[]) {
//        int nums[] = new int[array.length];
//        for(int i = 0; i < array.length; i++) {
//            nums[i] = Integer.parseInt(array[i]);
//        }
//        return nums;
//    }
}
