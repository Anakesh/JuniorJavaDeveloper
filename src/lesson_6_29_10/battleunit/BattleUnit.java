package lesson_6_29_10.battleunit;

abstract public class BattleUnit implements ISpeakerUnit {
//    private int health;
//    private int attackScore;
    protected int health;
    protected int attackScore;

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAttackScore() {
        return attackScore;
    }

    public void setAttackScore(int attackScore) {
        this.attackScore = attackScore;
    }

    public BattleUnit(int health, int attackScore) { // 100 20
        this.health = health;
        this.attackScore = attackScore;
    }

    public boolean isAlive(){
        return health > 0;
    }

    abstract public void attack(BattleUnit enemy);



}
