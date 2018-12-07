package homework_11_05_12.Alarm;

/**
 * Created by Pavel on 07.12.2018.
 */
public class RedAlarm implements IAlarm {

    private boolean isActive = false;
    private int activationTemp = 600;

    private void setActive(boolean active) {
        isActive = active;
        System.out.print(isActive ? "Red alarm activated" : "Red alarm deactivated");
    }

    @Override
    public void shutdown() {
        if(isActive){
            isActive = false;
            System.out.println("Red alarm shutdown and deactivated");
        } else{
            System.out.println("Red alarm shutdown");
        }
    }

    @Override
    public void tempChanged(int temp) {
        if (temp >= activationTemp) {
            if (!this.isActive)
                setActive(true);
        } else if (this.isActive)
            setActive(false);
    }
}
