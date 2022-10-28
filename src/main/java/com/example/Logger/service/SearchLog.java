package com.example.Logger.service;

import com.example.Logger.model.db.Log;
import com.example.Logger.model.db.LogType;
import com.example.Logger.model.dto.LogDTO;
import com.example.Logger.model.request.SearchLogRequest;
import com.example.Logger.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@Service
public class SearchLog {

    private LogRepository logRepository;

    @Autowired
    public SearchLog(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    public ResponseEntity   execute(SearchLogRequest searchLogRequest){
        Date dateFrom = null;
        Date dateTo = null;
        LogType logType = null;

        if(searchLogRequest.getLogTypeValue() != null && !searchLogRequest.getLogTypeValue().isEmpty()){
             logType = LogType.getLogType(searchLogRequest.getLogTypeValue());
            if(logType == LogType.UNDEFINED)
                logType = null;
        }

       if(searchLogRequest.getDateFrom() != null && !searchLogRequest.getDateFrom().isEmpty())
           dateFrom = convertStringToDate(searchLogRequest.getDateFrom());

        if(searchLogRequest.getDateTo() != null && !searchLogRequest.getDateTo().isEmpty())
            dateTo = convertStringToDate(searchLogRequest.getDateTo());

        List<Log> logs = logRepository.findByAllParams(searchLogRequest.getMessage(), logType, dateFrom, dateTo);
        List<LogDTO> logDTOS = new ArrayList<>();
        logs.forEach(log -> logDTOS.add(new LogDTO(log.getMessage(), log.getLogType().value, convertDateToString(log.getCreatedDate()))));
        return  status(HttpStatus.OK).body(logDTOS);
    }

    private Date convertStringToDate(String string){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
           return df.parse(string);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String convertDateToString(Date date) {
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            return df.format(date);
        } catch (Exception ex) {
            return "N/A";
        }
    }

}
