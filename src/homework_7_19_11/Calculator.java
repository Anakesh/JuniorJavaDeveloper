package homework_7_19_11;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {
    private Map<String, Double> variables = new HashMap<>();

    public String parseInput(String input) throws CalculationException {
        Pattern invalidFormat = Pattern.compile(
                //Всё кроме чисел, букв и указанных символов, или
                "([^\\w+\\-*/().^=])|" +
                        //Любое повторение символа подряд кроме скобок и минуса, или
                        "([\\W&&[^()\\-]]{2,})|" +
                        //Повторение минуса более двух раз, или
                        "(-{3,})|" +
                        //Наличие в строке более двух равно, или
                        "(.*=.*=)|" +
                        //Наличие любого из указанных символов перед закрывающей скобкой, или
                        "([+\\-*/^]\\))|" +
                        //Наличие буквы или цифры перед открывающей скобкой
                        "(\\w\\()");
        Pattern newVariablePattern = Pattern.compile(
                //Любой количество букв с начала строки и следующее за ними равно
                "^[a-zA-Z]+=");
        Pattern operationPattern = Pattern.compile(
                //Наличие любого из указанных символов
                "[+\\-*/^]");

        //Убираем все пробелы
        String filteredInput = input.replaceAll("\\s+", "");

        //Проверка на несоответствие формату и разное количетво "(" и ")"
        if (invalidFormat.matcher(filteredInput).find() ||
                (getNumberOfMatches(filteredInput, "(") != getNumberOfMatches(filteredInput, ")")))
            throw new CalculationException("Wrong format");

        //Проверка является ли ввод созданием новой переменной
        Matcher newVariableMatcher = newVariablePattern.matcher(filteredInput);

        if (newVariableMatcher.find()) {
            //Если ввод является объявлением новой переменной
            int valueStartIndex = newVariableMatcher.end();

            //Получаем название новой переменной
            String newVariable = filteredInput.substring(0, valueStartIndex - 1);

            //Удаляем левую часть ввода (название переменной и =)
            filteredInput = filteredInput.substring(valueStartIndex, filteredInput.length());

            //Заменяем известные переменные на их значения
            filteredInput = knownVariableInsert(filteredInput);
            double variableValue;

            //Проверяем является ли правая часть выражением (имеются ли в нем операции)
            if (operationPattern.matcher(filteredInput).find()) {
                //Если является то решаем его
                variableValue = solveExpression(filteredInput);

                //И добавляем в коллекцию переменных
                variables.put(newVariable, variableValue);
            } else {
                //Если не является то парсим
                try {
                    variableValue = Double.parseDouble(filteredInput);
                } catch (NumberFormatException ex) {
                    throw new CalculationException("Wrong format", ex);
                }
                //И добавляем в коллекцию переменных
                variables.put(newVariable, variableValue);
            }
            return "New variable: " + newVariable + " equals: " + variables.get(newVariable);
        } else {
            //Если ввод не является обявлением новой переменной то ищем и заменяем известные переменные их значениями
            filteredInput = knownVariableInsert(filteredInput);

            //И возвращаем решение
            return "Answer: " + solveExpression(filteredInput);
        }
    }

    private double solveExpression(String str) throws CalculationException {
        Pattern bracketsPattern = Pattern.compile(
                // Цифры и указанные символы находящиеся внутри скобок
                "\\([0-9.\\-+*/^]+\\)");
        Matcher bracketsMatcher = bracketsPattern.matcher(str);

        //Ищем выражение заключенное внутри скобок
        while (bracketsMatcher.find()) {
            String match = bracketsMatcher.group();

            //Удаляем внешние скобки в найденном выражении
            String inExpression = match.replaceAll("^\\(", "").replaceAll("\\)$", "");

            //И решаем его
            double answer = solveExpression(inExpression);

            //Заменяем выражение со скобками на его решение
            str = str.replace(match, String.valueOf(answer));

            //Обновление matcher'а уже с новой строкой
            bracketsMatcher = bracketsPattern.matcher(str);
        }
        //Поочередно выполняем все операции в выражении
        str = doAllOperations(str, new Power());     //Степень
        str = doAllOperations(str, new Multiply());  //Умножение
        str = doAllOperations(str, new Division());  //Деление
        str = doAllOperations(str, new Plus());      //Сложение
        str = doAllOperations(str, new Minus());     //Вычитание

        //Парсим и возвращаем результат
        double result;
        try {
            result = Double.parseDouble(str);
        } catch (NumberFormatException ex) {
            throw new CalculationException("Unexpected error", ex);
        }
        return result;
    }

    //Поиск количества повторений указанной строки regEx в строке str
    private int getNumberOfMatches(String str, String regEx) {
        Pattern pattern = Pattern.compile(Pattern.quote(regEx));
        Matcher matcher = pattern.matcher(str);
        int count = 0;
        while (matcher.find())
            count++;
        return count;
    }

    //Замена известных переменных на их значения
    private String knownVariableInsert(String str) throws CalculationException {
        Set<String> vars = new TreeSet<>(
                Arrays.asList(
                        str.replaceAll("[^a-zA-Z]", " ")
                                .replaceAll("\\s+", " ")
                                .split(" ")));
        for (String var : vars) {
            Double value = variables.get(var);
            if (value == null)
                throw new CalculationException("Unknown variable");
            str = str.replace(var, value.toString());
        }
        return str;
    }

    //Выполнение всех указанных операций в переданной строке
    private String doAllOperations(String str, IOperation operation) {
        Pattern pattern = Pattern.compile(
                //Целое или дробное число, с или без минусом(а) перед ним
                //(минус захватывается только если перед ним имеется еще один из указанных символов
                // или он является первым в строке)
                "((((?<=([+\\-*/^]))-)|((?<=^)-))?\\d+\\.*\\d*)" +
                        //За которым следует символ операции
                        Pattern.quote(operation.getString()) +
                        //После которого идет целое или дробное число, с или без минусом(а)
                        "(-?\\d+\\.*\\d*)");
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            String match = matcher.group();
            String[] nums = match.split(
                    //Выполнится только тогда, когда перед символом идет число
                    "(?<=[\\d.])" + Pattern.quote(operation.getString()));

            //Выполнение операции
            double result = operation.execute(Double.parseDouble(nums[0]), Double.parseDouble(nums[1]));

            //Заменяем операцию на ее решение
            str = str.replaceAll(Pattern.quote(match), String.valueOf(result));

            //Обновление matcher'а уже с новой строкой
            matcher = pattern.matcher(str);
        }
        return str;
    }
}
