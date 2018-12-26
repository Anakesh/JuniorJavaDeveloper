package lesson_27_26_12_nio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class NioApi {
    //Channel: (все каналы работают через буфер) не блокируется во время чтения/записи (может делать оба)
    //FileChannel
    //DatagramChannel - UDP протокол
    //SocketChannel - TCP протокол
    //ServerSocketServer - TCP сервер
    //Buffer
    //Selector

    public static void write(File file) throws IOException {
        try(RandomAccessFile raf = new RandomAccessFile(file,"rw")){
            //Получаем канал из RandomAccessFile
            FileChannel channel = raf.getChannel();
            String text = "Text\n"+"Text\n"+"Text\n";
            byte[] bytes = text.getBytes(Charset.forName("utf-8"));
            //Создаем буфер
            ByteBuffer buffer = ByteBuffer.allocate(512);

            //Заполняем буфер данными
             buffer.put(bytes);// имеет position(указатель на текущее полодение) и limit(размер буфера)
             buffer.flip();  //устанавливает limit равный position, а  position  в 0
            //Записываем данные из буфера в канал курсок переметится на позицию, равную количеству записанных байт
            int written = channel.write(buffer);
            System.out.println("written:" +
                    written+ "byte to file"+file.getAbsolutePath());
        }
    }

    public static void read(File file) throws IOException {
        try(RandomAccessFile raf = new RandomAccessFile(file,"rw")){
            FileChannel channel = raf.getChannel();
            //буфер чтения данных
            ByteBuffer buffer = ByteBuffer.allocate(512);
            StringBuilder builder = new StringBuilder();

            //чтение данныъ
            while(channel.read(buffer)!=-1){
                builder.append(new String(buffer.array(),0,buffer.remaining()));
                //очищение буфера
                buffer.clear();
            }
            System.out.println("Read from file "+file+":");
            System.out.println(builder);
        }
    }

    public static void main(String[] args) {
        try{
            write(new File("file.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Buffer
//        Параметры:
//        capacity емкость не меняется
//        position текущее положение курсора
//        limit предел для позиции
        ByteBuffer buffer = ByteBuffer.allocate(512);
//        buffer.
//        putXXX()/getXXX() - запись и чтение данных в буфер
//        flip()
//        rewind() - сброс позиции на 0
//        clear()
//        remaining() разница между лимитом и позицией
        buffer.position(); // текущая позиция курсора
        buffer.capacity(); //размер буфера
        buffer.limit(400); //установка лимита
        buffer.limit();    //текущий лимит
        buffer.remaining();

//        Path path = Paths.get();

//        Files.copy()
    }

}
