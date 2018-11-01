package lesson_7_31_10.memberInner;

import javax.swing.text.html.HTMLDocument;
import java.util.Iterator;

public class MyArray {
    private  Object[] array;
    private int x;

    public MyArray(){
        array = new Object[10];
        for(int i=0;i<array.length;i++){
            array[i] = String.valueOf(i);
        }
        x++;
        System.out.println(x);
    }

    public  Iterator evenIterator(){
        return  new EvenIterator();
    }
    private class EvenIterator implements Iterator{
        private  int nextIndex;

        @Override
        public boolean hasNext() {
            return nextIndex<array.length;
        }

        @Override
        public Object next() {
            Object next = array[nextIndex];
            nextIndex+=2;
            return next;
        }
    }
}
