package com.gupao.edu.vip.nio.talk.entity;

import io.netty.handler.codec.serialization.ClassResolver;
import io.netty.handler.codec.serialization.ObjectDecoder;

/**
 * @author HXS
 * @copyright
 * @since 2019-08-23
 */
public class MessageDecoder extends ObjectDecoder {
    public MessageDecoder() {
        super(new ClassResolver() {
            @Override
            public Class<?> resolve(String className) throws ClassNotFoundException {
                return null;
            }
        });
    }
}
