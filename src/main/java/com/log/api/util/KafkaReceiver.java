package com.log.api.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

/**
    Consuming a kafka message is NOT the requirement. The main objective is to use in Test scenario where we can test that sender sent the message and kafka
    consumed it
 */
@Component
public class KafkaReceiver {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaReceiver.class);

    private CountDownLatch latch = new CountDownLatch(1);
    private String payload;

    @KafkaListener(topics = "${kafka.topic}", groupId = "group_id")
    public void receive(String logContent) {
        LOGGER.info("received payload='{}'", logContent);
        payload = logContent;
        latch.countDown();
    }

    public void resetLatch() {
        latch = new CountDownLatch(1);
    }
    public CountDownLatch getLatch() {
        return latch;
    }
    public String getPayload() {
        return payload;
    }
}
