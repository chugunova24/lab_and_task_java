package task6;

import java.util.*;
import java.util.stream.*;
import java.text.DecimalFormat;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//Main class
public class task6 {
    public static void main(String[] args) {
        System.out.println("\n");
        System.out.println("task1");
        System.out.println(bell(1));
        System.out.println(bell(2));
        System.out.println(bell(3));
        System.out.println("\n");
        System.out.println("task2");
        System.out.println(translateSentence("flag"));
        System.out.println(translateSentence("Apple"));
        System.out.println(translateSentence("button"));
        System.out.println(translateSentence(""));
        System.out.println(translateSentence("I like to eat honey waffles."));
        System.out.println(translateSentence("Do you think it is going to rain today?"));
        System.out.println("\n");
        System.out.println("task3");
        System.out.println(validColor("rgb(0,0,0)"));
        System.out.println(validColor("rgb(0,,0)"));
        System.out.println(validColor("rgb(255,256,255)"));
        System.out.println(validColor("rgba(0,0,0,0.123456789)"));
        System.out.println("\n");
        System.out.println("task4");
        System.out.println(stripUrlParams("https://edabit.com?a=1&b=2&a=2", new String[]{""}));
        System.out.println(stripUrlParams("https://edabit.com?a=1&b=2&a=2", new String[]{"b"}));
        System.out.println(stripUrlParams("https://edabit.com",new String[]{"b"}));
        System.out.println("\n");
        System.out.println("task5");
        displayStringArray(getHashTags("How the Avocado Became the Fruit of the Global Trade"));
        displayStringArray(getHashTags("Why You Will Probably Pay More for Your Christmas Tree This Year"));
        displayStringArray(getHashTags("Hey Parents, Surprise, Fruit Juice Is Not Fruit"));
        displayStringArray(getHashTags("Visualizing Science"));
        System.out.println("\n");
        System.out.println("task6");
        System.out.println(ulam(4));
        System.out.println(ulam(16));
        System.out.println(ulam(206));
        System.out.println("\n");
        System.out.println("task7");
        System.out.println(longestNonrepeatingSubstring("abcabcbb"));
        System.out.println(longestNonrepeatingSubstring("aaaaaa"));
        System.out.println(longestNonrepeatingSubstring("abcde"));
        System.out.println(longestNonrepeatingSubstring("abcda"));
        System.out.println("\n");
        System.out.println("task8");
        System.out.println(convertToRoman(2));
        System.out.println(convertToRoman(12));
        System.out.println(convertToRoman(16));
        System.out.println("\n");
        System.out.println("task9");
        System.out.println(formula("6 * 4 = 24"));
        System.out.println(formula("18 / 17 = 2"));
        System.out.println(formula("16 * 10 = 160 = 14 + 120"));
        System.out.println("\n");
        System.out.println("task10");
        System.out.println(palindromedescendant(11211230));
        System.out.println(palindromedescendant(13001120));
        System.out.println(palindromedescendant(23336014));
        System.out.println(palindromedescendant(11));






    }

    //Найти число белла через треугольный метод
    static int bell(int n) {
        int[][] bell = new int[n+1][n+1];
        // Начнем с единицы. Помещаем ее в верхнюю строку.
        bell[0][0] = 1;
        for(int i=1; i<=n; i++) {
            //Явно установить значение для 0.
            // Каждая новая строка должна начинаться с
            // крайнего правого элемента прошлой строки.
            bell[i][0] = bell[i-1][i-1];
            // Заполнить остальные значения.
            //Заполняем строчку i по формуле:
            for (int j=1; j<=i; j++)
                bell[i][j] = bell[i-1][j-1] + bell[i][j-1];
        }
        //Крайнее левое число данной строки является числом Белла для этой строки
        return bell[n][0];
    }

    //Переводчик слова на свинский латинский
    public static String translateWord(String s, boolean capitalFlag) {
        //System.out.println(s);
        //проверка на пустую. строку
        if (s.isEmpty()){return "";};
        char c = s.charAt(0);
        //если нулевой символ явлется гласной и у нас стоит флаг тру, то
        // делаем букву заглавной и прибавляем окончание yay
        if(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
            if(capitalFlag) {
                return Character.toString(s.charAt(0)).toUpperCase().concat(s.substring(1).concat("yay"));
            }
            return s.concat("yay");
        }

        //если слово не является гласной, то добавляем ay в конец
        if(capitalFlag) {
            return Character.toString(s.charAt(1)).toUpperCase().concat(s.substring(2).concat(Character.toString(c)).concat("ay"));
        }
        return s.substring(1).concat(Character.toString(c)).concat("ay");
    }

    //Переводчик предложений на свинский латинский
    // здесь то же самое, только с помощью регулярок игнорируем пунктуацию
    //
    public static String translateSentence(String s) {
        String result = "";
        if(s.equals("")) return "";
        Pattern p = Pattern.compile("[a-zA-Z0-9]+");
        Matcher m = p.matcher(s);
        boolean first = true;
        boolean capitalFlag = false;
        int pe = 0;
        try {
            // hitEnd - Возвращает true, если поисковый механизм достиг конца ввода
            // во время последней операции сопоставления, выполненной этим сопоставителем.
            //find() возвращает значение первого найденного в массиве элемента,
            // которое удовлетворяет условию переданному в callback функции
            while(!m.hitEnd()) {
                m.find();
                if(s.charAt(m.start()) >= 'A' && s.charAt(m.start()) <= 'Z') {
                    capitalFlag = true;
                }
                result = result+s.substring(pe,m.start())+translateWord(s.substring(m.start(),m.end()).toLowerCase(),capitalFlag);
                capitalFlag = false;
                pe = m.end();
            }
        } catch(Exception err) {
            //пустота
        }
        return result;
    }

    //Проверяет, является ли строка допустимым форматом rbg;
    public static boolean validColor(String s) {
        //создаем шаблон
        Pattern p = Pattern.compile("rgb(a|)\\([0-9]+,[0-9]+,[0-9]+(,[0-9.]+|)\\)");
        //сравниваем строку с шаблоном
        Matcher m = p.matcher(s);
        // если строка соотв. шаблону, мы ее сплитим, проверяем цифры на валидность и
        // пустоту
        if(m.matches()) {
            String temp1 = s.split("\\(")[1].split("\\)")[0];
            String[] sarr = temp1.split(",");
            if(Integer.parseInt(sarr[0]) > 255 || Integer.parseInt(sarr[1]) > 255 || Integer.parseInt(sarr[2]) > 255) return false;
            if(s.charAt(3) == 'a' && (Double.parseDouble(sarr[3]) > 1.0 || Double.parseDouble(sarr[3]) < 0.0)) return false;
        } else return false;
        return true;
    }

    //Удаляем дублирующиеся параметры запроса и параметры,
    // указанные во втором аргументе
    //"https://edabit.com?a=1&b=2&a=2
    public static String stripUrlParams(String s, String[] exclude) {
        //если юрл не содержит знак вопроса, то аргумментов нет и возвращаем
        //юрл как есть
        if(!s.contains("?")) return s;
        // сплитим строку на две части из префикса+хоста и аргументы
        String[] ss = s.split("\\?");
        //аргументы тоже сплитим
        String[] args = ss[1].split("&");
        // создаем карту
        HashMap<String, String> hm = new HashMap<>();
        String resultParams = "";
        //перечисляем все аргументы, сплитим их пополам(на переменную и значение)
        //если лист содержит параметр, то мы в хэшмап добавляем новый объект
        for(String temp1 : args) {
            String[] temp = temp1.split("=");
            //asList возвращает List. V put(K k, V v) : помещает в коллекцию
            // новый объект с ключом k и значением
            if(!Arrays.asList(exclude).contains(temp[0])) hm.put(temp[0], temp[1]);
        }
        boolean first = true;
        //итерируем по хэшмапу. в результат (состоящий из параметров) записываем параметры
        // .конкатенируем с первой частью строки и возвращаем значение
        for(String key : hm.keySet()) {
            if(first) {
                resultParams = resultParams.concat("?"+key+"="+hm.get(key));
                first = false;
            }
            else resultParams = resultParams.concat("&"+key+"="+hm.get(key));
        }
        return ss[0].concat(resultParams);
    }

    //5.Извлечение хэштэгов
    public static String[] getHashTags(String s) {
        String a = "";
        String b = "";
        String c = "";
        //создаем шаблон, который игнорирует пунктуацию (Набор из букв и цифр)
        Pattern p = Pattern.compile("[a-zA-Z0-9]+");
        //ищем совпадения
        Matcher m = p.matcher(s);
//        System.out.println("KNOENPJPCDC");
//        System.out.println(m.find());
        boolean first = true;
        boolean capitalFlag = false;
        int pe = 0;
        try {
            // hitEnd - Возвращает true, если поисковый механизм достиг конца ввода
            // во время последней операции сопоставления, выполненной этим сопоставителем.
            //find() возвращает значение первого найденного в массиве элемента,
            // которое удовлетворяет условию переданному в callback функции
            while(!m.hitEnd()) {
                m.find();
                //Math. min() возвращает наименьшее из нуля или более чисел.
                int length = m.end()-m.start();
                int mins = Math.min(a.length(),Math.min(b.length(),c.length()));
                if(mins < length) {
                    if(mins == a.length()) {
                        a = s.substring(m.start(), m.end()).toLowerCase();
//                            System.out.println("KNOENPJPCDC");
//                            System.out.println(mins);

                    } else
                    if(mins == b.length()) {
                        b = s.substring(m.start(), m.end()).toLowerCase();
                    } else
                    if(mins == c.length()) {
                        c = s.substring(m.start(), m.end()).toLowerCase();

                    }
                }
            }
        } catch(Exception err) {
            //пустота
        }
        String[] result = new String[]{a,b,c};
        //сортируем слова, сравниваем
        Arrays.sort(result, new Comparator < String > () {
            public int compare(String one, String two) {
                int val = two.length() - one.length();
                // В Java compareToIgnoreCase() возвращает отрицательное
                // целое число, ноль или положительное целое число,
                // если заданная строка меньше, равна или больше сравниваемой строки,
                // игнорируя регистр. Метод compareToIgnoreCase() – в Java сравнивает
                // лексически две строки, игнорируя регистр букв.
                //Если разница равна нулю, то val присваем
                if (val == 0) val = one.compareToIgnoreCase(two);
                return val;
            }
        });
        return result;
    }

    //Найти указанное число Улама. Полная матрица (квадрат)
    public static int ulam(int n) {
        ArrayList<Integer> arr = new ArrayList<>();
        //последовательность начинается с 1 и 2
        arr.add(1);
        arr.add(2);
        int i, j;
        for (i = 3, j = 2; j < n; i++) {
            int cnt = 0;
            //следующее число - это наименьшее положительное число, равное
            // сумме двух разных чисел(кот. уже есть в последовательности)
            for (int k = 0; k < arr.size() - 1; k++) {
                for (int l = k + 1; l < arr.size(); l++) {
                    if (arr.get(k) + arr.get(l) == i) cnt++;
                    if (cnt > 1) break;
                }
                //если счетчик больше единицы, прерываемся
                if (cnt > 1) break;
            }
            //если счетчик равен единице, добавляем в массив значение i и +1 к j
            if (cnt == 1) {
                arr.add(i);
                j++;
            }
        }
        //вычитаем из результата единицу
        return i - 1;
    }

    //Найти самую длинную подстроку, где все символы уникальные
    public static String longestNonrepeatingSubstring(String str) {
        String res = "", temp = "";
        //итерируем по строке. Если наш шаблон не содержит символ из строки(str),
        // то к temp добавляем этот символ
        //в ином случае если длина шаблона больше длины res, то res=temp, при этом temp очищаем.
        // а j присваиваем значение длины строки(str)
        for(int i = 0; i < str.length(); ++i){
            for(int j = i; j < str.length(); ++j){
                if(!temp.contains(String.valueOf(str.charAt(j)))) temp += str.charAt(j);
                else {
                    if(temp.length() > res.length()) res = temp;
                    temp = "";
                    j = str.length();
                }
            }
        }
        return res;
    }


    // Конвертированние в римские цифры
    public static String convertToRoman(int num) {
        String fs = "";
        while(num != 0){
            if(num >= 1000){
                num -= 1000;
                fs += "M";
            }
            else if(num >= 900){
                num -= 900;
                fs += "CM";
            }
            else if(num >= 500){
                num -= 500;
                fs += "D";
            }
            else if(num >= 400){
                num -= 400;
                fs += "CD";
            }
            else if(num >= 100){
                num -= 100;
                fs += "C";
            }
            else if(num >= 90){
                num -= 90;
                fs += "XC";
            }
            else if(num >= 50){
                num -= 50;
                fs += "L";
            }
            else if(num >= 40){
                num -= 40;
                fs += "XL";
            }
            else if(num >= 10){
                num -= 10;
                fs += "X";
            }
            else if(num >= 9){
                num -= 9;
                fs += "IX";
            }
            else if(num >= 5){
                num -= 5;
                fs += "V";
            }
            else if(num >= 4){
                num -= 4;
                fs += "IV";
            }
            else if(num > 0){
                num -= 1;
                fs += "I";
            }
        }
        return fs;
    }

    //9.Калькулятор.
    //здесь убираем пробелы для удобства
    static int a = 4;
    public static int calc(String str) {
        str = str.replaceAll(" ", "");
        if(str.equals("a"))
            return a;
        try {
            //парсим строку на инт, в случае исключения NumberFormatException
            // сплитим строку, при этом учитывая по какому символу мы сплитим.
            //если умножение - перемножаем первые два символа и т.д.
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            String[] tokens;
            tokens = str.split("\\*", 2);
            if (tokens.length > 1) {
                return calc(tokens[0]) * calc(tokens[1]);
            }
            tokens = str.split("/", 2);
            if (tokens.length > 1) {
                return calc(tokens[0]) / calc(tokens[1]);
            }
            tokens = str.split("\\+", 2);
            if (tokens.length > 1) {
                return calc(tokens[0]) + calc(tokens[1]);
            }
            tokens = str.split("-", 2);
            if (tokens.length > 1) {
                return calc(tokens[0]) - calc(tokens[1]);
            }
            return 0;
        }
    }
    //Проверка валидности формулы. Проверяем шоб все части формулы были равны между собой
    public static boolean formula(String str) {
        String[] equations = str.split("=");
        for (int i = 0; i < equations.length-1; i++) {
            if(calc(equations[i]) != calc(equations[i+1])) {
                return false;
            }
        }
        return true;
    }


    //Находим, если число палиндром или его потомок.
    // Число может не быть полиндромом, но его потомком моэжет быть.
    // Прямой поток числа создается путем суммирования каждой пары соседних цифр,
    //чтобы создать цифры следующего числа
    public static boolean palindromedescendant(int num) {
        // выполняем следующие действия, пока число двухзначное
        while(num > 9) {
//            System.out.println("Number: "+num);
            //преставление числа в виде строки
            String snum = Integer.toString(num);
            boolean isPal = true;
            //сравниваем две половинки числа
            for(int i = 0; i < snum.length()/2; i++) {
//                System.out.println(snum.charAt(i)+" =?= "+snum.charAt(snum.length()-i-1));
                if(!(snum.charAt(i) == snum.charAt(snum.length()-i-1))) {
                    isPal = false;
                    break;
                }
            }
            if(isPal) return true;
            String newnum = "";
            //создаем потомков, складывая текущую и следующую цифры из числа, добавляя их сумму в новую строку
            for(int i = 0; i < snum.length(); i+=2) {
                newnum = newnum.concat(Integer.toString(Integer.parseInt(Character.toString(snum.charAt(i)))+Integer.parseInt(Character.toString(snum.charAt(i+1)))));
            }
            num = Integer.parseInt(newnum);
        }
        return false;
    }


    //Строка в интовый массив
    protected static int[] strArrToIntArr(String array[]) {
        int nums[] = new int[array.length];
        for(int i = 0; i < array.length; i++) {
            nums[i] = Integer.parseInt(array[i]);
        }
        return nums;
    }

    //строка в массив добле
    protected static double[] strArrToDoubleArr(String array[]) {
        double nums[] = new double[array.length];
        for(int i = 0; i < array.length; i++) {
            nums[i] = Double.parseDouble(array[i]);
        }
        return nums;
    }

    //Вывести массив интовый
    protected static void displayInts(int[] arr) {
        for(int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }


    //Вывести стринг лист
    protected static void displayStringList(List<String> arr) {
        for(int i = 0; i < arr.size(); i++) {
            System.out.println(arr.get(i));
        }
    }

    //Вывести стринг арей
    protected static void displayStringArray(String arr[]) {
        for(int i = 0; i < arr.length; i++) {
            System.out.println("#"+arr[i]);
        }
    }
}

