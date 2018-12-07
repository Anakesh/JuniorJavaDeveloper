package lesson_06_29_10.battleunit;

public class BattleUnitFactory {
    public static BattleUnit create(String userChoice){
        switch(userChoice){
            case "knight":
                return new Knight(100,20);
            case "spearman":
                return new Spearman(60,70);
            case "doctor":
                return new Doctor(50,10);
            default:
                return null;
        }
    }
}
