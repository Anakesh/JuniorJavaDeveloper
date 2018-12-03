package homework_8_26_11;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Message implements Serializable {
    private String messText;
    private String sender;
    public Message(String sender, String messText){
        this.sender = sender;
        this.messText = messText;
    }

    Set<String> str = new HashSet<>();
    @Override
    public String toString() {
        return "Message:" + messText + " from: "+ sender;
    }

    public String getMessText() {
        return messText;
    }

    public void setMessText(String messText) {
        this.messText = messText;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}
