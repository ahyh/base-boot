package demo.kafka.consumer;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.TopicPartition;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 *
 */
public class KafkaConsumerDemo {

    public static void main(String[] args) {
        Properties properties = new Properties();

        // 1- 设置连接的kafka集群
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "yanhuan1:9092");

        // 2- 开启自动提交和自动提交的延迟, 同步提交的时候需要关掉这两个配置
//        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
//        properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, 1000);
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);

        // 3- 设置key value的反序列化器
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");

        // 4- 设置消费者组
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "group-test");

        // 5- 创建consumer
        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(properties);

        // 6- 订阅主题
        List<String> topics = new ArrayList<>();
        topics.add("first");
        kafkaConsumer.subscribe(topics);

        // 7- 拉取数据
        while (true) {
            ConsumerRecords<String, String> consumerRecords = kafkaConsumer.poll(100);
            for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {
                System.out.println(consumerRecord.key() + "==>" + consumerRecord.value());
            }
            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (Exception e){
                e.printStackTrace();
            }
            // 同步提交
            // kafkaConsumer.commitAsync();

            // 异步提交
            kafkaConsumer.commitAsync(new OffsetCommitCallback() {
                @Override
                public void onComplete(Map<TopicPartition, OffsetAndMetadata> offsets, Exception exception) {

                }
            });
        }
    }

}
