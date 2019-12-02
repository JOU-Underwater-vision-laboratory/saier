package com.hhit.common.redis.shiro;

/**
 * @author hhit 1992lcg@163.com
 * @version V1.0
 */

import org.springframework.beans.factory.annotation.Value;
import redis.clients.jedis.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 */
public class RedisManager {

    @Value("${spring.redis.cluster.nodes:null}")
    //默认是"null"不是null关键字
    private String nodes;
    @Value("${spring.redis.pool.max-idle:1}")
    private Integer maxIdle;
    @Value("${spring.redis.pool.min-idle:0}")
    private Integer minIdle;
    @Value("${spring.redis.pool.max-active:1}")
    /** 对应配置文件时max-active */
    private Integer maxTotal;
    @Value("${spring.redis.pool.max-wait:1}")
    private Integer maxWait;
    @Value("${spring.redis.timeout:0}")
    private Integer timeout;

    private static JedisCluster jedis = null;

    public RedisManager() {

    }

    //利用参数返回JedisPoolConfig对象
    public JedisPoolConfig getConfig() {
        JedisPoolConfig config = new JedisPoolConfig();
        //设定参数
        config.setMaxIdle(maxIdle);
        config.setMinIdle(minIdle);
        config.setMaxTotal(maxTotal);
        config.setMaxWaitMillis(maxWait);
        return config;
    }

    /**
     * 初始化方法
     */
    public void init() {
            //获取infoList
//        List<JedisShardInfo> infoList =
//                new ArrayList<JedisShardInfo>();
            Set<HostAndPort> hostAndPortsSet = new HashSet<>();
            //处理字符串nodes
            if (!("null".equals(nodes))) {
                String[] hostAndPorts = nodes.split(",");
                for (String node : hostAndPorts) {
                    //每个node格式192.168.198.40:6379
                    String[] hostAndPort = node.split(":");
                    //生成一个info对象,添加到list中
                    hostAndPortsSet.add(new HostAndPort(hostAndPort[0],
                            Integer.parseInt(hostAndPort[1])));
                }
                //用config对象和list对象生产jedis分片连接池
                jedis = new JedisCluster(hostAndPortsSet,timeout,getConfig());
            }

    }

    /**
     * get value from redis
     *
     * @param key
     * @return
     */
    public byte[] get(byte[] key) {
        byte[] value = null;
        value = jedis.get(key);
        return value;
    }
    public String get(String key) {

        return jedis.get(key);
    }
    /**
     * set
     *
     * @param key
     * @param value
     * @return
     */
    public byte[] set(byte[] key, byte[] value) {
        jedis.set(key, value);

        return value;
    }

    public void set(String key, String value) {
        jedis.set(key, value);
    }

    /**
     * 设置List集合
     * @param key
     * @param list
     */
    public void setList(String key ,List<?> list){
        try {
            if(list == null || list.size() == 0){
                jedis.set(key.getBytes(), "".getBytes());
            }else{//如果list为空,则设置一个空
                jedis.set(key.getBytes(), SerializeUtils.serialize(list));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取List集合
     * @param key
     * @return
     */
    public List<?> getList(String key){

        if(jedis == null || !jedis.exists(key)){
            return null;
        }
        byte[] data = jedis.get(key.getBytes());

        return (List<?>) SerializeUtils.deserialize(data);
    }


    /**
     * set
     *
     * @param key
     * @param value
     * @param expire
     * @return
     */
    public byte[] set(byte[] key, byte[] value, int expire) {

            jedis.set(key, value);
            if (expire != 0) {
                jedis.expire(key, expire);
            }

        return value;
    }

    /**
     * del
     *
     * @param key
     */
    public void del(byte[] key) {

            jedis.del(key);

    }

    /**
     * flush
     */
    public void flushDB() {

            jedis.flushDB();

    }

    /**
     * size
     */
    public Long dbSize() {
        Long dbSize = 0L;
        dbSize = jedis.dbSize();

        return dbSize;
    }

    /**
     * keys
     *
     * @param
     * @return
     */
    public Set<String> keys(String pattern) {
        Set<String> keys = null;

            keys = jedis.hkeys(pattern);

        return keys;
    }

    public String getNodes() {
        return nodes;
    }

    public void setNodes(String nodes) {
        this.nodes = nodes;
    }

    public Integer getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(Integer maxIdle) {
        this.maxIdle = maxIdle;
    }

    public Integer getMinIdle() {
        return minIdle;
    }

    public void setMinIdle(Integer minIdle) {
        this.minIdle = minIdle;
    }

    public Integer getMaxTotal() {
        return maxTotal;
    }

    public void setMaxTotal(Integer maxTotal) {
        this.maxTotal = maxTotal;
    }

    public Integer getMaxWait() {
        return maxWait;
    }

    public void setMaxWait(Integer maxWait) {
        this.maxWait = maxWait;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public static JedisCluster getJedis() {
        return jedis;
    }

    public static void setJedis(JedisCluster jedis) {
        RedisManager.jedis = jedis;
    }

    public int getExpire() {

        return 24000;
    }
}
