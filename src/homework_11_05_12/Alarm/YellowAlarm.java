package homework_11_05_12.Alarm;

/**
 * Created by Pavel on 07.12.2018.
 */
public class YellowAlarm implements IAlarm {
    private boolean isActive = false;
    private int activationTemp = 300;

    private void setActive(boolean active) {
        isActive = active;
        System.out.print(isActive ? "Yellow alarm activated" : "Yellow alarm deactivated");
    }

    @Override
    public void shutdown() {
        if(isActive){
            isActive = false;
            System.out.println("Yellow alarm shutdown and deactivated");
        } else{
            System.out.println("Yellow alarm shutdown");
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
