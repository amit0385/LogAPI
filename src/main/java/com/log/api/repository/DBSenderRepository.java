package com.log.api.repository;

import com.log.api.model.LogContent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DBSenderRepository extends JpaRepository<LogContent, Long> {
}
