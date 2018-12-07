package lesson_12_14_11_maps;

import java.util.*;

public class MapExample {
    public static void main(String[] args) {
        User user = new User(2,"John");
        System.out.println(user.hashCode());

//        Map - интерфейс
//        1. хранение данных в паре ключ-значение
//        2. ключи должны быть уникальными
//        3. хранение зависит от реализации
//        4. могут зранить любые типы данных ( включая null, но не всегда)

//        boolean containsKey(Object key);
//        boolean containsValue(Object val);
//        void putAll(Map<?extends K, ?extends  V> m);
        //основные методы
//        get(T key);
//        put(T key, K val);
            //реализации Map (классы)
        //HashMap
        //TreeMap
        //EnumMap
        //LinkedHashMap
        //WeakHashMap
        //IdentityHashMap
        Map<Integer, String> hashMap = new HashMap<>();
        hashMap.put(1,"Elem 1");
        hashMap.put(2,"Elem 2");
        hashMap.put(2,"Elem 3");
        hashMap.put(null,"Elem null");// null всегда на первом месте  в  HashMap
        System.out.println(hashMap);
        System.out.println(hashMap.get(1));

        // перебрать элементы Map
        for(Map.Entry entry: hashMap.entrySet()){
            System.out.println("Key: " +entry.getKey()+
                    " Value: "+ entry.getValue());
        }

        Map<Integer, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put(1,"Elem 1");
        linkedHashMap.put(2,"Elem 2");
        linkedHashMap.put(null,"Elem null");
        linkedHashMap.put(2,"Elem 3");
        System.out.println(linkedHashMap);
        System.out.println(linkedHashMap.get(1));

        Map<Integer, String> treeMap = new TreeMap<>();
        treeMap.put(2,"Elem 2");
//        treeMap.put(null,"Elem null"); //Ошибка
        treeMap.put(1,"Elem 1");
        treeMap.put(2,"Elem 3");
        System.out.println(treeMap);
        System.out.println(treeMap.get(1));

        //бинарное дерево
//        1. корня - верхний узел
//        2. листья - узлы без потомков
//        3. значение левого потомка должно быть меньше родителя
//        4. значение правого потомка должно быть больше родителя

        //красно черное бинарное дерево
//        1. корень и листья всегда черные
//        2. каждый красный узел имеет 2 черных потомков
//        3. все пути от узла(корня) до листьев должны иметь
//            одинаковое количество черных узлов

        User user1 = new User(3,"First");
        User user2 = new User(2,"Second");
        User user3 = new User(1,"More than second but less than fourth");

        Map<Integer, User> userTreeMap = new TreeMap<>();
        userTreeMap.put(1,user1);
        userTreeMap.put(2,user2);
        userTreeMap.put(3,user3);
        System.out.println(userTreeMap);

        TreeSet<User> treeSet = new TreeSet<>();
        treeSet.add(user1);
        treeSet.add(user2);
        treeSet.add(user3);
        System.out.println(treeSet);

        Map<Integer,String> hashmap2 = new HashMap<>();
        hashmap2.put(1,"Elem 1");
        hashmap2.put(3,"Elem 3");
        hashmap2.put(12,"Elem 12");
        hashmap2.put(23,"Elem 23");
        System.out.println(hashmap2);

        Map<Integer,String> treeMap2 = new TreeMap<>(hashmap2);

        Map<String,Integer> hashMap3 = new HashMap<>();
        hashMap3.put("first",100);
        hashMap3.put("second",300);
        hashMap3.put("three",400);
        hashMap3.put("zero", 50);
        System.out.println(hashMap3);
        /*
        for(Map.Entry<String,Integer> entry:hashMap3.entrySet()){
            if(entry.getValue()<200){
               hashMap3.remove(entry.getKey());
            }
        }*/

    }
}
