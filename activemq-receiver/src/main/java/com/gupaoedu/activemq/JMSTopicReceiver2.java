package com.gupaoedu.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 *
 */
public class JMSTopicReceiver2 {
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
            Destination destination = session.createTopic("myTopic");
            //创建一个接收者
            MessageConsumer consumer = session.createConsumer(destination);
            //通过生产者发送消息,这个地方没有消息的话，会阻塞
            Message message = consumer.receive();

            if(message instanceof  TextMessage){
                TextMessage textMessage = (TextMessage)message;
                System.out.println(textMessage.getText());
            }

            session.commit();
            session.close();
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
