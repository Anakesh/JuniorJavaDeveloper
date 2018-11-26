package lesson_9_07_11.Pair;

import lesson_9_07_11.classes.Book;

public class Pair<K,V> {
    private K key;
    private V value;
    public Pair(K key, V value){
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
    public static void main(String[] args){
        Pair<Integer,String> pair = new Pair<>(0,"zero");
        Integer key = pair.getKey();
        String value = pair.getValue();

        Book book = new Book("book",1000);
        Pair<String, Book> pair1 = new Pair<>("book",book);
        Book book1 = pair1.getValue();
    }
}
