package lesson_15_21_11_streams;

//io api

import java.io.*;
import java.net.*;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Vector;

public class IOStreams {
//    InputStream // Ввод информации в байтах
//    OutputStream //вывод информации в байтах

    protected long readByte(File file) throws IOException {
        long byteSum = 0;

        try(FileInputStream fileInputStream = new FileInputStream(file)){
            System.out.println(fileInputStream.available());
            while(fileInputStream.available()>0){
                int data = fileInputStream.read();
                byteSum+=data;
                System.out.print((char) data);
            }
            return byteSum;
        }
    }

    private void readByteArray(File file, Charset charset) throws IOException {
        try(InputStream in =  new FileInputStream(file);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream())
        {
            byte[] buf = new byte[10];
            int len;
            while((len = in.read(buf))>0){
                System.out.println(Arrays.toString(buf));
                byteArrayOutputStream.write(buf, 0, len);
            }
            System.out.println(Arrays.toString(byteArrayOutputStream.toByteArray()));
            System.out.println(new String(byteArrayOutputStream.toByteArray(), charset));
        }
    }

    private void writeToFile(File file, boolean append,
                             Charset charset) throws IOException {
        try(FileOutputStream fileOutputStream = new FileOutputStream(file,append)){
            StringBuilder sb = new StringBuilder();
            for(int i = 0;i<6;i++){
                sb.append("line ").append(i).append('\n');
            }
            byte[] bytes = sb.toString().getBytes(charset);
            fileOutputStream.write(bytes);
        }
    }

    public void writeWithBuffer(File file) throws IOException {
        try(FileOutputStream out = new FileOutputStream(file);
        BufferedOutputStream bout = new BufferedOutputStream(out);
        ){
            StringBuilder sb = new StringBuilder();
            for(int i = 0;i<60;i++){
                sb.append("line ").append(i).append('\n');
            }
            byte[] buffer = sb.toString().getBytes();
            bout.write(buffer,0,buffer.length);
        }
    }

    private void readFromTwoFiles(File[] files, Charset charset) throws IOException {
        try(    InputStream input1 = new FileInputStream(files[0]);
                InputStream input2 = new FileInputStream(files[1]);
                ByteArrayOutputStream bout = new ByteArrayOutputStream();)
        {
            SequenceInputStream sequenceInputStream = new SequenceInputStream(input1,input2);
            byte[] buf = new byte[1024];
            int len;
            while ((len = sequenceInputStream.read(buf))>0){
                bout.write(buf, 0 ,len);
            }
            System.out.println(new String(
                    bout.toByteArray(),charset
            ));
        }
    }

    private void readFromMultipleFiles(File[] files,Charset charset) throws IOException {
        try(    InputStream input1 = new FileInputStream(files[0]);
                InputStream input2 = new FileInputStream(files[1]);
                ByteArrayOutputStream bout = new ByteArrayOutputStream();)
        {
            InputStream[] inputStreams = new InputStream[files.length];
            Vector<InputStream> stream = new Vector<>();
            for(int i = 0;i<files.length;i++){
                inputStreams[i] = new FileInputStream(files[i]);
                stream.add(inputStreams[i]);
            }

            SequenceInputStream sequenceInputStream = new SequenceInputStream(stream.elements());
            byte[] buf = new byte[1024];
            int len;
            while ((len = sequenceInputStream.read(buf))>0){
                bout.write(buf, 0 ,len);
            }
            System.out.println(new String(
                    bout.toByteArray(),charset
            ));
        }

    }

    private static void  dataOutput(OutputStream out) throws IOException {
        DataOutputStream dataOutputStream =  new DataOutputStream(out);
        dataOutputStream.writeBoolean(true);
        dataOutputStream.writeInt(1233);
        dataOutputStream.writeUTF("hello\n");
    }

    private static void dataInput(InputStream in) throws  IOException{
        DataInputStream dataInputStream = new DataInputStream(in);
        boolean b = dataInputStream.readBoolean();

        int i = dataInputStream.readInt();
        String str = dataInputStream.readUTF();
//        System.out.println(i);
        System.out.printf("%s %s %s", b,i,str);
    }

    public String readUrl(String url, Charset charset) throws IOException {
        HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
        con.setRequestProperty("Accept-Charse", charset.name());
        try(InputStream in = con.getInputStream()){
            return readText(in, charset);
        }
    }
    private String readText(InputStream in, Charset charset) throws IOException {
        InputStreamReader reader = new InputStreamReader(in,charset);
        StringBuilder sb = new StringBuilder();
        char[] buf = new char[20];
        int len;
        while((len = reader.read(buf))>0){
            sb.append(buf, 0,1);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
//        PipedInputStream pipedInputStream;
//        PipedOutputStream pipedOutputStream;

//        FilterInputStream filterInputStream;
//        FilterOutputStream filterOutputStream;

//        RandomAccessFile

        IOStreams ioStreams = new IOStreams();
        //чтение из файлв по байту
        File file = new File("file.txt");
        File file1 = new File("file2.txt");
        try {
//            System.out.println(ioStreams.readByte(file));
//            ioStreams.readByteArray(file, Charset.forName("UTF-8"));
//            ioStreams.writeToFile(file,false,Charset.forName("UTF-8"));
//            ioStreams.writeWithBuffer(file);
//            ioStreams.readFromTwoFiles(new File[]{file,file1},Charset.forName("UTF-8"));
/*            dataOutput(System.out);
            try(OutputStream out = new FileOutputStream(file)){
                dataOutput(out);
            } catch (IOException ex){
                ex.printStackTrace();
            }
            try(InputStream in = new FileInputStream(file)){
                dataInput(in);
            }catch (IOException ex){
                ex.printStackTrace();
            }*/
            String str = ioStreams.readUrl("https://www.google.ru/",
                    Charset.forName("UTF-8"));
            System.out.println(str);
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }
}
