package homework_11_05_12.Alarm;

/**
 * Created by Pavel on 07.12.2018.
 */
public class GreenAlarm implements IAlarm {
    private boolean isActive = false;
    private int activationTemp = 100;

    private void setActive(boolean active) {
        isActive = active;
        System.out.print(isActive ? "Green alarm activated" : "Green alarm deactivated");
    }

    @Override
    public void shutdown() {
        if(isActive){
            isActive = false;
            System.out.println("Green alarm shutdown and deactivated");
        } else{
            System.out.println("Green alarm shutdown");
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
