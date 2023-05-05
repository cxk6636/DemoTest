package com.rabbitmq;
import java.io.IOException;
import java.util.concurrent.TimeoutException;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
/**
 * @author cxk
 * 2023/1/10 14:24
 */

public class RabbitmqP {
    //队列名称
    private static final String QUEUE = "rabbitmq_queue_name";
    //队列名称
    private static final String MESSAGE = "rabbitmq_message_";

    public static void main(String[] args) throws Exception {
        Connection connection = null;
        Channel channel = null;
        ConnectionFactory factory = new ConnectionFactory();

        factory.setHost("10.10.5.23");
        factory.setPort(31199);
        factory.setUsername("default_user_d1FapJzk3RfKabLG07m");
        factory.setPassword("kZL99oIPDRQUXJXrQ5-7-cgBwNuPoa9I");

        factory.setVirtualHost("/");//rabbitmq默认虚拟机名称为“/”，虚拟机相当于一个独立的mq服务器
        factory.setHandshakeTimeout(300000000);//设置握手超时时间
        factory.setRequestedChannelMax(30000);
        connection  = factory.newConnection();
        channel = connection.createChannel();

        int i = 0;
        while(true) {
            i++;
            System.out.println("消息：" + i);
            /**
             * 声明队列，如果Rabbit中没有此队列将自动创建
             * param1:队列名称
             * param2:是否持久化
             * param3:队列是否独占此连接
             * param4:队列不再使用时是否自动删除此队列
             * param5:队列参数
             //	             */
            channel.queueDeclare(QUEUE, true, false, false, null);
            /**
             * 消息发布方法
             * param1：Exchange的名称，如果没有指定，则使用Default Exchange
             * param2:routingKey,消息的路由Key，是用于Exchange（交换机）将消息转发到指定的消息队列
             * param3:消息包含的属性
             * param4：消息体
             */
            /**
             * 这里没有指定交换机，消息将发送给默认交换机，每个队列也会绑定那个默认的交换机，但是不能显示绑定或解除绑定
             *　默认的交换机，routingKey等于队列名称
             */
            channel.basicPublish("", QUEUE, null, (MESSAGE + i + "_" + System.currentTimeMillis()).getBytes());
        }
    }
}
