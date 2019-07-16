package com.arcln.pattern.decorator.io;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author HXS
 * @copyright
 * @since 2019-07-01
 */
public class LowerCaseInputStream extends FilterInputStream {
    protected LowerCaseInputStream(InputStream in) {
        super(in);
    }

    @Override
    public int read() throws IOException {
        int r = super.read();
        return r;
    }

    @Override
    public int read(byte b[]) throws IOException {
        int r = read(b, 0, b.length);
        return r;
    }

    public int read(byte b[], int off, int len) throws IOException{
        int r = super.read(b, off, len);
        for (int i = off; i < off + r; i ++){
            System.out.println(i+"---["+b[i]+"]-");
            b[i] = (byte) Character.toLowerCase(b[i]);
        }
        return r;
    }

}
