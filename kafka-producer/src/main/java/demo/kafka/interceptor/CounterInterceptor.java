package demo.kafka.interceptor;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 统计成功和失败的拦截器
 *
 * @author yanhuan
 */
public class CounterInterceptor implements ProducerInterceptor<String, String> {

    private static AtomicInteger SUCCESS_NUM = new AtomicInteger(0);
    private static AtomicInteger FAIL_NUM = new AtomicInteger(0);

    @Override
    public ProducerRecord<String, String> onSend(ProducerRecord<String, String> record) {
        return record;
    }

    @Override
    public void onAcknowledgement(RecordMetadata metadata, Exception exception) {
        if (metadata != null) {
            SUCCESS_NUM.getAndIncrement();
        } else {
            FAIL_NUM.getAndIncrement();
        }
    }

    @Override
    public void close() {
        System.out.println("Success Num:" + SUCCESS_NUM.get());
        System.out.println("Failed Num:" + FAIL_NUM.get());
    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
