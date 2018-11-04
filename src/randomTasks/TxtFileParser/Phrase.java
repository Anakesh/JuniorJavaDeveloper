package randomTasks.TxtFileParser;

import java.util.Deque;

class Phrase {
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
}