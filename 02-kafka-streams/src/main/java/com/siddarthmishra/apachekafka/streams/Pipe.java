/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.siddarthmishra.apachekafka.streams;

import java.util.Properties;
import java.util.concurrent.CountDownLatch;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;

import com.siddarthmishra.apachekafka.streams.constant.CommonConstants;

/**
 * In this example, we implement a simple Pipe program using the high-level
 * Streams DSL that reads from a source topic "streams-plaintext-input", where
 * the values of messages represent lines of text, and writes the messages as-is
 * into a sink topic "streams-pipe-output".
 */
public class Pipe {

	public static void main(String[] args) {
		Properties props = new Properties();
		props.put(StreamsConfig.APPLICATION_ID_CONFIG, "streams-pipe");
		props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, CommonConstants.BOOTSTRAP_SERVERS_CONFIG);
		props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
		props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());

		final StreamsBuilder builder = new StreamsBuilder();

		builder.stream(CommonConstants.TOPIC_STREAMS_PIPE_INPUT).to(CommonConstants.TOPIC_STREAMS_PIPE_OUTPUT);

		final Topology topology = builder.build();
		final KafkaStreams streams = new KafkaStreams(topology, props);
		final CountDownLatch latch = new CountDownLatch(1);

		Runnable runnable = () -> {
			System.out.println(Thread.currentThread().getName());
			streams.close();
			latch.countDown();
		};
		// attach shutdown handler to catch control-c
		Runtime.getRuntime().addShutdownHook(new Thread(runnable, "streams-shutdown-hook"));

		try {
			streams.start();
			latch.await();
		} catch (Throwable e) {
			System.exit(1);
		}
		System.exit(0);
	}
}
