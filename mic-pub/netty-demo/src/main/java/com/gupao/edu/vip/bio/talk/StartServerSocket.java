package com.gupao.edu.vip.bio.talk;

import com.gupao.edu.vip.bio.talk.server.ServerListener;

/**
 * 同步阻塞IO模型
 * 聊天室Server端启动
 * @author HXS
 * @copyright
 * @since 2019-08-16
 */
public class StartServerSocket {
    public static void main(String[] args) {
        new ServerListener().start();
    }
}
