package com.log.api.service;

import com.log.api.model.LogContent;
import org.apache.kafka.common.protocol.types.Field;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class KafkaSender {

    private static final Logger logger = LoggerFactory.getLogger(KafkaSender.class);

    @Autowired
    private KafkaTemplate<String, LogContent> kafkaTemplate;

    @Async
    public CompletableFuture<String> sendMessage(String topic, LogContent logContent) {
        logger.info(String.format("Outgoing Message - Producing -> %s", logContent));
        this.kafkaTemplate.send(topic, logContent);
        return CompletableFuture.completedFuture("Log message sent to Kafka");
    }

}
