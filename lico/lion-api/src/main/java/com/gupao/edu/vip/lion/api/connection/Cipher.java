package com.gupao.edu.vip.lion.api.connection;

/**
 * 密码
 */
public interface Cipher {
    byte[] decrypt(byte[] data);
    byte[] encrypt(byte[] data);
}
