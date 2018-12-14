package lesson_22_14_12_safeColl;

public class SomeClass {
    private static boolean run = true;

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

            }
        }
    }
}
