package homework_04_29_10.MyList;

interface IMyList <T> {
    void add(T object);
    void set(int index, T object);
    void remove(int index);
    T get(int index);
    int size();
}
