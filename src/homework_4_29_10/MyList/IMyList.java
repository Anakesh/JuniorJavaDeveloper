package homework_4_29_10.MyList;

interface IMyList <T> {
    void add(T object);
    void set(T object, int index);
    void remove(int index);
    T get(int index);
    int size();
}
