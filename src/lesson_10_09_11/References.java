package lesson_10_09_11;

import java.lang.ref.PhantomReference;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

public class References {
    public static void main(String[] args) throws InterruptedException {
        Object[] objects = new Object[1024*1024];
        for(int i = 0;;i++){
            if(i<0)
                i=0;
            byte[] bytes = new byte[1024*1024];
            objects[i%objects.length] = new SoftReference<>(bytes);
//                                          WeakReference<>(bytes);
//                                          PhantomReference<>(bytes);
            Thread.sleep(10);
        }
    }
}