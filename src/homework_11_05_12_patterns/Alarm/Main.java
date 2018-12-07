package homework_11_05_12_patterns.Alarm;

public class Main {
    public static void main(String[] args) {

        System.out.println(Double.parseDouble("-5.5"));

        IAlarm red = new RedAlarm();
        IAlarm green = new GreenAlarm();
        IAlarm yellow = new YellowAlarm();

        Sensor sensor = new Sensor();
        sensor.addAlarm(red).addAlarm(green).addAlarm(yellow);

        int j = 50;
        for(int i= 0;j!=-50||i>=0;i+=j){
            System.out.print("Current temperature: "+i+'\t');
            sensor.newTemperature(i);
            System.out.println();
            if(i>=700) {
                sensor.deleteAlarm(red);
                j = -50;
            }
        }
        sensor.deleteAlarm(green);
        sensor.deleteAlarm(yellow);
    }
}

