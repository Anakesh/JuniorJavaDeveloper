package lesson_06_29_10.battleunit;

public class Knight extends BattleUnit implements  ISpeakerUnit{

    public Knight(int health, int attackScore) { // 100 20
        super(health, attackScore); // 100 20
    }

    private void addHealth(){
        if (this.health > 1) {
            this.health +=5;
        }
    }

    @Override
    public void attack(BattleUnit enemy){
        enemy.health -= this.attackScore;
        addHealth();
        System.out.println("Knight attack");
    }


    @Override
    public void say(String text) {
        System.out.println("Knigth said: "+text);
    }

    @Override
    public void sing(String song) {
        System.out.println("Singing a song: "+song);
    }
}
