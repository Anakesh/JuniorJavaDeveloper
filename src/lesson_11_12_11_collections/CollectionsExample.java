package lesson_11_12_11_collections;

import java.util.*;

public class CollectionsExample {
    public static void main(String[] args) {
        // Java Collection Framework
        // java.util.Collection интерфейс
//
//        boolean add(E e);
//        boolean remove(Object o);
//        int size();
//        boolean isEmpty();
//        boolean contains(Object o);
//        Object[] toArray[];
//        <T> T[] toArray(T[] a);
//        String[] arr = collectionName.toArray(new String[0]);
        //iterator()

         // java.util.List интерфейс
//        предоставляет методы для работы с коллекцией,
//        которая является списком
//        void add(int index, E obj);
//        boolean addAll(int ind, Collection<? extends E> col);
//        E get(int ind);
//        int indexOf(Object o);
//        int lastIndexOf(Object o);
//        E remove(int ind);
//        E set(int index, E obj);
//        List<E> subList(int start, int end);

        //Set интерфейс

        //ArrayList класс
        ArrayList<String> stringList = new ArrayList<String>();
        stringList.ensureCapacity(120);
        stringList.trimToSize();
        stringList.add("3");
        stringList.add("2");
        stringList.add("3");
        stringList.add(0,"1");
        stringList.remove("3");
        System.out.println(stringList);

        Integer[] integers = {23,67,12,90};
        ArrayList<Integer> intList = new ArrayList<>(Arrays.asList(integers));
        System.out.println(intList);

        for(Integer i: intList){
            System.out.println(i);
        }
        Iterator<Integer> iterator = intList.iterator();
        while(iterator.hasNext()){
            System.out.println("iterator"+ iterator.next());
            if(iterator.next()==12){
                iterator.remove();
            }
        }
        System.out.println(intList);

        //LinkedList класс
        LinkedList<String> stringLinkedList = new LinkedList<>();
        stringLinkedList.add("1");
        stringLinkedList.add("2");
        stringLinkedList.add("4");
        stringLinkedList.add(2,"3");
        System.out.println(stringLinkedList);

        //удаление элементов
//        stringLinkedList.poll();        //null
//        stringLinkedList.pollFirst();   //null
//        stringLinkedList.remove();      //NoSuchElementException
//        stringLinkedList.removeFirst(); //NoSuchElementException
        String[] arr = stringLinkedList.toArray(new String[0]);
        System.out.println(Arrays.toString(arr));


        //HashSet класс, быстрее всего
        //SortedSet интерфейс -> TreeSet класс, медленее
        //LinkedHashSet класс, еще медленнее


        HashSet<String> hashSet = new HashSet<>();
        hashSet.add("Set 2");
        hashSet.add("Set 1");
        hashSet.add("Set 3");
        hashSet.add("Set 3");
        System.out.println(hashSet);

        TreeSet<String> treeSet = new TreeSet<>();
        treeSet.add("Set 2");
        treeSet.add("Set 1");
        treeSet.add("Set 3");
        treeSet.add("Set 3");
        System.out.println(treeSet);

        LinkedHashSet<String> linkedHashSet = new LinkedHashSet<>();
        linkedHashSet.add("Set 2");
        linkedHashSet.add("Set 1");
        linkedHashSet.add("Set 3");
        linkedHashSet.add("Set 3");
        System.out.println(linkedHashSet);


    }

}
