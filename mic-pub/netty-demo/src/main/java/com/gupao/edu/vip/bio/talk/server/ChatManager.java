package com.gupao.edu.vip.bio.talk.server;


import java.util.Vector;

/**
 * 管理所有在线用户
 * @author HXS
 * @copyright
 * @since 2019-08-16
 */
public class ChatManager {
    private ChatManager(){}
    private static final ChatManager CM = new ChatManager();
    private static Vector<ChatSocket> vector = new Vector<>();

    public static ChatManager getChatManager(){
        return CM;
    }

    /**
     * 用户上线
     * @param cs
     */
    public void add(ChatSocket cs) {
        vector.add(cs);
        System.out.println("在线人数合计:"+vector.size());
    }

    /**
     * 用户下线
     * @param cs
     */
    public void remove(ChatSocket cs){
        vector.remove(cs);
        System.out.println("在线人数合计:"+vector.size());
    }

    /**
     * 向所有在线用户推送消息，自已除外
     * @param cs
     * @param msg
     */
    public void publish(ChatSocket cs,String msg){
        for (int i = 0; i < vector.size(); i ++){
            ChatSocket temp = vector.get(i);
            if (cs != temp){
                temp.out(msg);
            }
        }
    }
}
