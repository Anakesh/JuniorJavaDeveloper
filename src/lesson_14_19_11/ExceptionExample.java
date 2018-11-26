package lesson_14_19_11;

import java.util.Arrays;

public class ExceptionExample {
    public static void main(String[] args) {
        int[] arr = new int[5];
//        arr[10] = 23; //java.lang.ArrayIndexOutOfBoundsException
        System.out.println(Arrays.toString(arr));

        String str = null;
//        str.equals("null"); //java.lang.NullPointerException

        int a = 9;
        int b = 0;
//        System.out.println(a/b);//java.lang.ArithmeticException

//        Integer.parseInt("int");//java.lang.NumberFormatException

        //выбросить свое исключение
//        throw new NumberFormatException("NFE");

        //обработка исключений
//        try-catch
        Object date = "42";
//        Integer intDate = (Integer) date; //java.lang.ClassCastException

        try {
//            System.out.println(a/b);// Не ловим поэтому все ломается но finally выполняется
            Integer intDate = (Integer) date;
            System.out.println("It worked!!!");
        }
        catch (ClassCastException|ArrayIndexOutOfBoundsException ex){
            System.out.println("Exception: "+ex.getMessage());
            System.out.println(Arrays.toString(ex.getStackTrace()));
            System.out.println(ex.getCause()); // показывает если одно исключение может порождать другое
        }
//        catch (RuntimeException ex){
//            System.out.println("Exception: "+ex.getMessage());
//        }
        /*
        catch (ClassCastException ex){
            System.out.println("Exception: "+ex.getMessage());
            ex.printStackTrace();
        }catch (NullPointerException ex){
            System.out.println("Exception: "+ex.getMessage());
        }*/
        finally {//Используется для закрытия ресурсов(соединения и т.д.) при exception, выполняется в любом случае
            System.out.println("finally");
        }
        System.out.println("maybe worked");


        //checked exception
//        voidWithExcpetion();
        try {
            voidWithExcpetion();
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        //throw new MyCheckedException("MyCheckedException"); //нужно обработать

        try{
            throw new MyCheckedException("MyCheckedException");
        }catch (MyCheckedException ex){
            System.out.println(ex.getMessage());
        }

        try{
            throw new MyUncheckedException("MyUncheckedException");
        }catch (MyUncheckedException ex){
            System.out.println(ex.getMessage());
        }

        try{
            voidWithCheckedExcpetion();
        }catch (MyCheckedException ex){
            System.out.println(ex.getMessage());
            throw new MyUncheckedException("In catch",ex);
        }
        System.out.println("end text");

    }

    public static void voidWithExcpetion() throws Exception {
        throw new Exception("cheked exception");
    }
    public static void voidWithCheckedExcpetion() throws MyCheckedException {
        throw new MyCheckedException("cheked exception");
    }
}
