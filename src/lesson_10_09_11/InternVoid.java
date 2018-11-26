package lesson_10_09_11;

public class InternVoid {
    public static final String DATA = "LONG STRING LONG STRING LONG STRING LONG STRING" +
            "LONG STRING LONG STRING LONG STRING LONG STRING" +
            "LONG STRING LONG STRING LONG STRING LONG STRING" +
            "LONG STRING LONG STRING LONG STRING LONG STRING";
    public static void main(String[] args) throws InterruptedException {
        String str = new String("String");
        String str2 = new String("String");
        System.out.println(str.intern()==str2.intern());
        System.out.println(str==str2);

        String[] strings = new String[1024*1024];
        for(int i=0;;i++){
            if(i<0)
                i=0;
            byte[] bytes = new byte[1024*1024];
            strings[i%strings.length] = new String(DATA).intern();

            if(i%100==0)
                Thread.sleep(1);
        }
    }
}
