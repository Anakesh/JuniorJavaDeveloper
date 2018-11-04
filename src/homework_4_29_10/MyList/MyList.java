package homework_4_29_10.MyList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    public int getIndex(T object){
        MyListElement<T> current = first;
        for(int i = 0; i<length;i++){
            if(current.getObject().equals(object))
                return i;
            current = current.getNextMyListElement();
        }
        return -1;
    }

    public T peekFirst(){
        return first.getObject();
    }

    public T peekLast(){
        return last.getObject();
    }

    public boolean contains(T object){
        MyListElement<T> current = first;
        while(current!=null){
            if(current.getObject().equals(object))
                return true;
            current = current.getNextMyListElement();
        }
        return false;
    }

    public void set(int index, T object) {
        if(index==0){
            MyListElement<T> next = first.getNextMyListElement();
            first = new MyListElement<>(object);
            first.setNextMyListElement(next);
        }
        else{
            MyListElement<T> previous = findElementByIndex(index - 1);
            if (previous != null && index < length && index > 0) {
                MyListElement<T> newElement = new MyListElement<>(object);
                newElement.setNextMyListElement(previous.getNextMyListElement().getNextMyListElement());
                previous.setNextMyListElement(newElement);
            }
        }
    }

    public void add(T object) {
        if (first == null) {
            first = new MyListElement<>(object);
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
                MyListElement<T> newMyListElement = new MyListElement<>(object);
                newMyListElement.setNextMyListElement(current.getNextMyListElement());
                current.setNextMyListElement(newMyListElement);
                length++;
            }
        }
    }

    public void remove(int index) {
        MyListElement<T> previous = findElementByIndex(index - 1);
        if (previous != null && index < length && index >= 0) {
            previous.setNextMyListElement(previous.getNextMyListElement().getNextMyListElement());
            length--;
        }
    }

    public void push(T object) {
        last.setNextMyListElement(new MyListElement<>(object));
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
        MyListElement<T> newMyListElement = new MyListElement<>(object);
        newMyListElement.setNextMyListElement(first);
        first = newMyListElement;
        length++;
    }

    public T unshift() {
        T out = first.getObject();
        first = first.getNextMyListElement();
        length--;
        return out;
    }

    public void reverseList() {
        MyListElement<T> newFirst = first.recursiveReverse();
        first.setNextMyListElement(null);
        last = first;
        first = newFirst;
    }

    public int size() {
        return length;
    }

    public List<T> toArrayList(){
        List<T> list = new ArrayList<>();
        MyListElement<T> current = first;
        for(int i=0;i<length;i++){
            list.add(current.getObject());
            current=current.getNextMyListElement();
        }
        return list;
    }
    public List<String> toStringList(){
        List<String> list = new ArrayList<>();
        MyListElement<T> current = first;
        for(int i=0;i<length;i++){
            list.add(current.getObject().toString());
            current = current.getNextMyListElement();
        }
        return list;
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
