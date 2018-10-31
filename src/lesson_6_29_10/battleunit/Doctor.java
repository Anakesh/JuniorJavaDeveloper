package lesson_6_29_10.battleunit;

public class Doctor
        extends BattleUnit
        implements ISpeakerUnit {
    public Doctor(int health, int attackScore) { // 100 20
        super(health, attackScore); // 100 20
    }

    @Override
    public void say(String text) {
        System.out.println("Doctor said: "+ text);
    }

    @Override
    public void sing(String song) {
        System.out.println("Doctor singing a song: "+song);
    }

    @Override
    public void attack(BattleUnit unit) {
        unit.health+=attackScore;
    }
}
