package com.yh.boot.activemq.producer.test;

import com.yh.boot.activemq.producer.ActivemqProducerApplicationTests;
import com.yh.boot.activemq.producer.service.Producer;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ActiveMqProducerTest extends ActivemqProducerApplicationTests {

    @Autowired
    private Producer producer;

    @Test
    public void testSendQueue() {
        String queueName = "boot-activemq-queue";
        boolean flag = producer.send2Queue(queueName, "love story1");
        Assert.assertTrue(flag);
    }

    @Test
    public void testSendTopic() {
        String topicName = "boot-activemq-topic";
        boolean flag = producer.send2Topic(topicName, "This is a topic message1");
        Assert.assertTrue(flag);
    }
}
