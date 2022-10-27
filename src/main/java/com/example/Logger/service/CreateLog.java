package com.example.Logger.service;

import com.example.Logger.model.db.Log;
import com.example.Logger.model.db.LogType;
import com.example.Logger.model.request.CreateLogRequest;
import com.example.Logger.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CreateLog {

    private LogRepository logRepository;

    @Autowired
    public CreateLog(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    public ResponseEntity execute(CreateLogRequest request){
        LogType logType = LogType.getLogType(request.getLogTypeValue());
        Log log = new Log (request.getMessage(), logType, new Date());
        return ResponseEntity.status(HttpStatus.OK).body(logRepository.save(log));
    }

}
