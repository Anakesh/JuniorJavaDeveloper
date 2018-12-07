package homework_11_05_12.CryptedStream;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class CryptoFilterOutputStream extends FilterOutputStream {

    private byte[] key;
    private int currentKeyPosition;

    public CryptoFilterOutputStream(OutputStream out, byte[] key) throws IOException {
        super(out);
        this.key = key;
    }

    @Override
    public void write(int val) throws IOException {
        val = val^key[currentKeyPosition %key.length];
        currentKeyPosition++;
        super.write(val);
    }

    /*@Override
    public void write(byte[] b, int off, int len) throws IOException {
        for(int i=off;i<off+len;i++){
            b[i] = (byte) (b[i]^key[currentKeyPosition %key.length]);
            currentKeyPosition++;
        }
        super.write(b, off, len);
    }*/
}

