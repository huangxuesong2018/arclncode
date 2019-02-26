package com.gupao.micro.service.spring.cloud.stream.binder.rocketmq;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.cloud.stream.binder.Binder;
import org.springframework.cloud.stream.binder.Binding;
import org.springframework.cloud.stream.binder.ConsumerProperties;
import org.springframework.cloud.stream.binder.ProducerProperties;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.messaging.support.GenericMessage;

import javax.swing.text.html.HTML;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Binder 实现步骤
 A typical binder implementation consists of the following:
    1,A class that implements the Binder interface; （实现 Binder  接口） {@link RocketMQMessageChannelBinder}
    2,A Spring @Configuration class that creates a bean of type Binder along with the middleware
       connection infrastructure.Binder 实现类上标注 @Configuration 注解）{@link RocketMQMessageChannelBinderConfiguration}
    3,A META-INF/spring.binders file found on the classpath containing one or more binder definitions,
       as shown in the following example:（META-INF/spring.binders  配置 Binder 名称和 Binder 实现自动装配类映射）
 * @author HXS
 * @copyright
 * @since 2019-02-15
 */
public class RocketMQMessageChannelBinder implements
        Binder<MessageChannel, ConsumerProperties, ProducerProperties> {
    private static final String TOPIC = "TEST_TOPIC";
    private static final String TAG = "TEST_TAG";
    private static final String GROUP = "TEST-GROUP";
    private static final String NAME_ADDRESS = "192.168.1.130:9876";

    @Override
    public Binding<MessageChannel> bindConsumer(String name, String group, MessageChannel inputChannel, ConsumerProperties consumerProperties) {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(GROUP);
        consumer.setNamesrvAddr(NAME_ADDRESS);
        try {
            consumer.subscribe(TOPIC, TAG);
            consumer.registerMessageListener(new MessageListenerConcurrently() {
                @Override
                public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
                                                                ConsumeConcurrentlyContext context) {
                    System.out.printf("%s Receive New Messages: %s %n", Thread.currentThread().getName(), msgs);
                    msgs.forEach(msg->{
                        byte[]body = msg.getBody();
                        //发送消息到消息管道
                        inputChannel.send(new GenericMessage<Object>(body));
                    });
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
            });
            consumer.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ()->{
            System.out.println("consumer shutdown");
            consumer.shutdown();
        };
    }

    /**
     * 发送消息
     * @param name
     * @param outboundBindTarget
     * @param producerProperties
     * @return
     */
    @Override
    public Binding<MessageChannel> bindProducer(String name, MessageChannel outboundBindTarget, ProducerProperties producerProperties) {
        DefaultMQProducer producer = new DefaultMQProducer(GROUP);
        producer.setNamesrvAddr(NAME_ADDRESS);
        try {
            producer.start();
            SubscribableChannel subscribableChannel = (SubscribableChannel)outboundBindTarget;
            //消息订阅回调
            subscribableChannel.subscribe(message -> {
                Object messageBody = message.getPayload();
                Message mqMessage = new Message();
                mqMessage.setTopic(TOPIC);
                mqMessage.setTags(TAG);
                try {
                    mqMessage.setBody(serialize(messageBody));
                    SendResult sendResult = producer.send(mqMessage);
                    System.out.printf("消息发送:%s%n", sendResult);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ()->{
            producer.shutdown();
        };
    }

    private byte[] serialize(Object serializable) throws IOException {
        if(serializable instanceof byte[] ){
            return (byte[])serializable;
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(serializable);
        return outputStream.toByteArray();
    }
}
