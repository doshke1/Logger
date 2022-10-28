package com.example.Logger.service;

import com.example.Logger.model.db.Client;
import com.example.Logger.model.db.Log;
import com.example.Logger.model.db.LogType;
import com.example.Logger.model.dto.LogDTO;
import com.example.Logger.model.request.CreateLogRequest;
import com.example.Logger.repository.ClientRepository;
import com.example.Logger.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Service
public class CreateLog {

    private LogRepository logRepository;
    private ClientRepository clientRepository;

    @Autowired
    public CreateLog(LogRepository logRepository, ClientRepository clientRepository) {
        this.logRepository = logRepository;
        this.clientRepository = clientRepository;
    }

    public ResponseEntity execute(CreateLogRequest request, String username){
        Optional<Client> optional = clientRepository.findClientByUsername(username);
        if(!optional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client with username: " +username+ " not found");

        LogType logType = LogType.getLogType(request.getLogTypeValue());
        Log log = new Log(request.getMessage(), logType, new Date(), optional.get());
        log = logRepository.save(log);
        LogDTO logDTO = new LogDTO(log.getMessage(), log.getLogType().value, convertDateToString(log.getCreatedDate()));
        return ResponseEntity.status(HttpStatus.OK).body(logDTO);
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
