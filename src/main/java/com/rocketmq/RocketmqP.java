package com.rocketmq;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * @author cxk
 * 2023/1/10 14:23
 */
@Slf4j
public class RocketmqP {
    public static void main(String[] args) {
        try{
            DefaultMQProducer producer = new DefaultMQProducer("groupb");
            producer.setNamesrvAddr("10.10.5.23:31227");//MQ服务器地址
//            producer.setVipChannelEnabled(false);
            producer.setSendMsgTimeout(10000);
            producer.start();
            while (true) {
                try {
//                    Thread.sleep(1000);
//            for (int i = 0; i < 100; i++) {
                    long time = System.currentTimeMillis();
                    DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    String nowTime = LocalDateTime.now().format(format);
                    String topic = "cxk1001";
                    String tag = "tag";
                    String key = "key_" + time;
                    String message = "message_" + time;
                    Message msg = new Message(topic, tag ,key, message.getBytes(RemotingHelper.DEFAULT_CHARSET));
                    SendResult sendResult = producer.send(msg);
                    System.out.println(sendResult.getSendStatus() + "::::" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                } catch (Exception e) {
                    System.out.println("异常了，等待中................");
                }
            }
//            producer.shutdown();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
