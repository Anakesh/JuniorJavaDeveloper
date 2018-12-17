package homework_14_14_12_safeColl.blockingQueueTop;

public class Word implements Comparable<Word> {
    private String text;
    private int count;

    public Word(String text){
        this.text = text;
        this.count = 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Word word = (Word) o;

        return text.equals(word.text);
    }

    @Override
    public int hashCode() {
        return text.hashCode();
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
