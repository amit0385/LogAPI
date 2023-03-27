package com.log.api.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "logmessage")
public class LogContent {

    private String applicationID;
    private String traceId;
    private String severity;
    private LocalDateTime timestamp;
    private String message;

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Long id;

    public LogContent() {
    }

    public LogContent(String applicationID, String traceId, String severity, LocalDateTime timestamp, String message) {
        this.applicationID = applicationID;
        this.traceId = traceId;
        this.severity = severity;
        this.timestamp = timestamp;
        this.message = message;
    }
    public LogContent(Long id,String applicationID, String traceId, String severity, LocalDateTime timestamp, String message) {
        this.id=id;
        this.applicationID = applicationID;
        this.traceId = traceId;
        this.severity = severity;
        this.timestamp = timestamp;
        this.message = message;
    }
    public String getApplicationID() {
        return applicationID;
    }

    public void setApplicationID(String applicationID) {
        this.applicationID = applicationID;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "LogContent{" +
                "applicationID='" + applicationID + '\'' +
                ", traceId='" + traceId + '\'' +
                ", severity='" + severity + '\'' +
                ", timestamp=" + timestamp +
                ", message='" + message + '\'' +
                '}';
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }
}
