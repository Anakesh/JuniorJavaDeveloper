package homework_1_19_10;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class task2 {
    public static void main(String[] args) {
        BufferedReader buffReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Количество соток:");
        try {
            int totalSquare = Integer.parseInt(buffReader.readLine().trim()) * 100;

            System.out.println("Длина сторон грядки в метрах через запятую:");
            String[] strBedParams = buffReader.readLine().trim().split(",");
            int firstBedParam = Integer.parseInt(strBedParams[0]);
            int secondBedParam = Integer.parseInt(strBedParams[1]);
            int bedSquare = firstBedParam*secondBedParam;
            int remainingMeters = totalSquare%bedSquare;
            System.out.println("Осталось незанято "+remainingMeters+" метров");
        }
        catch(Exception e) {
            System.out.println("Неверный формат");
        }
    }
}
