package sentinel;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 哨兵
 * @author HXS
 * @copyright
 * @since 2019-03-15
 */
public class Demo {
    public static void main(String[] args) {
        String masterName = "mymaster";
        String[] a = new String[]{
                "192.168.1.129:5000","192.168.1.129:5001",
                "192.168.1.129:5002","192.168.1.130:5000"};
        Set<String> sentinels = new HashSet<>(Arrays.asList(a));
        //通过哨兵节点和，mastername 会发现主节点
        JedisSentinelPool sentinelPool = new JedisSentinelPool(masterName,sentinels);
        Jedis jedis = sentinelPool.getResource();
        jedis.append("hello","hs");
        System.out.println(sentinelPool.getCurrentHostMaster());
    }
}
