package homework_7_19_11_calculator;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Calculator calculator = new Calculator();
        try{
            System.out.println("y = 10");
            System.out.println(calculator.parseInput("y = 10"));
            System.out.println("x=  20.6");
            System.out.println(calculator.parseInput("x = 20.6"));
            System.out.println("x- (y-2^ 2) *3");
            System.out.println(calculator.parseInput("x- (y-2^ 2) *3"));
            System.out.println("x-10.5");
            System.out.println(calculator.parseInput("x-10.5"));
            System.out.println("x-10.5-y*0.5");
            System.out.println(calculator.parseInput("x-10.5-y*0.5"));
            System.out.println("4.8+(3.1^2-3*(2+2.5))/(2^2+4-6.5)");
            System.out.println(calculator.parseInput("4.8+(3.1^2-3*(2+2.5))/(2^2+4-6.5)"));
        } catch (CalculationException ex){
            System.out.println(ex.getMessage());
        }
        while(true){
            String input = in.nextLine();
            if(input.equals("/stop"))
                break;
            try{
                System.out.println(calculator.parseInput(input));
            } catch (CalculationException ex){
                System.out.println(ex.getMessage());
            }
        }
    }
}

