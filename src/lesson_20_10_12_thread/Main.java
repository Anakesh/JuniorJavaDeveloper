package lesson_20_10_12_thread;

public class Main {

    public static void main(String[] args) throws InterruptedException {
//  методы thread:
//        static сurrentThread();
//        setName(String);
//        getName();
//        join();
//        isAlive();
//        isInterrupted();
//        interrupt();
        System.out.println(Thread.currentThread().getName());
/*        Thread thread = new Thread();
        thread.start();

        Thread myThread = new MyThread();
        myThread.start();

        Thread otherThread = new Thread(new OtherThread());
        otherThread.start();

        for(int i=0;i<5;i++){
            Thread otherOtherThread = new Thread(new OtherThread());
            otherOtherThread.setName("T-"+i);
            otherOtherThread.start();
        }*/
        /*Thread thread4 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Anon class thread");
                System.out.println("During "+Thread.currentThread().isAlive());
                System.out.println("During "+Thread.currentThread().getState());
            }
        });
        System.out.println("Before start" + thread4.isAlive());
        System.out.println("Before start" + thread4.getState());
        thread4.start();
        //ожидание, пока поток завершится
        thread4.join();
        System.out.println("After join " + thread4.isAlive());
        System.out.println("After join " + thread4.getState());*/

/*        Thread thread5 = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try{
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread5.start();*/

        //Поки отмеченные как daemon будут остановленны сразу после остановки последнего потока
/*        Thread thread6 = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try{
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread6.setDaemon(true);
        thread6.start();*/

        //остановка потоков
//        1. поток выполнил все инструкции в run()
//        2. в run() было выброшено необрабатываемое исключение
//        3. остановка JVM
//        4. поток является daemon и последний основной поток завершился

        //механизм прерывания
        Thread thread7 = new Thread(new Runnable() {
            @Override
            public void run() {
                while(!Thread.currentThread().isInterrupted()){
                    try{
                        System.out.println("Thread7");
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        System.out.println("Interrupted exception");
                        System.out.println("Catch1: "+
                                Thread.currentThread().isInterrupted());
                        Thread.currentThread().interrupt();
                        System.out.println("Catch2: "+
                                Thread.currentThread().isInterrupted());
                    }
                }
            }
        });

        thread7.start();
        Thread.sleep(2000);
        System.out.println(thread7.isInterrupted());
        thread7.interrupt();
    }
}

//для создания своего класса потока нужно унаследовать класс от Thread
class MyThread extends Thread{
    @Override
    public void run() {
        System.out.println("MyThread"+
                Thread.currentThread().getName());
    }
}

//либо реализовать интерейс Runnable, разницы нет
class OtherThread implements Runnable{

    @Override
    public void run() {
        System.out.println("OtherThread"+
                Thread.currentThread().getName());
    }
}
