package com.gupaoedu.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * ActiveMQ  点对点消息传送
 */
public class JMSQueueProducer {
    public static void main(String[] args) {

        //1、创建工厂连接对象，需要制定ip和端口号
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.1.130:61616");
        Connection connection = null;
        try {
            //2、使用连接工厂创建一个连接对象
            connection = connectionFactory.createConnection();
            //3、开启连接
            connection.start();
            //4、使用连接对象创建会话（session）对象
            Session session = connection.createSession(
                    Boolean.TRUE,   //开启事务
                    Session.AUTO_ACKNOWLEDGE);

            //5.用会话对象创建目标对象，包含queue和topic（一对一和一对多）
            Destination destination = session.createQueue("myQueue");
            //6、使用会话对象创建生产者对象
            MessageProducer producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.PERSISTENT);//持久化后，mq服务器挂掉重启后，消费端还可以被接收
            //producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            //7、使用会话对象创建一个消息对象
            TextMessage textMessage = session.createTextMessage("hello world");
            //8、发送消息
            producer.send(textMessage);

            session.commit(); //提交事务

            //9、关闭资源
            producer.close();
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
