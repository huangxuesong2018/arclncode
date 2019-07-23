package com.arcln.pattern.template.demo2;

import javax.swing.*;
import java.awt.*;

/**
 * @author HXS
 * @copyright
 * @since 2019-07-16
 */
public class MyFrame extends JFrame {
    public MyFrame(String title) throws HeadlessException {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300,300);
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawString("Let go..",100,100);
    }

    public static void main(String[] args) {
        MyFrame frame = new MyFrame("test");
    }
}
