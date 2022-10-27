package task2;


import java.util.Arrays;
import java.util.OptionalInt;

public class task2 {
    public static void main(String[] args) {
        System.out.println("Введите значение: ");
        String input = System.console().readLine();




//        System.out.println(isValid(input));


//        int n = Integer.parseInt(input);
//        System.out.println(Fibonacci(n));


//        System.out.println(getDecimalPlaces(input));
        

//        String[] inp =  input.split(" ");
//        int[] inpArr = new int[inp.length];
//        for (int i = 0; i < inp.length; i++) {
//            inpArr[i] = Integer.parseInt(inp[i]);
//        }
//        System.out.println(Arrays.toString(cumulativeSum(inpArr)));


//        String[] inp =  input.split(" ");
//        int[] inpArr = new int[inp.length];
//        for (int i = 0; i < inp.length; i++) {
//            inpArr[i] = Integer.parseInt(inp[i]);
//        }
//        System.out.println(isAvgWhole(inpArr));


//        String[] inp =  input.split(" ");
//        int[] inpArr = new int[inp.length];
//        for (int i = 0; i < inp.length; i++) {
//            inpArr[i] = Integer.parseInt(inp[i]);
//        }
//        System.out.println(differenceMaxMin(inpArr));


//        System.out.println(repeat(input, 1));
    }

    public static int[] array(String input){
        String[] inp =  input.split(" ");
        int[] inpArr = new int[inp.length];
        for (int i = 0; i < inp.length; i++) {
            inpArr[i] = Integer.parseInt(inp[i]);
        }
        return inpArr;
    }



    // 1 программа увечиливает количество каждого символа в n раз
    public static String repeat(String inpArr, int n){
        StringBuilder sb = new StringBuilder();
        String[] inp = inpArr.split("");
        for (int j = 0; j<inp.length; j++){
            for (int i = 1; i<=n; i++){
                sb.append(inp[j]);
            }
        }
        return sb.toString();
    }


    // 2 находит разницу между минимум и максимумом массива
    // минимуму присваивам максимально возможное число
    // максимуму - минимально возможное
    // находим минимум и максимум введенного массива
    // и возвращаем разницу между этими числами
    public static int differenceMaxMin(int[] inpArr){
        Arrays.sort(inpArr);
        int max = Arrays.stream(inpArr).max().getAsInt();
        int min = Arrays.stream(inpArr).min().getAsInt();
//        System.out.println(Arrays.toString(inpArr));
        return max-min;
    }


    // 3 проверяет, является ли среднее значение всех элементов целым числом
    // суммируем все элементы массива
    // если среднее арифметическое целое число - возвращаем true
    // если нет - false
    public static boolean isAvgWhole(int[] inpArr){
        double a = Arrays.stream(inpArr).average().getAsDouble();
        return a%1==0;
    }


    // 4 возвращает массив
    // в котором каждое целое число - сумма самого себя
    // и всех предыдущих чисел
    public static int[] cumulativeSum(int[] inpArr){
        int[] result = new int[inpArr.length];
        int sum = 0;
        for (int i = 0; i < inpArr.length; i++) {
            sum += inpArr[i];
            result[i] = sum;
        }
        return result;
    }


    //5 считает число десятичных знаков
    // если в числе есть точка
    // возвращаем длинну числа - порядковый номер точки - 1
    // если точки нет - возвращаем 0
    public static int getDecimalPlaces(String inpArr){
        String[] inp = inpArr.split("");
        int index_dot = 0;
        for (int i = 0; i < inp.length; i++){
            if (inp[i].equals(".")) index_dot = i;
        }
        System.out.println(index_dot);

        if (index_dot != 0) return inp.length - index_dot-1;
        else return 0;
    }


    //6 возвращается n-ое число Фибоначчи
    // если просим вывести 0 или 1 число Фибоначчи,
    // то возвращаем 1
    public static int Fibonacci(int n){
        int a = 0;
        int b = 1;
        for (int i = 1; i <= n; ++i) {
            int next = a + b;
            a = b;
            b = next;
        }
        return b;
    }



    // 7 проверяет , првильно ли введен индекс
    // если в введенном индексе есть буквы, пробелы или не 5 символов
    // то счетчик принимает не нулевое значение
    // в противном случае выводится true

    public static boolean isValid(String index){
        int buf = 0;
        for (int i = 0; i < index.length(); i++) {
            if (!Character.isDigit(index.charAt(i)))
                buf++;
            else if (index.charAt(i) == ' ')
                buf++;
            else if (index.length() != 5)
                buf++;
        }
        return buf == 0;
    }


    //8 странная пара
    // если первый символ первого слова и последний второго совпадают
    // и первый элемент второго слова совпадает с последним элементом первого
    // то вернется true

    public static boolean isStrangePair (String word1, String word2){
        return word1.charAt(0) == word2.charAt(word2.length() - 1) &&
                word2.charAt(0) == word2.charAt(word1.length() - 1);
    }


    //9 проверяем является ли данный суффикс или префикс частью введенного слова
    //убираем из префикса/суффикса -
    // проверяем начинается/заканчивается ли введенное слово на суффикс/префикс

    public static boolean isPrefix(String word, String pref){
        pref =  pref.substring(0,  pref.length()-1);
        //System.out.println(pref);
        return word.startsWith(pref);
    }
    public static boolean isSuffix(String word, String suf){
        suf = suf.substring(1);
        //System.out.println(suf);
        return word.endsWith(suf);
    }


    // 10 Создайте функцию, которая принимает число (шаг) в качестве аргумента
    // и возвращает количество полей на этом шаге последовательности.
    // если шаг = 0 , возвращаем 0
    // создаем цикл, "делающий" шаги
    // если шаг четный, то к счетчику прибавляем 3
    // если шаг не четный, то из счетчика вычитается 1

    public static int boxSeq(int shag){
        if (shag == 0) return 0;
        int count = 0;
        for (int i = 0; i < shag; i++) {
            if (i % 2 != 0) count--;
            else count += 3;
        }
        return count;
    }







}
