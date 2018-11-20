package com.gupaoedu.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

import java.util.Arrays;
import java.util.Collections;
import java.util.Properties;

public class KafkaConsumerDemo1 extends Thread{
    private String topic;
    private final KafkaConsumer kafkaConsumer;

    public KafkaConsumerDemo1(String topic) {
        Properties properties=new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"192.168.1.130:9092,192.168.1.131:9092");
        properties.put(ConsumerConfig.GROUP_ID_CONFIG,"KafkaConsumerDemo2");//同一组的consumer 可以获取一次消息，然后组内
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,"false");//false 时，不会确认消息，所以可以再次消费
        properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG,"1000");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.IntegerDeserializer");
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringDeserializer");
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");
        kafkaConsumer=new KafkaConsumer(properties);
        this.topic = topic;
        subscribe();
        // assign();
    }

    /**
     * 第1种消费方式
     */
    private void subscribe(){
        kafkaConsumer.subscribe(Collections.singletonList(topic));
    }
    /**
     * 第2种 指定消费分区
     */
    private void assign(){
        TopicPartition topicPartition = new TopicPartition(this.topic,0);
        kafkaConsumer.assign(Arrays.asList(topicPartition));
    }

    @Override
    public void run() {

        while(true){
            ConsumerRecords<Integer,String> consumerRecord=kafkaConsumer.poll(1000);
            for(ConsumerRecord record:consumerRecord){
                System.out.println("partition->"+record.partition()+",message receive:"+record.value());
                kafkaConsumer.commitAsync();//手动提交
            }
        }
    }


    public static void main(String[] args) {
        new KafkaConsumerDemo("test001").start();
    }
}
