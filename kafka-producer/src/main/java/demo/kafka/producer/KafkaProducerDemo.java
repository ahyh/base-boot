package demo.kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * 通过API的方法produce message
 *
 * @author yanhuan
 */
public class KafkaProducerDemo {

    public static void main(String[] args) {

        Properties properties = new Properties();

        // 1- 设置连接的kafka集群
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "yanhuan1:9092");

        // 2- ack应答级别
        properties.put(ProducerConfig.ACKS_CONFIG, "all");

        // 3- 重试次数
        properties.put(ProducerConfig.RETRIES_CONFIG, 3);

        // 4- 设置批次大小
        properties.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
        properties.put(ProducerConfig.LINGER_MS_CONFIG, 1);

        // 5- 缓冲区大小
        properties.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);

        // 6- key-value的序列化
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");

        // 创建producer
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(properties);

        for (int i = 0; i < 10; i++) {
            kafkaProducer.send(new ProducerRecord<>("first", "yanhuan" + i));
        }

        // 关闭producer
        kafkaProducer.close();
    }

}
