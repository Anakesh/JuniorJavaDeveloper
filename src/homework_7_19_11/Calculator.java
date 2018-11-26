package homework_7_19_11;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {
    private Map<String,Double> variables = new HashMap<>();
    private Pattern invalidFormat = Pattern.compile("([^\\w+\\-*/().^=])|([\\W&&[^()]]{2,})|(.*=.*=)|([+\\-*/^]\\))|([+\\-*/^]-)");
    private Pattern newVariablePattern = Pattern.compile("^[a-zA-Z]+=");
    private Pattern operationPattern = Pattern.compile("[+\\-*/^]");
    public String parseInput(String input) throws CalculationException {

        //Убираем все пробелы
        String filteredInput = input.replaceAll("\\s+","");

        //Проверка на несоответствие формату
        if(invalidFormat.matcher(filteredInput).find()&&
                (getNumberOfMatches(filteredInput,"(")!=getNumberOfMatches(filteredInput,")")))
            throw new CalculationException("Wrong format");
        Matcher newVariableMatcher = newVariablePattern.matcher(filteredInput);
        if(newVariableMatcher.find()){
            int valueStartIndex = newVariableMatcher.end();
            String newVariable = filteredInput.substring(0,valueStartIndex-1);
            filteredInput =  filteredInput.substring(valueStartIndex,filteredInput.length());
            filteredInput = knownVariableInsert(filteredInput);
            if(operationPattern.matcher(filteredInput).find()){
                double variableValue = solveEquation(filteredInput);
                variables.put(newVariable,variableValue);
            } else{
                double variableValue;
                try {
                    variableValue = Double.parseDouble(filteredInput);
                }catch (NumberFormatException ex){
                    throw new CalculationException("Wrong format",ex);
                }
                variables.put(newVariable,variableValue);
            }
            return newVariable+"="+variables.get(newVariable);
        } else {
            filteredInput = knownVariableInsert(filteredInput);
            return "Answer: "+solveEquation(filteredInput);
        }
    }
    private double solveEquation(String str) throws CalculationException {
//        Pattern bracketsPattern = Pattern.compile("\\((((?<=\\()-)?\\d+\\.*\\d*)+([+\\-*/^](\\d+\\.*\\d*))*\\)");
        Pattern bracketsPattern = Pattern.compile("\\([0-9.\\-+*/^]+\\)");
        Matcher bracketsMatcher = bracketsPattern.matcher(str);
        while(bracketsMatcher.find()){
            String inEquation = bracketsMatcher.group().replaceAll("^\\(","").replaceAll("\\)$","");
            double answer = solveEquation(inEquation);
            str = str.replaceFirst(bracketsPattern.toString(), String.valueOf(answer));
        }
        str = doAllOperations(str,"^");
        str = doAllOperations(str,"*");
        str = doAllOperations(str,"/");
        str = doAllOperations(str,"+");
        str = doAllOperations(str,"-");
        double result;
        try {
            result = Double.parseDouble(str);
        }catch (NumberFormatException ex){
            throw new CalculationException("Unexpected error",ex);
        }
        return result;
    }
    private int getNumberOfMatches(final String str, String regEx){
        Pattern pattern = Pattern.compile(Pattern.quote(regEx));
        Matcher matcher = pattern.matcher(str);
        int count =0;
        while(matcher.find())
            count++;
        return count;
    }
    private String knownVariableInsert(String str) throws CalculationException {
        Set<String> vars = new TreeSet<>(
                Arrays.asList(
                        str.replaceAll("[^a-zA-Z]"," ")
                                .replaceAll("\\s+"," ")
                                .split(" ")));
        for(String var : vars){
            Double value = variables.get(var);
            if(value==null)
                throw new CalculationException("Unknown variable");
            str = str.replace(var,value.toString());
        }
        return str;
    }
    private String doAllOperations(String str, String operationRegex){
//        Pattern pattern = Pattern.compile("((((?<=^)-)?\\d+\\.*\\d*)"+Pattern.quote(operationRegex)+"\\d*");
        Pattern pattern = Pattern.compile("\\d+\\.*\\d*"+Pattern.quote(operationRegex)+"\\d+\\.*\\d*");
        Matcher matcher = pattern.matcher(str);
        while(matcher.find()){
            String match = matcher.group();
            String[] nums = match.split(Pattern.quote(operationRegex));
            double result = doOperation(Double.parseDouble(nums[0]),operationRegex,Double.parseDouble(nums[1]));

            str = str.replaceAll(Pattern.quote(match),String.valueOf(result));
            matcher = pattern.matcher(str);
        }
        return str;
    }
    private double doOperation(double first, String operation, double second){
        double result = 0;
        switch(operation) {
            case "^":
                result = (int) Math.pow(first, second);
                break;
            case "*":
                result = first*second;
                break;
            case "/":
                result = first/second;
                break;
            case "+":
                result = first+second;
                break;
            case "-":
                result = first-second;
                break;
        }
        return result;
    }
}