package com.siddarthmishra.apachekafka;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Properties;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

public class ProducerDemo {

	private static final String KAFKA_TOPIC = "demo-01-pub-sub";
	private static final String BOOTSTRAP_SERVERS = "127.0.0.1:9092";

	public static void main(String[] args) {

		Properties config = new Properties();
		config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);

		KafkaProducer<String, String> producer = new KafkaProducer<>(config);

		// Currently, not marked @FunctionalInterface but has only one abstract method
		Callback callback = (metadata, exception) -> {
			if (exception != null) {
				exception.printStackTrace();
				return;
			}
			System.out.println("Acknowledged - " + metadata.toString());
		};

		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			String input = null;
			do {
				System.out.println("Provide the message to be published");
				input = br.readLine();
				producer.send(new ProducerRecord<String, String>(KAFKA_TOPIC, Integer.valueOf(0), null, input),
						callback);
				producer.flush();
			} while (!input.trim().equalsIgnoreCase("stop"));
		} catch (Throwable t) {
			t.printStackTrace();
		}
		producer.close();
		System.out.println("This producer is closed");
	}

}
