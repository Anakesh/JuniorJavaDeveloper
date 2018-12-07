package lesson_11_12_11_collections;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class CollectionPractice {
    public static void main(String[] args) throws IOException {


//        Ввести с клавиатуры 5 слов в список строк.
//        Удалить 3 - ий элемент списка, и вывести оставшиеся элементы в обратном порядке.

        Scanner scanner = new Scanner(System.in);
        List<String> input = new ArrayList<>();
        for(int i=0;i<5;i++)
            input.add(scanner.nextLine().trim());
        input.remove(2);
        for(int i = input.size()-1;i>=0;i--){
            System.out.print(input.get(i)+" ");
        }
        Iterator<String> iterator = input.iterator();

        File txtFile = new File("lesson11/file.txt");
        List<String>list = Files.readAllLines(txtFile.toPath());
        System.out.println(list);
    }
}
