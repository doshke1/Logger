package com.example.Logger.model.db;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Log {

     public Log(String message, LogType logType, Date createdDate, Client client) {
        this.message = message;
        this.logType = logType;
        this.createdDate = createdDate;
        this.client = client;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long logId;

    private String message;

    @Enumerated(EnumType.STRING)
    private LogType logType;

    @Temporal(TemporalType.DATE)
    private Date createdDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private Client client;

    @Override
    public String toString() {
        return "Log{" +
                "logId=" + logId +
                ", message='" + message + '\'' +
                ", logType=" + logType +
                ", createdDate=" + createdDate +
                '}';
    }
}
