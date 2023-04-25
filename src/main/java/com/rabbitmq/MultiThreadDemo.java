package com.rabbitmq;

import java.io.IOException;
import java.util.Random;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;
import kotlin.random.RandomKt;

public class MultiThreadDemo {

    public static void main(String[] args) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("10.10.5.23");
        factory.setPort(31367);
        factory.setUsername("default_user_GpHhZ2JYN-Cy-VVld-h");
        factory.setPassword("_MhRLNuJ0zN0SCSQKokeIXntKRkt1nBl");
        factory.setVirtualHost("/");//rabbitmq默认虚拟机名称为“/”，虚拟机相当于一个独立的mq服务器
        factory.setHandshakeTimeout(300000000);//设置握手超时时间
        factory.setRequestedChannelMax(30000);

        Runnable task = () -> {
            try {
                while (true) {
                    String QUEUE_NAME = "xx" + System.currentTimeMillis();
                    System.out.println("开启：  " + QUEUE_NAME);
                    Connection connection = factory.newConnection();
                    Channel channel = connection.createChannel();

                    boolean durable = true;
                    channel.queueDeclare(QUEUE_NAME, durable, false, false, null);

                    String message = "Hello from thread #" + Thread.currentThread().getId();
                    channel.basicPublish("", QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
//
//                    channel.close();
//                    connection.close();
                    System.out.println("结束：  " + QUEUE_NAME);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        for (int i = 0; i < 1000; i++) {
            System.out.println("线程：" + i + " 启动");
            new Thread(task).start();
        }
    }
}