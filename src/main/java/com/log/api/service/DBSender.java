package com.log.api.service;

import com.log.api.model.LogContent;
import com.log.api.repository.DBSenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class DBSender {

    @Autowired
    private DBSenderRepository dbSenderRepository;
    @Async
    public CompletableFuture<String> saveLogContentDB(LogContent logContent) {
        dbSenderRepository.save(logContent);
        return CompletableFuture.completedFuture("Log message saved to database");
    }
}
