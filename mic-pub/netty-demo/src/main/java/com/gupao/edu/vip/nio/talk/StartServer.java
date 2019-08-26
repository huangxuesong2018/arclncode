package com.gupao.edu.vip.nio.talk;

import com.gupao.edu.vip.nio.talk.server.TalkServer;

/**
 * Netty版本的 网络聊天室  服务端
 * @author HXS
 * @copyright
 * @since 2019-08-23
 */
public class StartServer {
    public static void main(String[] args) {
        new TalkServer(8080).start();
    }
}
