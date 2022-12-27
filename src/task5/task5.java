package task5;

import java.util.*;
import java.util.stream.*;
import java.text.DecimalFormat;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class task5 {
    public static void main(String[] args) {

        System.out.println("task 1");
        System.out.println(encrypt("Hello"));
        System.out.println(encrypt("Sunshine"));
        System.out.println(decrypt(72, 33, -73, 84, -12, -3, 13, -13, -68));
        System.out.println();

        System.out.println("task 2");
        System.out.println(canMove("ладья", "A8", "H8"));
        System.out.println(canMove("слон", "A7", "G1"));
        System.out.println(canMove("ферзь", "C4", "D6"));
        System.out.println();

        System.out.println("task 3");
        System.out.println(canComplete("butl", "beautifulqq"));
        System.out.println(canComplete("butlz", "beautiful"));
        System.out.println(canComplete("tulb", "beautiful"));
        System.out.println(canComplete("bbutl", "beautiful"));
        System.out.println();

        System.out.println("task 4");
        System.out.println(sumDigProd(new int[]{16, 28}));
        System.out.println(sumDigProd(new int[]{0}));
        System.out.println(sumDigProd(new int[]{1, 2, 3, 4, 5, 6}));
        System.out.println();

        System.out.println("task 5");
        System.out.println(sameVowelGroup("toe", "ocelot", "maniac"));
        System.out.println(sameVowelGroup("many", "carriage", "emit", "apricot", "animal"));
        System.out.println(sameVowelGroup("hoops", "chuff", "bot", "bottom"));
        System.out.println();

        System.out.println("task 6");
        System.out.println(validateCard("1234567890123456L"));
        System.out.println(validateCard("1234567890123452L"));
        System.out.println();

        System.out.println("task 7");
        System.out.println(numToEng(0));
        System.out.println(numToEng(18));
        System.out.println(numToEng(126));
        System.out.println(numToEng(909));
        System.out.println(numToRus(0));
        System.out.println(numToRus(18));
        System.out.println(numToRus(126));
        System.out.println(numToRus(909));
        System.out.println();

        System.out.println("task 8");
        System.out.println(getSha256Hash("password123"));
        System.out.println(getSha256Hash("Fluffy@home"));
        System.out.println(getSha256Hash("Hey dude!"));
        System.out.println();

        System.out.println("task 9");
        System.out.println(correctTitle("jOn SnoW, kINg IN thE noRth."));
        System.out.println(correctTitle("sansa stark, lady of winterfell."));
        System.out.println(correctTitle("TYRION LANNISTER, HAND OF THE QUEEN."));
        System.out.println();

        System.out.println("task 10");
        hexLattice(1);
        System.out.println();
        hexLattice(7);
        System.out.println();
        hexLattice(19);
        System.out.println();
        hexLattice(21);
        System.out.println();

    }


    //1  Криптография через разницу кода символов
    public static String encrypt(String line) {
        // берём первый символ и добавляем в массив, размер которого равен длине строки
        int[] crypt = new int[line.length()];
        crypt[0] = line.charAt(0);
        // добавляем разность индексов с предыдущим символом в массив
        for (int i = 1; i < line.length(); i++) {
            crypt[i] = line.charAt(i) - line.charAt(i - 1);
        }
        return Arrays.toString(crypt);
    }

    public static StringBuilder decrypt(int... crypt) {
        // создаём изменяемую строку, добавляем туда первый символ
        StringBuilder line = new StringBuilder(crypt.length);
        int sum = crypt[0];
        line.append((char) sum);
        // для того, чтобы находить последующие, суммируем значения разности индексов символов,
        // добавляя по индексу символы в строку
        for (int i = 1; i < crypt.length; i++) {
            sum += crypt[i];
            line.append((char) sum);
        }
        return line;
    }


    //2 шахматы
    public static boolean canMove(String figure, String startp, String endp) {
        int startx = startp.charAt(0) - 'A';
        int starty = startp.charAt(1) - '1';
        int endx = endp.charAt(0) - 'A';
        int endy = endp.charAt(1) - '1';
        if(figure.equals("BottomPawn")) {
            if(startx == endx && endy-starty < 3 && starty < endy) return true;
            return false;
        } else if(figure.equals("TopPawn")) {
            if(startx == endx && endy-starty > -3 && starty > endy) return true;
            return false;
        } else if(figure.equals("Rook")) {
            if(startx == endx || starty == endy) return true;
            return false;
        } else if(figure.equals("Knight")) {
            if((Math.abs(startx-endx) == 2 && Math.abs(starty-endy) == 1) || (Math.abs(startx-endx) == 1 && Math.abs(starty-endy) == 2)) return true;
            return false;
        } else if(figure.equals("Bishop")) {
            if(Math.abs(startx-endx) == Math.abs(starty-endy)) return true;
            return false;
        } else if(figure.equals("Queen")) {
            if(Math.abs(startx-endx) == Math.abs(starty-endy) || startx == endx || starty == endy) return true;
            return false;
        } else if(figure.equals("King")) {
            if(Math.abs(startx-endx) < 2 && Math.abs(starty-endy) < 2) return true;
            return false;
        }
        return false;
    }

    //3 завершённость строки
    public static boolean canComplete(String subline, String line) {
        // Идём по строке, сравнивая поочерёдно с каждм символом субстроки.
        // Если длинна субстроки равна счётку совпадений, true
        int count = 0;
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == subline.charAt(count)) {
                count++;
                if (subline.length() == count) return true;
            }
        }
        return false;
    }

    //4 сложение чисел массива, и перемножение цифр суммы, до 1 цифры
    public static int sumDigProd(int[] arr) {
        int n = 0;
        for (int i = 0; i < arr.length; i++) {
            n += arr[i];
        }
        while(n/10 > 0) {
            int temp = 1;
            String strtemp = Integer.toString(n);
            for(int i = 0; i < strtemp.length(); i++) {
                char c = strtemp.charAt(i);
                temp *= Integer.parseInt(Character.toString(c));
            }
            n = temp;
        }
        return n;
    }

    //5 совпадение глассных
    public static List<String> sameVowelGroup(String... lines) {
        // Создаём результирующий список, массив уникальных эллементов
        List<String> result = new ArrayList<>();
        Set<Character> set = new HashSet<>();
        // по умолчанию вводим первое слово, вносим все его гласные в set, глассные находим через регулярку
        result.add(lines[0]);
        String word1 = lines[0].replaceAll("[^aeiouy]", "");
        for (int i = 0; i < word1.length(); i++) set.add(word1.charAt(i));
        // идём по всем строка, начиная со второй, сравниваем глассные с теми, что есть в set
        for (int i = 1; i < lines.length; i++) {
            String word2 = lines[i].replaceAll("[^aeiouy]", "");
            boolean k = true;
            for (char c : word2.toCharArray())
                if (!set.contains(c)) {
                    k = false;
                    break;
                }
            if (k) result.add(lines[i]);
        }
        return result;
    }

    //6 банковские карты
    public static boolean validateCard(String s) {
        if(s.length() < 14 || s.length() > 19) return false;
        int sum = 0;
        for(int i = 0; i <= s.length()-2; i++) {
            int temp = Integer.parseInt(Character.toString(s.charAt(s.length()-i-2)))*((1-i%2)+1);
            if (temp/10 > 0) {
                sum += temp/10;
                sum += temp%10;
            } else {
                sum += temp;
            }
        }
        return Integer.toString(10-sum%10).charAt(0) == (s.charAt(s.length()-1));
    }

    //7 число в литерацию
    public static StringBuilder numToEng(int n) {
        StringBuilder result = new StringBuilder();
        String[] SUBTWENTY = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
                "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};
        String[] DECADES = {"twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};
        if (n / 100 > 0) result.append(String.format("%s hundred ", SUBTWENTY[n / 100]));
        if (n % 100 >= 20) result.append(String.format("%s ", DECADES[n % 100 / 10]));
        if (n % 10 > 0 || n == 0) result.append(SUBTWENTY[n % 20]);
        return result;
    }

    public static StringBuilder numToRus(int n) {
        StringBuilder result = new StringBuilder();
        String[] SUBTWENTY = {"ноль", "один", "два", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять",
                "десять", "одинадцать", "двенадцать", "тринадцать", "четырнадцать", "пятнадцать", "шестнадцать",
                "семнадцать", "восемнадцать", "девятнадцать"};
        String[] DECADES = {"двадцать", "тридцать", "сорок", "пятьдесят",
                "шестьдесят", "семьдесят", "восемьдесят", "девяносто"};
        String[] HUNDREDS = {"сто", "двесте", "триста", "четыреста",
                "пятьсот", "шестьсот", "семьсот", "восемьсот", "девятьсот"};
        if (n / 100 > 0) result.append(String.format("%s ", HUNDREDS[n / 100 - 1]));
        if (n % 100 >= 20) result.append(String.format("%s ", DECADES[n % 100 / 10 - 2]));
        if (n % 10 > 0 || n == 0) result.append(SUBTWENTY[n % 20]);
        return result;
    }

    //8 SHA-256
    public static String getSha256Hash(String Line) {
        try {
            // MessageDigest представляет криптографическую хеш-функцию, которая
            // может вычислять дайджест (хэш-сумма) сообщения из двоичных данных.
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(Line.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (int i : hash) {
                // из шестн. сс. в строку
                String hex = Integer.toHexString(0xff & i);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    //9 Титул
    public static String correctTitle(String text) {
        StringBuilder right = new StringBuilder(text.toLowerCase());
        //выставляем первый символ заглавным, если это буква
        right.setCharAt(0, Character.toUpperCase(text.charAt(0)));
        //крутимся в цикле, и меняем буквы, перед которыми пробел на заглавные +
        // требует проверки, что слово не является служебной частью речи
        for (int i = 1; i < text.length(); i++)
            if (Character.isSpaceChar(text.charAt(i - 1)) && !right.substring(i, i + 3).matches
                    ("of |in |the|and")) right.setCharAt(i, Character.toUpperCase(text.charAt(i)));
        return right.toString();
    }

    //10 Гексогон
    public static void hexLattice(int n) {
        // вычисляем модификатор, присваиваем его n
        // модификатор - центрированное шестиугольное число
        for (int i = 1; n >= 1; i++) {
            if (n == 1) {
                n = i;
                break;
            }
            n -= i * 6;
        }
        if (n < 0) System.out.println("Invalid");
        // проход с модификатором, как можно обойтись без space?
        // который бы контролировал отступ с динамичным n
        int space = 1 + (n - 1) * 2;
        for (int i = space; i > 0; i--) {
            System.out.println(" ".repeat(Math.abs(space + 1 - n)) + "o ".repeat(n));
            if (n <= i) n++;
            else n--;
        }
    }



// ________________________________________________


    //Преобразует массив строк в массив целых чисел
    protected static int[] strArrToIntArr(String array[]) {
        int nums[] = new int[array.length];
        for(int i = 0; i < array.length; i++) {
            nums[i] = Integer.parseInt(array[i]);
        }
        return nums;
    }

    //Преобразует массив строк в массив doubles
    protected static double[] strArrToDoubleArr(String array[]) {
        double nums[] = new double[array.length];
        for (int i = 0; i < array.length; i++) {
            nums[i] = Double.parseDouble(array[i]);
        }
        return nums;
    }

    //Отображает массив целых чисел
    protected static void displayInts(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }


    //Отображает список строк
    protected static void displayStringList(List<String> arr) {
        for (int i = 0; i < arr.size(); i++) {
            System.out.println(arr.get(i));
        }
    }


}
