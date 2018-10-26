package com.gupaoedu.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class JMSQueueListenerRecever {
    public static void main(String[] args) {
        //创建连接工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.1.130:61616");
        Connection connection = null;
        try {
            connection = connectionFactory.createConnection();
            connection.start();
            /**
             * 创建会话
             */
            Session session = connection.createSession(
                    Boolean.TRUE,
                    Session.AUTO_ACKNOWLEDGE);

            //创建目的地
            Destination destination = session.createQueue("myQueue1");
            //创建一个接收者
            MessageConsumer consumer = session.createConsumer(destination);

            MessageListener messageListener = new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    try {
                        if(message instanceof  TextMessage){
                            System.out.println(((TextMessage)message).getText());
                        }
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            };

            while (true){
                consumer.setMessageListener(messageListener);
                session.commit();
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }finally {
            if(connection != null){
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
