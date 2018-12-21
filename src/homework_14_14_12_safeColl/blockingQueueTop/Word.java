package homework_14_14_12_safeColl.blockingQueueTop;

public class Word implements Comparable<Word> {
    private String text;
    private int count;

    public Word(String text){
        this.text = text;
        this.count = 1;
    }

    @Override
    public int compareTo(Word o) {
        return o.count - this.count ;
    }

    public int getCount() {
        return count;
    }

    public void incrementCount() {
        this.count++;
    }

    @Override
    public String toString() {
        return "Word{" +
                "text='" + text + '\'' +
                ", count=" + count +
                '}';
    }
}
