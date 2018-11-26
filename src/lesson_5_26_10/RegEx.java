package lesson_5_26_10;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegEx {
    public static void main(String[] args) {
        String string = "First [] [String] number 1. SYMBOL";
        String regEx = "rst";
        System.out.println(string.replaceAll(regEx, "*"));
        regEx = "^Fi";//^ - Начало строки, $ - конец строки
        System.out.println(string.replaceAll(regEx,"*"));
        regEx = "[irg]";// [] - перечень символов
        System.out.println(string.replaceAll(regEx,"*"));
        regEx = "[^irg]"; // [^] - все кроме указанных
        System.out.println(string.replaceAll(regEx,"*"));
        regEx = "\\d"; // цифровой символ
        System.out.println(string.replaceAll(regEx,"*"));
        regEx = "\\D"; // не цифровой
        System.out.println(string.replaceAll(regEx,"*"));
        // \\w - буквенно цифровой символ или _
        // \\W - не
        // \\s - любой пробельный символ
        // \\S - не пробельный символ
        regEx = "[a-z]"; // все нижние указанные
        System.out.println(string.replaceAll(regEx,"*"));
        // [А-Я] - все заглавные указанные ё НЕ СЧИТАЕТСЯ
        regEx = "[A-Z0-9]";// все заглавные и цифры == [A-Z,0-9]
        //[A-Z][0-9] - только большая и следующее за ней число
        regEx = "i{2,4}";// только символы i которые повторояются от 2 до 4 раз
        //. - любой символ
        // \\[\\] - замена []
        // \\n - замена \n
        // Pattern | Matcher
        string = "String B1 to search# and 'replace' CHARS";
        //string = "Str";
        regEx = "(B1|ea)";
        Pattern pattern = Pattern.compile(".{6,}");// . - любой символ в строке     {x,y} количество повторений от x до y
        pattern = Pattern.compile("([?=^])(?=.*\\d)(?=.*\\[A-Z]).{8,}");//
        //() - разбиение на подвыражения
        // ?=.*\\d - любой символ(.) 0 и более раз(*) а потом число(\\d)
        // ?= - поиск совпадений
        pattern = Pattern.compile("[a-zA-Z]");
        Matcher matcher = pattern.matcher(string);
        System.out.println(matcher.find());// true|false поиск соотв части строки
        System.out.println(matcher.matches());//true|false поиск соотв всей строки

    }
}
