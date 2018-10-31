package homework_4_29_10.MyList;

public class MyList<T> implements IMyList<T>,IMyStack<T>,IMyQueue<T> {
    private MyListElement<T> first;
    private MyListElement<T> last;
    private int length = 0;

    public T get(int index) {
        MyListElement<T> target = findElementByIndex(index);
        if (target != null)
            return target.getObject();
        else
            return null;
    }

    public void set(T object, int index) {
        MyListElement<T> previous = findElementByIndex(index - 1);
        if (previous != null && index < length && index >= 0) {
            MyListElement<T> newElement = new MyListElement<>(object, index);
            newElement.setNextMyListElement(previous.getNextMyListElement().getNextMyListElement());
            previous.setNextMyListElement(newElement);
        }
    }

    public void add(T object) {
        if (first == null) {
            first = new MyListElement<T>(object, 0);
            last = first;
        } else {
            last.addNextMyListElement(object);
            last = last.getNextMyListElement();
        }
        length++;
    }

    public void add(T... objects) {
        for (T object : objects) {
            this.add(object);
        }
    }

    public void putAfter(int index, T object) {
        MyListElement<T> current = findElementByIndex(index);
        if (current != null) {
            if (current.getNextMyListElement() == null)
                this.add(object);
            else {
                MyListElement<T> newMyListElement = new MyListElement<>(object, index + 1);
                newMyListElement.setNextMyListElement(current.getNextMyListElement());
                current.setNextMyListElement(newMyListElement);
                newMyListElement.getNextMyListElement().changeIndex(1);
                length++;
            }
        }
    }

    public void remove(int index) {
        MyListElement<T> previous = findElementByIndex(index - 1);
        if (previous != null && index < length && index >= 0) {
            previous.setNextMyListElement(previous.getNextMyListElement().getNextMyListElement());
            previous.getNextMyListElement().changeIndex(-1);
            length--;
        }
    }

    public void push(T object) {
        last.setNextMyListElement(new MyListElement<>(object, length));
        last = last.getNextMyListElement();
        length++;
    }

    public T pop() {
        MyListElement<T> previous = findElementByIndex(length - 2);
        T out = last.getObject();
        previous.setNextMyListElement(null);
        last = previous;
        length--;
        return out;
    }

    public void shift(T object) {
        MyListElement<T> newMyListElement = new MyListElement<>(object, 0);
        newMyListElement.setNextMyListElement(first);
        first = newMyListElement;
        first.getNextMyListElement().changeIndex(1);
        length++;
    }

    public T unshift() {
        T out = first.getObject();
        first = first.getNextMyListElement();
        first.changeIndex(-1);
        length--;
        return out;
    }

    public void reverseList() {
        MyListElement<T> newFirst = first.recursiveReverse(length);
        first.setNextMyListElement(null);
        last = first;
        first = newFirst;
    }

    public int size() {
        return length;
    }

    private MyListElement<T> findElementByIndex(int index) {
        if (first == null || index > length - 1 || index < 0)
            return null;
        MyListElement<T> current = first;
        for (int i = 0; i < index; i++) {
            current = current.getNextMyListElement();
        }
        return current;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        MyListElement current = first;
        while (current != null) {
            str.append(current.toString()).append("\n");
            current = current.getNextMyListElement();
        }
        str.append("Length: ").append(length).append("\n");
        return str.toString();
    }
}
