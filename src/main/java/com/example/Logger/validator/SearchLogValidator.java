package com.example.Logger.validator;

import com.example.Logger.model.db.LogType;
import com.example.Logger.model.request.SearchLogRequest;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class SearchLogValidator {

    public List<String> validate(SearchLogRequest searchLogRequest){

        List<String> validationMessages = new ArrayList<>();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        if(searchLogRequest.getDateFrom() != null && !searchLogRequest.getDateFrom().isEmpty()){
            try{
                Date validFrom = df.parse(searchLogRequest.getDateFrom());
            } catch (ParseException ex) {
                validationMessages.add("Date valid from is invalid.");
            }
        }

        if(searchLogRequest.getDateFrom() != null && !searchLogRequest.getDateFrom().isEmpty()) {
            try {
                Date validTo = df.parse(searchLogRequest.getDateTo());
            } catch (ParseException ex) {
                validationMessages.add("Date valid from is invalid.");
            }
        }

        LogType logType = LogType.getLogType(searchLogRequest.getLogTypeValue());
        if(logType.value.equals(LogType.UNDEFINED.value))
            validationMessages.add("Log type is not supported.");

        return validationMessages;
    }
}
