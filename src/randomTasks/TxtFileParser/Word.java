package randomTasks.TxtFileParser;

import java.util.Objects;

class Word {
    private String word;
    private int quantity;

    Word(String word) {
        this.word = word;
        this.quantity = 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word1 = (Word) o;
        return quantity == word1.quantity &&
                Objects.equals(word, word1.word);
    }

    @Override
    public int hashCode() {
        return Objects.hash(word, quantity);
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

    void incrementQuantity() {
        this.quantity++;
    }
}
