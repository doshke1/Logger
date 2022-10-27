package com.example.Logger.model.db;

public enum LogType {
    ERROR ("error"),
    WARNING ("warning"),
    INFO ("info"),
    UNDEFINED("undefined");

    public final String value;

    LogType(String value){
        this.value = value;
    }

    public static LogType getLogType(String value){
        for (LogType log:values()) {
            if(log.value.equals(value)) return log;
        }
        return UNDEFINED;
    }
}
