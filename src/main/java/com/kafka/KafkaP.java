package com.kafka;

import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.KafkaAdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

/**
 * @author cxk
 * 2023/1/10 14:38
 */

import java.util.Properties;
import org.apache.kafka.clients.producer.*;

public class KafkaP {
    public static void main(String[] args) {
        String topicName = "my-topic";
        String key = "key1";
        String value = "Hello, Kafka!";

        // Set up the producer configuration properties
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        // Create the Kafka producer instance
        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        // Create the message to send
        ProducerRecord<String, String> record = new ProducerRecord<>(topicName, key, value);

        // Send the message asynchronously
        producer.send(record, new Callback() {
            public void onCompletion(RecordMetadata metadata, Exception exception) {
                if (exception == null) {
                    System.out.printf("Sent message with offset %d to partition %d\n", metadata.offset(), metadata.partition());
                } else {
                    System.out.println("Error sending message: " + exception.getMessage());
                }
            }
        });

        // Close the producer
        producer.close();
    }
}
