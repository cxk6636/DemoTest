package com.redis;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    static class MyRun implements Runnable {
        @Override
        public void run() {
            Jedis jedis = new Jedis("10.10.5.23", 30647);
            jedis.auth("Beyond#11");
            for(int i = 1; i <= 200; i++) {
                String random = RandomStringUtils.randomAlphabetic(11) + System.currentTimeMillis();
                jedis.set("ss_" + random, i + "vv");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

//        Jedis jedis = new Jedis("10.10.5.23", 30647);
//        jedis.auth("Beyond#11");
//        jedis.flushAll();

//        for(int i =100; i <=10000 ; i++) {
//            jedis.set("key_" + i, "value_" + i);
//        }

        for(int i = 0; i < 500; i++) {
            Thread thread = new Thread(new MyRun());
            String name = thread.getName();
            System.out.println("线程：" + name);
            thread.start();
        }

        //连接本地的redis  10.10.6.19:30231
//        for(int i = 0; i< 5; i++) {
//            Jedis jedis = new Jedis("localhost", 6379);
//            //如果有密码则需要下面这一行
////            jedis.auth("Beyond11");
//            Thread.sleep(500);
//            String ss = jedis.get("ss");
//            System.out.println("i  ......   " + i);
//        }

//        Jedis jedis = new Jedis("10.10.5.23", 31298);
//        jedis.auth("Beyond#11");
//        for(int i = 1; i <= 100; i++) {
//            jedis.set("ss_" + i, i + "vv");
//        }

//        Map<String, Map<String, Integer>> s= new HashMap<>();
//        if(!s.containsKey("127.0.0.0")) {
//            s.put("127.0.0.1", new HashMap<>());
//            s.get("127.0.0.1").put("total", 0);
//            s.get("127.0.0.1").put("active", 0);
//        }
//        s.get("127.0.0.1").put("total", s.get("127.0.0.1").get("total") + 1);
//        s.get("127.0.0.1").put("active", s.get("127.0.0.1").get("active") + 1);
    }
}
