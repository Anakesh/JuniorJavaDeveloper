package randomTasks.TxtFileParser;

import java.util.Arrays;
import java.util.Deque;

class Phrase implements Comparable<Phrase> {
    private Word[] phrase;
    private int quantity;

    Phrase(Deque<Word> phraseQueue) {
        this.phrase = new Word[phraseQueue.size()];
        int i = 0;
        for (Word word : phraseQueue) {
            this.phrase[i] = word;
            i++;
        }
        quantity = 0;
    }

    int getQuantity() {
        return quantity;
    }

    public Word[] getPhrase() {
        return phrase;
    }

    void incrementQuantity() {
        this.quantity++;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < phrase.length; i++) {
            str.append(phrase[i].getWord()).append(" ");
        }
        return "{phrase = \'" + str.toString().trim() + "\' " +
                ", quantity = " + quantity + "}\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Phrase phrase1 = (Phrase) o;

        if (quantity != phrase1.quantity) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(phrase, phrase1.phrase);
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(phrase);
        result = 31 * result + quantity;
        return result;
    }

    @Override
    public int compareTo(Phrase o) {
        if(this.equals(o)) return 0;
        return o.quantity-this.quantity;
    }
}