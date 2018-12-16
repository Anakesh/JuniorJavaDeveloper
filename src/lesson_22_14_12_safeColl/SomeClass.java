package lesson_22_14_12_safeColl;

import java.util.Vector;

public class SomeClass {
    //чтение из памяти всегда атомарно
    //однако thread создает кэш переменных, которые нужно синхронизировать
    //(синхронизация происходит автом но в рандомное время)
    private static volatile boolean run = true; //volatile - чтение из кэша не происходит, считывается всегда из памяти

    public static void main(String[] args) throws InterruptedException {
        for(int i = 0;i<1000;i++){
            new SomeThread().start();
        }

        Thread.sleep(3000);
        run = false;
    }

    private static class SomeThread extends Thread{
        @Override
        public void run() {
            while(run){
                try {
                    sleep(1);
                } catch (InterruptedException e) {
                    run = false;
                    e.printStackTrace();
                }
            }
        }
    }
    /*
    Изначальные потокобезопасные коллекции (просто используют синхр):
    Vector
    HashTable
    Stack
    StringBuffer


    однопоточные коллекции с декораторами:
    List<String> list = Collections.synchronizedList(new ArrayList<>());
    Set<String> set = Collections.synchronizedSet(new HashSet<>());
    Map<String, Integer> map = Collections.synchronizedMap(new HashMap<>());


    java.util.concurrent. - java 1.5, пакет с многопоточными коллекциями
    многопоточные коллекции:
    интерфейс ConcurentMap
    чтение и запись всегда атомарны
    операции чтения не требуют блокировок
    некоторые операции записи не требуют блокировок

    putIfAbsent(key,val) - добавляет если не совпадает пара значений
    remove(key, val) - удалениепара значений
    replace(key,oldValue, newValue) - заменяет значение если существует пара значений

    Реализация:
    ConcurrentHashMap - мапа которая разбита на сегменты, с блокировкой на уровне сегментов но тольк при записи
    ConcurrentSkipListMap (Skip list алгоритм)
    ConcurrentSkipListSet (Skip list алгоритм)
    CopyOnWriteArrayList - используют при большом количестве чтения и малом кол-ве записей
    CopyOnWriteArraySet - используют при большом количестве чтения и малом кол-ве записей

    Блокирующие очереди:
    интерфейс BlockingQueue - однонаправленная очередь
    интерфейс BlockingDeque - двунаправленная очередь
    блокирующие очереди не позволит получить элемент при пустой очереди и добавить при полной
    Методы queue:
    Вставка
        add(e) - exception если полон
        offer(e,time) - возвращает boolean соотв. об успехе(зависит от реализации) TimeUnit
        put(e) - блокирует поток (усыплен) если полон, а затем разбужен когда появится место
    Удаление:
        remove - exception если пуст
        poll() - зависит от реализации
        take() - блокирует поток пока не будет что удалять

    Реализации:
    ArrayBlockingQueue - очередь фиксированного размера
    new ArrayBlockingQueue(int capacity)
    new ArrayBlockingQueue(int capacity, boolean fair) - fair (по умолчанию true), если true будет ожидающий поток (он обязательно считает/запишет первым), иначе кто успел тот и влез
    new ArrayBlockingQueue(int capacity, boolean fair, Collection collection) - на основе коллекции

    LinkedBlockingQueue() - изменяемого размера, по умолч Integer.MAX_VALUE
    new LinkedBlockingQueue() - с размером по умолчанию
    new LinkedBlockingQueue(int capacity) - заданным размером
    new LinkedBlockingQueue(Collection collection) - на основе коллекции

    SynchronousQueue - блокирует удаление до момента удаления (всегда один элемент), используется для передачи данными между потоками
    */
}
