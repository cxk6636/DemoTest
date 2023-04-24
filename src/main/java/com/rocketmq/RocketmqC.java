package com.rocketmq;

import java.util.List;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

/**
 * @author cxk
 * 2023/1/10 14:24
 */

public class RocketmqC {
    public static void main(String[] args) {
        rocketMQConsumer();
    }
    public static void rocketMQConsumer() {
        try {
            System.out.println("rocketMQConsumer  开始------");
            // 消费目标
            // 声明一个消费者consumer，需要传入一个组
            DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("groupb");
            // 设置集群的NameServer地址，多个地址之间以分号分隔
            consumer.setNamesrvAddr("10.10.45.114:32716");
            // 设置consumer的消费策略
            consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
            // 集群模式消费，广播消费不会重试
            consumer.setMessageModel(MessageModel.CLUSTERING);
            // 设置最大重试次数，默认是16次
            consumer.setMaxReconsumeTimes(5);
            // 设置consumer所订阅的Topic和Tag，*代表全部的Tag
            consumer.subscribe("cxk1001", "*");
            // Listener，主要进行消息的逻辑处理,监听topic，如果有消息就会立即去消费
            consumer.registerMessageListener(new MessageListenerConcurrently() {
                @Override
                public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                    for (MessageExt msg : msgs) {
                        System.out.println(new String(msg.getBody()));
                    }
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
            });
            // 调用start()方法启动consumer
            consumer.start();
            System.out.println("消费者启动成功。。。");
            System.out.println("rocketMQConsumer 结束------");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("消息消费操作失败--" + e.getMessage());
        }
    }
}
