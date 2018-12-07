package lesson_18_05_12_patterns.Listener;

import java.util.ArrayList;
import java.util.List;

public class NewsAgency  {

    private List<IListener> listeners = new ArrayList<>();

    public void addListener(IListener listener){
        listeners.add(listener);
    }
    public void deleteListener(IListener listener){
        listeners.remove(listener);
    }
    public void newMessage(String mess){
        System.out.println("Message" + mess);
        notifyListeners(mess);
    }

    private void notifyListeners(String mess) {
        for (IListener listener:listeners){
            listener.publish(mess);
        }
    }

    public static void main(String[] args) {
        NewsAgency newsAgency = new NewsAgency();
        newsAgency.addListener(new IListener() {
            @Override
            public void publish(String str) {
                System.out.println("Listener 1 has received this message: "+str);
            }
        });
        newsAgency.addListener(new IListener() {
            @Override
            public void publish(String str) {
                System.out.println("Listener 2 has received this message: "+str);
            }
        });
        newsAgency.addListener(new IListener() {
            @Override
            public void publish(String str) {
                System.out.println("Listener 3 has received this message: "+str);
            }
        });
        
        newsAgency.newMessage("Hello listeners!!!");
    }
}
