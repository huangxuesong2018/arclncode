package com.gupao.edu.vip.nio.buffer;

import java.nio.ByteBuffer;

/**
 * @author HXS
 * @copyright
 * @since 2019-04-19
 */
public class Buffers {
    ByteBuffer readBuffer;
    ByteBuffer writeBuffer;

    public Buffers(int readCapacity, int writeCapacity){
        readBuffer = ByteBuffer.allocate(readCapacity);
        writeBuffer = ByteBuffer.allocate(writeCapacity);
    }

    public ByteBuffer getReadBuffer(){
        return readBuffer;
    }

    public ByteBuffer gerWriteBuffer(){
        return writeBuffer;
    }

}
