package com.redis;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author cxk
 * 2023/1/10 14:18
 */

@Component
public class RedisDemo {

    public static void main(String[] args) {
        //连接本地的redis  10.10.6.19:30231
        Jedis jedis = new Jedis("10.10.6.19", 30231);
        //如果有密码则需要下面这一行
//        jedis.auth("Abc123");
        //查看服务是否运行,运行正常的话返回一个PONG，否则返回一个连接错误
        //	        System.out.println(jedis.ping());
//        for(int j = 0; j < 100; j++) {
//            String key = "init_key_" + j;
//            String value = "value_" + j;
//            jedis.set(key, value);
//        }


        for(int j = 0; j < 100; j++) {
            String key = "init_key_" + j;

            String s = jedis.get(key);
            System.out.println(s);
        }




//        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(100, 200, 100, TimeUnit.HOURS, new LinkedBlockingQueue<>(500));
//
//        for(int j = 0; j <= 100; j++) {
//               threadPoolExecutor.submit(() -> {
//                for(int i = 0; i< 100 ; i++) {
//                    String r = RandomStringUtils.random(8);
//                    String key = "redis" + System.currentTimeMillis() + "_" + r + "_" + "key";
//                    String value = System.currentTimeMillis() + "_" + r + "_" + "value";
//                    jedis.set(key, value);
//                    System.out.println(key +" ::::" + value);
//                }
//            });
//        }

        //        for(int j = 0; j < 100; j++) {
//            String key = "redis" + System.currentTimeMillis() + "_" + j + "_" + "key";
//            String value = System.currentTimeMillis() + "_" + j + "_" + "value";
//            jedis.set(key, value);
//        }


        //redis中存放大key的值
//        for(int j = 0; j < 20; j++) {
//            StringBuilder sb = new StringBuilder();
//            for(int i = 0; i < 99999; i++) {
//                sb.append("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
//            }
//            jedis.set(System.currentTimeMillis() + "_" + j, sb.toString());
//        }
        /*****************String示例*****************/

        //设置字符串数据
//        jedis.set("word" +,"helloWorld");
//        //读取字符串数据
//        System.out.println(jedis.get("word"));
//        //删除数据
//        jedis.del("word");
//
//
//        /*****************List示例*****************/
//        jedis.lpush("list","google");
//        jedis.lpush("list","aLi");
//        jedis.rpush("list","Mi");
//
//        List<String> stringList = jedis.lrange("list",0l,-1l);
//        for(String str:stringList){
//            System.out.println(str);
//        }
//
//        /*****************Hash示例*****************/
//        HashMap<String,String> map = new HashMap<>();
//        map.put("name","tom");
//        map.put("age","81");
//        jedis.hmset("man",map);
//        System.out.println(jedis.hmget("man","name"));
//        System.out.println(jedis.hgetAll("man"));
//        System.out.println("获取所有字段:"+jedis.hkeys("man"));
//        System.out.println("获取字段数量："+jedis.hlen("man"));
//        System.out.println("判断age字段是否存在:"+jedis.hexists("man","age"));
//
//        /*****************Set示例*****************/
//        jedis.sadd("set1","1");
//        jedis.sadd("set1","2");
//        jedis.sadd("set1","1");
//        jedis.sadd("set2","1");
//        jedis.sadd("set2","4");
//        System.out.println("获取集合的成员数"+jedis.scard("set1"));
//        System.out.println("获取集合中的成员"+jedis.smembers("set2"));
//        System.out.println("判断集合是否包含指定成员"+jedis.sismember("set1","2"));
//        System.out.println("获取多个集合的交集"+jedis.sinter("set1","set2"));
//        System.out.println("获取多个集合并集"+jedis.sunion("set1","set2"));
//        System.out.println("返回第一个集合与其他集合之间的差异"+jedis.sdiff("set1","set2"));
//
//        /*****************sorted Set示例*****************/
//        jedis.zadd("set1",1,"a");
//        jedis.zadd("set1",3,"b");
//        System.out.println("获取有序集合的成员数"+jedis.zcard("set1"));
//        System.out.println("获取元素对应的索引"+jedis.zrank("set1","b"));
//        System.out.println("获取有序集合指定范围的元素"+jedis.zrange("set1",0,-1));
//        System.out.println("移除集合中的一个元素或多个元素"+jedis.zrem("set1","a"));
//        jedis.zadd("set1",3,"c");
//        System.out.println("获取有序集合指定范围的元素"+jedis.zrange("set1",0,-1));
//        jedis.zadd("set1",5,"b");
//        System.out.println("获取有序集合指定范围的元素"+jedis.zrange("set1",0,-1));

    }
}
