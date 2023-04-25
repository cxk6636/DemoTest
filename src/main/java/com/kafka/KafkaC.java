package com.kafka;

import org.apache.kafka.clients.consumer.*;
import java.util.Arrays;
import java.util.Properties;

public class KafkaC {
    public static void main(String[] args) {
        String topicName = "test-topic";
        String groupName = "test-group";

        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", groupName);
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("key.deserializer",
                "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer",
                "org.apache.kafka.common.serialization.StringDeserializer");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
        consumer.subscribe(Arrays.asList(topicName));

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
                System.out.println("topic = " + record.topic() + ", partition = " + record.partition()
                        + ", offset = " + record.offset() + ", key = " + record.key() + ", value = " + record.value());
            }
            // 手动提交偏移量
            consumer.commitSync();
        }
    }
}