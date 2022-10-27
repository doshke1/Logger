package com.example.Logger.validator;

import com.example.Logger.model.db.LogType;
import com.example.Logger.model.request.CreateLogRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CreateLogValidator {

    public List<String> validate(CreateLogRequest request) {
        List<String> validationMessages = new ArrayList<>();

        LogType logType = LogType.getLogType(request.getLogTypeValue());
        if(logType.value.equals(LogType.UNDEFINED.value))
            validationMessages.add("Log type undefined.");

        if(request.getMessage().length() > 1024)
            validationMessages.add("Messages is to big.");

        return validationMessages;
    }
}
