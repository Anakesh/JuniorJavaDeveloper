package homework_02_22_10;

/**
 * Created by Pavel on 21.10.2018.
 */
public class task6_22_10 {
    public static void main(String[] args) {
        int luckyCount = 0;
        for(int i =1;i<1_000_000;i++)
        {
            if(GetSum(i/1000)==GetSum(i%1000))
                luckyCount++;
        }
        System.out.println("В рулоне находится "+luckyCount+" счастливых билетов.");
    }
    private static int GetSum(int number){
        return (number/100)+(number/10)+(number%10);
    }
}
