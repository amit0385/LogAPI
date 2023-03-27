package com.log.api.controller;


import com.log.api.model.LogContent;
import com.log.api.service.DBSender;
import com.log.api.service.KafkaSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;


/**
    Receive the call with Log message from external legacy system via rest call
 */
@RestController
@RequestMapping(value = "/v1/savelog")
public class LogController {
    @Value("${kafka.topic}")
    private String topic;

    private final String destinationKafka="kafka";
    private final String destinationFile="file";
    private final String destinationDb="db";
    private static final Logger LOGGER = LoggerFactory.getLogger(LogController.class);
    @Autowired
    private KafkaSender kafkaSender;

    @Autowired
    private DBSender dbSender;

    @PostMapping(value = "/{destination}", consumes={"application/json"}, produces = {"application/json"} )
    @Async
    public CompletableFuture<String> sendMessage(@RequestBody LogContent logContent, @PathVariable("destination") String destination){

        // validation of request body is a TO-DO part
        // focusing only on saving a request data to different log destination
        // at the moment destination supported to kafka, file and database
        if (destination.equals(destinationKafka)) {
            return this.kafkaSender.sendMessage(topic, logContent);

        }
        /*
        We can use 'appender by' using logback.xml that will provide additional
        content like timestamp, level etc. Please note our incoming request also have
        similar details. These  details are wrapped and can be seen as message section
         */
        if (destination.equals(destinationFile)) {
            // message section - Request payload {}
            LOGGER.info("Request payload {}", logContent);
            // above statement will log the data to console as well to log file with the help of appender with other logged data
            return CompletableFuture.completedFuture("Log message saved to file");
        }

        if (destination.equals(destinationDb)) {
            // Save log content to database
            // here I have used in-memory database - H2
            dbSender.saveLogContentDB(logContent);
            return CompletableFuture.completedFuture("Log message saved to database");
        }
        if (!destination.equals(destinationFile)&&!destination.equals(destinationKafka)&&!destination.equals(destinationDb))
            return CompletableFuture.completedFuture("Please check the request call path, At the moment only kafka, file and database are support. Please have a look on README.md .");
        return null;
    }
}
