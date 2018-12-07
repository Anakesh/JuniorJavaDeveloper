package homework_11_05_12.Alarm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pavel on 07.12.2018.
 */
public class Sensor {
    private List<IAlarm> alarms = new ArrayList<>();

    public Sensor addAlarm(IAlarm alarm){
        this.alarms.add(alarm);
        return this;
    }

    public Sensor deleteAlarm(IAlarm alarm){
        this.alarms.remove(alarm);
        alarm.shutdown();
        return this;
    }

    public void newTemperature(int temperature){
        this.checkAlarms(temperature);
    }

    private void checkAlarms(int temp){
        for(IAlarm alarm :alarms){
            alarm.tempChanged(temp);
        }
    }
}
