package com.siddarthmishra.apachekafka;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

public class ConsumerDemo {

	private static final String KAFKA_TOPIC = "demo-01-pub-sub";
	private static final String BOOTSTRAP_SERVERS = "0.0.0.0:9092";
	private static final String CONSUME_FORMAT = "Consumed - Value=%s ; Topic=%s ; Partition=%s ; Offset=%s ; Leader=%s ; %s=%s ; Key=%s";

	public static void main(String[] args) {
		Properties config = new Properties();
		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
		config.put(ConsumerConfig.GROUP_ID_CONFIG, "ConsumerDemo-1");

		KafkaConsumer<String, String> consumer = new KafkaConsumer<>(config);

		consumer.subscribe(Arrays.asList(KAFKA_TOPIC));

		boolean doStop = false;
		do {
			ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
			for (ConsumerRecord<String, String> record : records) {
				String consume_message = String.format(CONSUME_FORMAT, record.value(), record.topic(),
						record.partition(), record.offset(), record.leaderEpoch().orElse(null), record.timestampType(),
						record.timestamp(), record.key());
				System.out.println(consume_message);
				if (!doStop) {
					doStop = record.value().trim().equalsIgnoreCase("stop");
				}
				consumer.commitAsync();
			}
		} while (!doStop);
		consumer.unsubscribe();
		consumer.close();
		System.out.println("Consumer is unscribed and closed");
	}

}
