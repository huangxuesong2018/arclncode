package com.gupao.spring.cloud.stream.kafka.raw.api;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Kafka原始api，生产消息
 * @author HXS
 * @copyright
 * @since 2019-01-21
 */
public class KafkaProducerDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //初始化配置
        Properties properties = new Properties();
        properties.put("bootstrap.servers","192.168.1.130:9092");
        properties.put("key.serializer",StringSerializer.class.getName());
        properties.put("value.serializer",StringSerializer.class.getName());
        //创建Kafka Producer
        KafkaProducer kafkaProducer = new KafkaProducer(properties);
        String topic = "topic2018";//指定topic
        Integer partition = 0;//指定分区，
        Long timestamp = System.currentTimeMillis();
        String key = "message-key";//可以不用传
        //要发送的消息
        String value = "message-value-"+System.currentTimeMillis();
        ProducerRecord record = new ProducerRecord(topic,partition,timestamp,key,value);
        Future<RecordMetadata> future =  kafkaProducer.send(record);
        future.get();
    }
}
