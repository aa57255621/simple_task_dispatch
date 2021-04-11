package com.example.demo.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author lp225484
 */
public class JedisCacheClient {

    private static JedisPool jedisPool;

    static {
        if (jedisPool == null) {
            jedisPool = SpringBeanUtil.getBean(JedisPool.class);
        }
    }

    private Integer times = 3600;

    /**
     * setVExpire(设置key值，同时设置失效时间 秒
     */
    public static void set(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.set(key, value);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(jedis);
        }

    }

    /**
     * (存入redis数据)
     */
    public static void expire(String key, String value, Integer times) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.set(key, value);
            jedis.expire(key, times);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(jedis);
        }
    }

    /**
     * 删除redis数据
     */
    public static void del(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.del(key);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(jedis);
        }
    }

    /**
     * 获取key的值
     */
    public static String get(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String s = jedis.get(key);
            return s;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(jedis);
        }
        return null;
    }

    /**
     * 释放连接
     *
     * @param jedis
     */
    public static void close(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
            if (jedis.isConnected()) {
                try {
                    jedis.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
