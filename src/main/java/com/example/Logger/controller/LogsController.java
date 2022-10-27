package com.example.Logger.controller;

import com.example.Logger.model.db.LogType;
import com.example.Logger.model.request.CreateLogRequest;
import com.example.Logger.model.request.SearchLogRequest;
import com.example.Logger.service.CreateLog;
import com.example.Logger.service.SearchLog;
import com.example.Logger.validator.SearchLogValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/logs")
public class LogsController {

    private CreateLog createLog;
    private SearchLogValidator searchLogValidator;
    private SearchLog searchLog;

    @Autowired
    public LogsController(CreateLog createLog, SearchLogValidator searchLogValidator, SearchLog searchLog) {
        this.createLog = createLog;
        this.searchLogValidator = searchLogValidator;
        this.searchLog = searchLog;
    }

    @PostMapping("create")
    public ResponseEntity create(HttpServletRequest request, @RequestBody CreateLogRequest createLogRequest) {
        String token = request.getHeader("Authorization");
        if(token == null || token.isEmpty())
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

        LogType logType = LogType.getLogType(createLogRequest.getLogTypeValue());
        if(logType.value.equals(LogType.UNDEFINED.value))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

        if(createLogRequest.getMessage().length() > 1024)
            return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).body(createLogRequest);

        ResponseEntity response = createLog.execute(createLogRequest);
        return response;
    }

    @GetMapping("search")
    public ResponseEntity search(HttpServletRequest request, @RequestBody SearchLogRequest searchLogRequest) {
        String token = request.getHeader("Authorization");
        if(token == null || token.isEmpty())
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

        List<String> validationMessages = searchLogValidator.validate(searchLogRequest);
        if(!validationMessages.isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationMessages);

        ResponseEntity response = searchLog.execute(searchLogRequest);
        return response;
    }
}
