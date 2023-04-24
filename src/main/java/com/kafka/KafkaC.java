package com.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;

import java.util.Collections;
import java.util.Properties;

/**
 * @author cxk
 * 2023/1/10 14:40
 */
import org.apache.kafka.clients.consumer.*;

public class KafkaC {
    public static void main(String[] args) {
        String topicName = "my-topic";
        // Set up the consumer configuration properties
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", "my-group");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        // Create the Kafka consumer instance
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);

        // Subscribe to the topic
        consumer.subscribe(Collections.singletonList(topicName));

        // Continuously poll for new messages
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(1000);
            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("Received message with value %s from partition %d and offset %d\n", record.value(), record.partition(), record.offset());
            }
        }
    }
}