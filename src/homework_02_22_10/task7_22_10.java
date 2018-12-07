package homework_02_22_10;

/**
 * Created by Pavel on 21.10.2018.
 */
public class task7_22_10 {
    public static void main(String[] args) {
        int hours = 0;
        int minutes = 0;
        int mirrorCount = 0;
        while(hours<24){
            if((hours/10==minutes%10)&&(hours%10==minutes/10))
                mirrorCount++;
            minutes++;
            if(minutes>=60){
                minutes=0;
                hours++;
            }
        }
        System.out.println(mirrorCount+" симетричных комбинаций.");
    }
}
