package com.gupao.micro.service.spring.cloud.client.event;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author HXS
 * @copyright
 * @since 2019-01-28
 */
public class GUIEvent {
    public static void main(String[] args) {
        JFrame frame = new JFrame("a");
        //java 8之前，接口没有default方法
        frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.printf("[%s]事件:%s\n",Thread.currentThread().getName(),e);
            }
        });
        frame.setBounds(300,300,400,300);
    }
}

