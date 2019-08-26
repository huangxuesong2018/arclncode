package com.gupao.edu.vip.nio.talk;

import com.gupao.edu.vip.nio.talk.client.SendChannel;
import com.gupao.edu.vip.nio.talk.client.UserInput;

/**
 * Netty版本的 网络聊天室  客户端
 * @author HXS
 * @copyright
 * @since 2019-08-23
 */
public class StartClient {
    public static void main(String[] args) {
        new Thread(new UserInput()).start();
    }
}
