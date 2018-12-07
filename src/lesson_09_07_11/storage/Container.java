package lesson_09_07_11.storage;

class Container<T> {
    T element;

    Container next;

    public Container(T element) {
        this.element = element;
    }
}
