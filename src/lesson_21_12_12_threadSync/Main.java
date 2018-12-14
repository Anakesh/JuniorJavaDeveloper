package lesson_21_12_12_threadSync;

import javax.swing.plaf.synth.SynthRootPaneUI;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.RandomAccessFile;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Sync sync = new Sync();
        List<Sync.Adder> adderList = new ArrayList<>(100);

        for(int i=0; i<100;i++){
            adderList.add(sync.new Adder());
        }

        for(Sync.Adder adder:adderList){
            adder.start();
        }

        for (Sync.Adder adder: adderList){
            adder.join();
        }
        System.out.println("Result: "+sync.counter);
    }
}

// общий участок памяти
// который будем изменять из разных потоков
class Sync{
    int counter;

    public class Adder extends Thread{
        @Override
        public void run() {
            for(int i= 0;i<100_000;i++){
                //отсутствие синхронизации
//                counter++;

                //синхронизация на объекте
//                synchronized (Sync.this){// lock object
//                    counter++;
//                }

                //синхронизация на методе
                increment();
            }
        }
    }

    //при синхр метода, синхр происходит на текущем объекте
    private synchronized void increment() {
        counter++;
    }
}


class Storage {
    int bookCount = 0;

    public synchronized void getBook() throws InterruptedException {
        System.out.println("getBook start");
        while (bookCount < 1) {
            wait();
        }
        bookCount--;
        System.out.println("One book was taken from storage");
        System.out.println("Storage has " + bookCount + " books");
        notify();//разбудит один случайный ожидающий поток
        System.out.println("getBook end");
    }

    public synchronized void putBook() throws InterruptedException {
        System.out.println("putBook start");
        while (bookCount >= 5) {
            wait();
        }
        bookCount++;
        System.out.println("One books was placed in storage");
        System.out.println("Storage has " + bookCount + " books");
        notify();
        System.out.println("putBook end");
    }
}

class Put implements Runnable{
    Storage storage;
    public Put(Storage storage){
        this.storage = storage;
    }

    @Override
    public void run() {
        for(int i=0;i<10;i++){
            try {
                storage.putBook();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Get implements Runnable{
    Storage storage;
    public Get(Storage storage){
        this.storage = storage;
    }

    @Override
    public void run() {
        for(int i = 0;i<10;i++){
            try{
                storage.getBook();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Library{
    public static void main(String[] args) {
//        Storage storage = new Storage();
//        Put put = new Put(storage);
//        Get get = new Get(storage);
//        new Thread(put).start();
//        new Thread(get).start();
        Object obj1 = new Object();
        Object obj2 = new Object();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread 1 start");
                synchronized (obj1){
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Thread 1 locked obj1");
                    synchronized (obj2){
                        System.out.println("Thread 1 locked obj2");
                    }
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread 2 start");
                synchronized (obj2){
                    System.out.println("Thread 2 locked obj2");
                    synchronized (obj1){
                        System.out.println("Thread 2 locked obj1");
                    }
                }
            }
        });
        thread1.start();
        thread2.start();
//        RandomAccessFile file = new RandomAccessFile("txt.txt",)
        try {
            BufferedReader reader = new BufferedReader(new FileReader("txt.txt"));
//            reader.
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}