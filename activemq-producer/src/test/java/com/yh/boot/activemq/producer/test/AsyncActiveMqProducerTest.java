package com.yh.boot.activemq.producer.test;

import com.yh.boot.activemq.producer.ActivemqProducerApplicationTests;
import com.yh.boot.activemq.producer.service.AsyncProducer;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class AsyncActiveMqProducerTest extends ActivemqProducerApplicationTests {

    private static final Logger logger = LoggerFactory.getLogger(AsyncActiveMqProducerTest.class);

    @Autowired
    private AsyncProducer asyncProducer;

    @Test
    public void testAsyncSend() throws Exception {
        boolean flag = asyncProducer.asyncSendQueue("async-queue", "This is a async message");
        Assert.assertTrue(flag);
    }

    @Test
    public void testAsyncSendTopic() throws Exception {
        boolean flag = asyncProducer.asyncSendTopic("async-topic", "This is a async message");
        Assert.assertTrue(flag);
    }
}
