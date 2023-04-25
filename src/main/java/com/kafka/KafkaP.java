package com.kafka;

import org.apache.kafka.clients.producer.*;
import java.util.Properties;

public class KafkaP {
    public static void main(String[] args) {
        String topicName = "test-topic";
        String key = "test-key";
        String value = "test-value";

        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<>(props);
        ProducerRecord<String, String> record = new ProducerRecord<>(topicName, key, value);
        producer.send(record);
        producer.close();
    }
}
