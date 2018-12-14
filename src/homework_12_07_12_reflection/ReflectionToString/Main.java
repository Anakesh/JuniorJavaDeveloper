package homework_12_07_12_reflection.ReflectionToString;

import homework_11_05_12_patterns.TextProcessor.Message;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        Message message = new Message("user",null);
        System.out.println(ReflectionMethods.toString(message));
        System.out.println(ReflectionMethods.toString("hello"));
        TestClass testClass = new TestClass();
        System.out.println(ReflectionMethods.toString(testClass));
    }
}

