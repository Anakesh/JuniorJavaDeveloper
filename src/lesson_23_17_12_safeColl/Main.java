package lesson_23_17_12_safeColl;

import homework_11_05_12_patterns.TextProcessor.Message;

import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        /*
        Эти интерфейсы являются обертками над потоками и позволяют получать из потока данные
        //java.util.concurrent.Callable - возвращает данные из потока
        //java.util.concurrent.Future -
        .get() - позволяют получить из потока результат
        .get(long l, TimeUnit tu) - тоже самое но с временем ожидания
        .cancel - отменяет задачу
        .isCancelled() - true если задача была отменена
        .isDone() - true если задача завершилась

        Реализация:
        FutureTask
        new FutureTask(Callable<T> c);
        new FutureTask(Runnable r, V result));
         */
        FutureTask<String> task = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "Task 0 finished";
            }
        });

        new Thread(task).start();
        //ждем завкршение задачи
        try {
            System.out.println(task.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        FutureTask<String> task1 = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                int counter = 0;
                while(!Thread.currentThread().isInterrupted()&& counter<3){
                    counter++;
                    Thread.sleep(1000);
                }
                return "Task1 finished";
            }
        });
        new Thread(task1).start();
        Thread.sleep(2000);
        task1.cancel(true);
        System.out.println(task1.isCancelled());
        System.out.println(task1.isDone());

        //Список задач
//        FutureTask futureTask = new FutureTask();
        /*
        интерфейс Executor - позволяет управлять группой потоков (создание,удаление...)
        .execute(Runnable runnable)

        ExecutorService интерфейс обертка Execute
        .execute(new Thread(task)); == new Thread(task).start();
        .submit(new Thread(task1)); - добавляет задачу, выполненияет и возвращает FutureTask
        .invokeAll(Collection<? extends Callable<T>> tasks) - добавляет коллекцию задач,выполняет их и возвращает(List<FutureTask>)

        Executors - класс реализация
        Executors.newFixedThreadPool(int i) - создание пула потоков с фикс кол-вом
        Executors.newSingleThreadExecutor() - пул с одним потоком
        Executors.newCachedThreadPool() - пул с необх кол-вом потоков
        Executors.newScheduledThreadPool(int i) -
        */
        ExecutorService pool = Executors.newFixedThreadPool(2); // создание пула из 2 потоков
//        for(int i= 0;i<5;i++){
//            pool.submit(new Runnable() {
//                @Override
//                public void run() {
//                    System.out.println("pool: "+ Thread.currentThread().getName());
//                }
//            });
//        }

        Future[] tasks = new FutureTask[5];
        for(int i =0;i<tasks.length;i++){
            tasks[i] = pool.submit(new Thread(task));
        }

        pool.submit(new Thread(task));

        Future futureTask2 = pool.submit(new Thread(task));
        System.out.println(futureTask2);


        //метод submit возвращает объект Future
        //соотв ему доступны все его методы
//        pool.submit(new Thread(task1)).get();


        pool.shutdown();

        ExecutorService service = new ThreadPoolExecutor(
                1,//основное кол-во потоков
                10,//макс кол-во потоков
                //как долго избыточные потоки будут простаивать в ожидании
                2,
                TimeUnit.MINUTES,
                new LinkedBlockingQueue<>()// очередь для задач
        );

        for(int i = 0;i<3;i++){
            service.submit(new Runnable() {
                @Override
                public void run() {
                    //код
                }
            });
        }
        /*
        объекты синхронизации
        CountDownLatch(int num) - создание объекта который блокирует выполнение потоков
         пока не наступят опред условия, num - кол-во событий
        .await(); - блокировка потоков
        .countDown(); - уменьшение счетчика

        Exchanger - позволяет обмениваться данными между потоками
        .exchange(T buffer) - buffer объект для синхронизации

         */
        Exchanger<Message> exchanger = new Exchanger<>();
        Message[] messages = new Message[4];
        for(int i =0;i<messages.length;i++){
            messages[i] = exchanger.exchange(messages[i]); //обмен объектами отправляемкущий message[4] и записываем полученное в message[4]
        }
    }
}


/*
Создать след классы:
user: id,email
account(счет): int id, int user_id, int moneyAmount
bank: List<Account> accountList(все),
    transferMoney(Account source, Account destination, int amount)
        source!=destination
        source.moneyAmount >= amount
transaction (независимый класс, поток): Account source, Account dest, Bank bank, int amount, Date date
 */