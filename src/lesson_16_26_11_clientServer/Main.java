package lesson_16_26_11_clientServer;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        File file = new File("stat.bin");
        LaunchStat stat;
        if(!file.exists())
            stat = new LaunchStat();
        else{
            try(ObjectInputStream objIn =
                    new ObjectInputStream(
                            new FileInputStream(file)
                    )){
                stat = (LaunchStat) objIn.readObject();
            }
        }
        if(stat.isFIrstLaunch()){
            System.out.println("Первый запуск");
        }else {
            System.out.println(stat);
        }
        stat.update();
        //сохранение
        try(ObjectOutputStream objOut =
                new ObjectOutputStream(
                        new FileOutputStream(file)
                )){
            objOut.writeObject(stat);
        }

        File file2 = new File("stat2.bin");

        LaunchStatExetern stat2;
        if(!file2.exists()){
            stat2 = new LaunchStatExetern();
        } else{
            try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file2))){
                stat2 = (LaunchStatExetern) ois.readObject();
            }
        }
        if(stat2.isFIrstLaunch()){
            System.out.println("Первый запуск");
        } else{
            System.out.println(stat2);
        }

        stat2.update();

        try(ObjectOutputStream ous = new ObjectOutputStream(new FileOutputStream(file2))){
            ous.writeObject(stat2);
        }

    }
}
