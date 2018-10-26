package com.gupaoedu.kafka;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class MyPartition implements Partitioner {
    private Random random = new Random();
    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        List<PartitionInfo> partitionInfoList = cluster.partitionsForTopic(topic);
        int partitionNum = 0;
        if(key == null){
            partitionNum =  random.nextInt(partitionInfoList.size());
        }else{
            partitionNum = Math.abs((key.hashCode())%partitionInfoList.size());
        }
        System.out.println("key:"+key+",value:"+value+"->partition:"+partitionNum);
        return partitionNum;
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> map) {

    }

    public static void main(String[] args) {
        int list = 3;
        String key = "abdsfwrw23";
        for (int i = 0 ; i < 10 ; i ++){
            System.out.println(Math.abs((key+i).hashCode()%list));
        }

    }
}
