package lesson_6_29_10;

import lesson_6_29_10.Calculator.Operation;
import lesson_6_29_10.Calculator.Plus;
import lesson_6_29_10.battleunit.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

/*        Knight knight = new Knight(100, 20);
        Spearman spearman = new Spearman(60, 70);

        knight.attack(spearman);

        Doctor doctor = new Doctor(50,10);
        doctor.attack(knight);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите название персонажа");
        String userChoice = scanner.nextLine().toLowerCase();

        BattleUnit unit = BattleUnitFactory.create(userChoice);



        Fight fight = new Fight(unit,spearman);
        fight.fight();
        System.out.println(fight.fightResult());*/
        float a =9f;
        float b = 1f;
        System.out.println("a = "+a+" b = "+b);
        Operation plus = new Plus(a,b);
        System.out.println(plus.execute());
    }

}
