package task4;

import java.util.*;

public class task4 {
    public static void main(String[] args) {

        System.out.println("task 1");
        System.out.println(Bessy(10, 7, "hello my name is Bessie and this is my essay"));
        System.out.println();

        System.out.println("task 2");
        System.out.println(split("((()))"));
        System.out.println(split("((()))(())()()(()())"));
        System.out.println(split("((())())(()(()()))"));
        System.out.println();

        System.out.println("task 3");
        System.out.println(toCamelCase("hello_edabit"));
        System.out.println(toCamelCase("is_modal_open"));
        System.out.println(toSnakeCase("helloEdabit"));
        System.out.println(toSnakeCase("toSnakeCase"));
        System.out.println();

        System.out.println("task 4");
        System.out.println(overTime(9, 17, 30, 1.5F));
        System.out.println(overTime(16, 18, 30, 1.8F));
        System.out.println(overTime(13.25F, 15, 30, 1.5F));
        System.out.println();

        System.out.println("task 5");
        System.out.println(BMI("205 pounds", "73 inches"));
        System.out.println(BMI("55 kilos", "1.65 meters"));
        System.out.println(BMI("154 pounds", "2 meters"));
        System.out.println();

        System.out.println("task 6");
        System.out.println(bugger(39));
        System.out.println(bugger(999));
        System.out.println(bugger(4));
        System.out.println();

        System.out.println("task 7");
        System.out.println(toStarShorthand("abbvvccc"));
        System.out.println(toStarShorthand("77777geff"));
        System.out.println(toStarShorthand("abc"));
        System.out.println(toStarShorthand(""));
        System.out.println();

        System.out.println("task 8");
        System.out.println(doesRhyme("Sam I am!", "Green eggs and ham."));
        System.out.println(doesRhyme("Sam I am!", "Green eggs and HAM."));
        System.out.println(doesRhyme("You are off to the races", "a splendid day."));
        System.out.println(doesRhyme("and frequently do?", "you gotta move."));
        System.out.println(doesRhyme("and frequently deo?", "you gotta move."));
        System.out.println();

        System.out.println("task 9");
        System.out.println(trouble(451999277, 41177722899L));
        System.out.println(trouble(45100277, 41177700899L));
        System.out.println(trouble(1222345, 12345));
        System.out.println(trouble(666789, 12345667));
        System.out.println(trouble(33789, 12345337));
        System.out.println(trouble(1234555, 1231319191955L));
        System.out.println();

        System.out.println("task 10");
        System.out.println(countUniqueBooks("AZYWABBCATTTA", 'A'));
        System.out.println(countUniqueBooks("$AA$BBCATT$C$$B$", '$'));
        System.out.println(countUniqueBooks("ZZABCDEF", 'Z'));
        System.out.println();

    }


    //1 Не более определённого количества символов в строке, исключая пробелы

    public static String Bessy(int n, int k , String txt){
        String[] text = txt.split(" ");
        txt="";
        String finaltxt="";
        for (int i = 0; i < n; i++) {
            if (txt.length() + text[i].length() > k) {
                finaltxt = finaltxt.trim() + "\n" + text[i] + " ";
                txt = text[i];
            } else {
                finaltxt += text[i] + " ";
                txt += text[i];
            }
        }
        return finaltxt.trim();
    }

    //2 кластеры скобок
    public static ArrayList<String> split(String line) {
        int opened = 0;
        int begin = 0;
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < line.length(); i++) {
            // идём по символам строки
            char currentCharacter = line.charAt(i);
            //Считаем сколько открытых и закрытых скобок,
            //Если число равно 0 после добавления, собираем в кластер через индекс начальной и конечной скобы
            if (currentCharacter == '(') opened++;
            else if (currentCharacter == ')') opened--;
            if (opened == 0) {
                arrayList.add(line.substring(begin, i + 1));
                begin = i + 1;
            }
        }
        return arrayList;
    }

    //3 строку преобразует либо в camelCase, либо в snake_case.
    public static String toCamelCase(String line) {
        String[] concatLine = line.split("_");
        StringBuilder finaLine = new StringBuilder(concatLine[0]);
        //Добавляем все слова кроме первого в изменяемую строку, первый символ в верхний регист
        for (int i = 1; i < concatLine.length; i++) {
            String currentWord = concatLine[i];
            finaLine.append(currentWord.substring(0, 1).toUpperCase());
            finaLine.append(currentWord.substring(1));
        }
        return String.valueOf(finaLine);
    }


    public static String toSnakeCase(String line) {
        StringBuilder result = new StringBuilder();
        //Если верхний регистр, добавляет разделитель и переводит в нижний
        for (int i = 0; i < line.length(); i++) {
            char ch = line.charAt(i);
            if (Character.isUpperCase(ch)) {
                result.append('_');
                result.append(Character.toLowerCase(ch));
            } else {
                result.append(ch);
            }
        }
        return result.toString();
    }

    //4 зарплата со сверхурочными
    public static String overTime(float... massive) {
        // просто суммируем по формуле, время переработки находим через половину сумму времени переработки и модуля времени переработки
        double salary = (massive[1] - massive[0] + ((massive[1] - 17) + Math.abs(massive[1] - 17)) / 2 * (massive[3] - 1)) * massive[2];
        return '$' + String.format("%.2f", salary);
    }

    //5 Коэффициент BMI
    public static String BMI(String weight, String height) {
        String[] weightArray = weight.split(" ");
        String[] heightArray = height.split(" ");
        float BMI;
        //всё переводим в систему СИ
        float weightNumber = Float.parseFloat(weightArray[0]);
        if (weightArray[1].equals("pounds")) weightNumber *= 0.453592F;
        float heightNumber = Float.parseFloat(heightArray[0]);
        if (heightArray[1].equals("inches")) heightNumber *= 0.0254F;
        BMI = weightNumber / (heightNumber * heightNumber);
        String BMIString = String.format("%.1f", BMI);

        if (BMI < 18.5) {
            return BMIString + " Underweight";
        } else if (BMI < 25) {
            return BMIString + " Normal weight";
        }
        return BMIString + " Overweight";
    }

    //6 перемножение цифр до тех пор, пока не будит в числе одной цифры
    public static int bugger(int number) {
        int count = 0;
        while (number > 9) {
            int tempNumber = 1;
            while (number > 0) {
                tempNumber *= number % 10;
                number /= 10;
            }
            number = tempNumber;
            count++;
        }
        return count;
    }

    //7 стенография, посимвольная
    public static StringBuilder toStarShorthand(String line) {
        StringBuilder NewLine = new StringBuilder();
        line += " ";
        //записываем первый символ в изменяемую строку
        char last = line.charAt(0);
        int count = 1;
        for (int i = 1; i < line.length(); i++) {
            //Если символ равен предыдущему, увеличиваем счётчик, если нет, добавляем значение
            char a = line.charAt(i);
            if (a == last) count++;
            else if (count != 1) {
                NewLine.append(last).append('*').append(count);
                count = 1;
            } else {
                NewLine.append(last);
            }
            // при любых условиях предыдущий приравнивается к текущему
            last = a;
        }
        return NewLine;
    }

    //8 рифма
    public static boolean doesRhyme(String firstLine, String secondLine) {
        // Находим через регулярное выражение только глассные в обоих последних словах. Порядок глассных важен.
        String word1 = firstLine.substring(firstLine.lastIndexOf(' ')).toLowerCase().replaceAll("[^aeiouy]", "");
        String word2 = secondLine.substring(secondLine.lastIndexOf(' ')).toLowerCase().replaceAll("[^aeiouy]", "");
//        System.out.println("word2");
//        System.out.println(word2);
        return word2.equals(word1);
    }

    //9 включение подстроки на 3 и 2 подряд одинаковых символа в первом и втором натуральном числе соответственно

    // нули не обработ
    public static boolean trouble(long firstNumber, long secondNumber) {
        //проверяем содержит ли строка одну из 10 комбинаций
        String first = Long.toString(firstNumber);
        String second = Long.toString(secondNumber);
        for (int i = 1000; i < 2000; i += 111)
            if (first.contains(Integer.toString(i % 1000)) && second.contains(Integer.toString(i % 100)))
                return true;
        return false;
    }

    //10 книги
    public static int countUniqueBooks(String line, char marker) {
        // Hashset хранит только уникальные эллементы
        Set<Character> characters = new HashSet<>();
        boolean flag = false;
        // если встречен первый разделитель, пока не будет встречен второй, все символы буду добавляться в set
        for (int i = 0; i < line.length(); i++) {
            char currentCharacter = line.charAt(i);
            if (currentCharacter == marker) {
                flag = !flag;
            } else if (flag) characters.add(currentCharacter);
        }
        return characters.size();
    }
}