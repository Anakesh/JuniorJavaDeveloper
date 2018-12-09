package homework_11_05_12_patterns.TextProcessor;

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

        return "From '" + sender + "':"+ messText;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Message message = (Message) obj;

        return this.sender.equals(message.sender)&&this.messText.equals(message.messText);
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
