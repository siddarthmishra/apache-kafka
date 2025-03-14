# [KAFKA STREAMS](https://kafka.apache.org/documentation/streams/)

### Tour of the Streams API

1. [Introduction to Kafka Streams](https://www.youtube.com/watch?v=ni3XPsYC5cQ)
2. [Creating a Streams Application](https://www.youtube.com/watch?v=9ZhsnXM2OVM)
3. [Transforming Data Pt. 1](https://www.youtube.com/watch?v=SYmqwvE8umM)
4. [Transforming Data Pt. 2](https://www.youtube.com/watch?v=Vk55Kl9x_Fw)

### Sample Programs for Streams

#### 1. [Pipe](https://kafka.apache.org/39/documentation/streams/tutorial#tutorial_code_pipe)
1. Create `streams-pipe-input` topic
```
./bin/kafka-topics.sh --create --topic streams-pipe-input --bootstrap-server localhost:9092
```
2. Create `streams-pipe-output` topic
```
./bin/kafka-topics.sh --create --topic streams-pipe-output --bootstrap-server localhost:9092
```
3. Run the `Pipe.java` in the IDE
4. Connect to `streams-pipe-input` topic via console. This will act as a producer (This can also be done via Java code).
```
./bin/kafka-console-producer.sh --topic streams-pipe-input --bootstrap-server localhost:9092
```
5. Connect to `streams-pipe-output` topic via console. This will act as a consumer (This can also be done via Java code).
```
./bin/kafka-console-consumer.sh --topic streams-pipe-output --bootstrap-server localhost:9092
```

#### 2. [LineSplit](https://kafka.apache.org/39/documentation/streams/tutorial#tutorial_code_linesplit)
1. Create `streams-linesplit-input` topic
```
./bin/kafka-topics.sh --create --topic streams-linesplit-input --bootstrap-server localhost:9092
```
2. Create `streams-linesplit-output` topic
```
./bin/kafka-topics.sh --create --topic streams-linesplit-output --bootstrap-server localhost:9092
```
3. Run the `LineSplit.java` in the IDE
4. Connect to `streams-linesplit-input` topic via console. This will act as a producer (This can also be done via Java code).
```
./bin/kafka-console-producer.sh --topic streams-linesplit-input --bootstrap-server localhost:9092
```
5. Connect to `streams-linesplit-output` topic via console. This will act as a consumer (This can also be done via Java code).
```
./bin/kafka-console-consumer.sh --topic streams-linesplit-output --bootstrap-server localhost:9092
```

#### 3. [WordCount](https://kafka.apache.org/39/documentation/streams/tutorial#tutorial_code_wordcount)
1. Create `streams-wordcount-input` topic
```
./bin/kafka-topics.sh --create --topic streams-wordcount-input --bootstrap-server localhost:9092
```
2. Create `streams-wordcount-output` topic
```
./bin/kafka-topics.sh --create --topic streams-wordcount-output --bootstrap-server localhost:9092
```
3. Run the `WordCount.java` in the IDE
4. Connect to `streams-wordcount-input` topic via console. This will act as a producer (This can also be done via Java code).
```
./bin/kafka-console-producer.sh --topic streams-wordcount-input --bootstrap-server localhost:9092
```
5. Connect to `streams-wordcount-output` topic via console. This will act as a consumer (This can also be done via Java code).
```
./bin/kafka-console-consumer.sh --topic streams-wordcount-output --from-beginning --property print.key=true --property print.value=true --property key.deserializer=org.apache.kafka.common.serialization.StringDeserializer --property value.deserializer=org.apache.kafka.common.serialization.LongDeserializer --bootstrap-server localhost:9092
```
