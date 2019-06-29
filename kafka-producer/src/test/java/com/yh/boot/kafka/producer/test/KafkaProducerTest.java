package com.yh.boot.kafka.producer.test;

import com.yh.boot.kafka.producer.KafkaProducerApplicationTests;
import com.yh.boot.kafka.producer.service.Producer;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class KafkaProducerTest extends KafkaProducerApplicationTests {

    private static final Logger logger = LoggerFactory.getLogger(KafkaProducerTest.class);

    @Autowired
    private Producer producer;

    @Test
    public void testSend() throws Exception {
        boolean flag = producer.send("test-topic", "First Kafka Message");
        logger.info("Send message");
        Assert.assertTrue(flag);
    }
}
