package homework_19_10;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class task1 {
    public static void main(String[] args) {
        BufferedReader buffReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Высота, длинна и ширина через запятую:");
        try {
            String[] params = buffReader.readLine().trim().split(",");
            int height = Integer.parseInt(params[0]);
            int length = Integer.parseInt(params[1]);
            int width = Integer.parseInt(params[2]);
            System.out.println("Высота = " + height + ". Длина = " + length+". Ширина = " + width + ".");
            int square = 2 * (height * length + height * width + length * width);
            System.out.println("Площадь равна " + square);
            System.out.println((height > width) ? "Высота больше ширины." : "Ширина больше высоты.");
        }catch(Exception e){
            System.out.println("Неверный формат");
        }
    }
}
