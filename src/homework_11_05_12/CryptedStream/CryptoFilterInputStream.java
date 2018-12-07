package homework_11_05_12.CryptedStream;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;


    /**
     * Created by Pavel on 07.12.2018.
     */
    public class CryptoFilterInputStream extends FilterInputStream {
    private byte[] key;
    private int currentKeyPosition = 0;

    public CryptoFilterInputStream(InputStream in, byte[] key) throws IOException {
        super(in);
        this.key = key;
    }

    @Override
    public int read() throws IOException {
        int buff = super.read();
        buff = buff^key[currentKeyPosition%key.length];
        currentKeyPosition++;
        return buff;
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        int num = super.read(b, off, len);
        for(int i=off;i<off+len;i++){
            b[i] = (byte) (b[i]^key[currentKeyPosition %key.length]);
            currentKeyPosition++;
        }
        return num;
    }
}
