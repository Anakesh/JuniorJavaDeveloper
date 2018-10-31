package lesson_6_29_10.battleunit;

public class Fight {

    BattleUnit unit1;
    BattleUnit unit2;
    public Fight(BattleUnit unit1, BattleUnit unit2){
        this.unit1 = unit1;
        this.unit2 = unit2;
    }
    public void fight(){
        while(unit1.isAlive()&&unit2.isAlive()){
            unit1.attack(unit2);
            if(unit2.isAlive())
                unit2.attack(unit1);
        }
    }
    public String fightResult(){
        return "Health of a unit 1 = "+unit1.getHealth()+". Health if a unit 2 = "+unit2.getHealth();
    }
}
