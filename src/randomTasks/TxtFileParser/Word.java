package randomTasks.TxtFileParser;

import java.util.Objects;

public class Word implements Comparable<Word> {
    private String word;
    private int quantity;

    public Word(String word) {
        this.word = word;
        this.quantity = 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Word word1 = (Word) o;

        if (quantity != word1.quantity) return false;
        return word.equals(word1.word);
    }

    @Override
    public int hashCode() {
        int result = word.hashCode();
        result = 31 * result + quantity;
        return result;
    }

    @Override
    public String toString() {
        return "{word=\'" + word + "\' " +
                ", quantity=" + quantity +
                "}\n";
    }

    String getWord() {
        return word;
    }

    int getQuantity() {
        return quantity;
    }

    public void incrementQuantity() {
        this.quantity++;
    }

    @Override
    public int compareTo(Word o) {
//        if(this.equals(o)) return 0;
//        else if(o.quantity-this.quantity==0) return -1;
//        else
            return o.quantity-this.quantity;
    }
}
