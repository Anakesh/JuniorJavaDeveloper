package homework_11_05_12.CryptedStream;

import java.io.*;
import java.util.Scanner;

/**
 * Created by Pavel on 07.12.2018.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter file path:");
        String filePath = input.nextLine();
        File file = new File(filePath);
        System.out.println("Enter key:");
        byte[] key = input.nextLine().getBytes("UTF-8");
        System.out.println("Enter message");
        byte[] message = input.nextLine().getBytes("UTF-8");
        try(
                CryptoFilterOutputStream out = new CryptoFilterOutputStream(new FileOutputStream(file),key);
                CryptoFilterInputStream in = new CryptoFilterInputStream(new FileInputStream(file),key);
                ){

            out.write(message);
            byte[] answer = new byte[in.available()];
            /*int b;
            for (int i =0;(b = in.read())>-1;i++){
                answer[i] = (byte)b;
            }*/
            in.read(answer);
            String strAnswer = new String(answer,"UTF-8");
            System.out.println(strAnswer);
        }
    }
}
