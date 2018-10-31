package lesson_6_29_10.battleunit;

public class Spearman extends BattleUnit {

    public Spearman(int health, int attackScore) {
        super(health, attackScore);
    }

    @Override
    public void attack(BattleUnit enemy) {
        enemy.health-=this.attackScore;
    }


    @Override
    public void say(String text) {
        System.out.println("Spearman said: "+ text);
    }

    @Override
    public void sing(String song) {
        System.out.println("Spearman cant sing :(");
    }
}
