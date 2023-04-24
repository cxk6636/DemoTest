package com.rabbitmq;

import java.io.IOException;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
/**
 * @author cxk
 * 2023/1/10 14:24
 */

public class RabbitmqC {
    private static final String QUEUE = "rabbitmq_queue_name";
    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        //设置MabbitMQ所在服务器的ip和端口
        factory.setHost("10.10.5.26");
        factory.setPort(31652);
        factory.setUsername("default_user_hQLXZzPi-AeFCShHPTV");
        factory.setPassword("vs39c4QfHy8Wd4XIqqHmdwSVUDVqTzSo");

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        //定义消费方法
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            /**
             * 消费者接收消息调用此方法
             * @param consumerTag 消费者的标签，在channel.basicConsume()去指定
             * @param envelope 消息包的内容，可从中获取消息id，消息routingkey，交换机，消息和重传标志(收到消息失败后是否需要重新发送)
             * @param properties
             * @param body
             * @throws IOException
             */
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                //交换机
                String exchange = envelope.getExchange();
                //路由key
                String routingKey = envelope.getRoutingKey();
                //消息id
                long deliveryTag = envelope.getDeliveryTag();
                //消息内容
                String msg = new String(body,"utf-8");
                System.out.println("receive message: " + msg + ",exchange:" + exchange + ",routingKey:" + routingKey+ ", deliveryTag:"+deliveryTag);
                channel.basicAck(envelope.getDeliveryTag(),false);
                try
                {
                    Thread.sleep(1);
                } catch (InterruptedException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        };

        //声明队列
        channel.queueDeclare(QUEUE, true, false, false, null);
        /**
         * 监听队列String queue, boolean autoAck,Consumer callback
         * 参数明细
         * 1、队列名称
         * 2、是否自动回复，设置为true为表示消息接收到自动向mq回复接收到了，mq接收到回复会删除消息，设置为false则需要手动回复
         * 3、消费消息的方法，消费者接收到消息后调用此方法
         */
        channel.basicConsume(QUEUE, false, consumer);

    }
}
