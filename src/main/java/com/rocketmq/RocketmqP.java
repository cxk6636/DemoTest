package com.rocketmq;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;


/**
 * @author cxk
 * 2023/1/10 14:23
 */
public class RocketmqP {
    public static void main(String[] args) {
        try{
            DefaultMQProducer producer = new DefaultMQProducer("groupb");
            producer.setNamesrvAddr("10.10.45.114:32716");//MQ服务器地址
//            producer.setVipChannelEnabled(false);
            producer.setSendMsgTimeout(10000);
            producer.start();
            for (int i = 0; i < 100; i++) {
                long time = System.currentTimeMillis();
                String topic = "cxk1001";
                String tag = "tag";
                String key = "key_" + time;
                String message = "message_" + time;
                Message msg = new Message(topic, tag ,key, message.getBytes(RemotingHelper.DEFAULT_CHARSET));
                SendResult sendResult = producer.send(msg);
                System.out.println(sendResult.toString());
            }
            producer.shutdown();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
