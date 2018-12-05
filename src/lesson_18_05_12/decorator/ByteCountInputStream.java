package lesson_18_05_12.decorator;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ByteCountInputStream extends FilterInputStream {

    private long readBytes;

    protected ByteCountInputStream(InputStream in) {
        super(in);
    }

    @Override
    public int read() throws IOException {
        int res = super.read();
        if(res>= 0)readBytes++;
        return res;
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        int res = super.read();
        if(res>0) readBytes+=res;
        return res;
    }

}
