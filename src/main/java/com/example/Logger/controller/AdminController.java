package com.example.Logger.controller;

import com.example.Logger.model.dto.ClientDTO;
import com.example.Logger.model.request.ChangePasswordRequest;
import com.example.Logger.service.ChangePassword;
import com.example.Logger.service.GetAllClients;
import com.example.Logger.service.GetLogsForClient;
import com.example.Logger.service.IsUserAdmin;
import com.example.Logger.validator.ChangePasswordValidator;
import com.example.Logger.validator.UsernameValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private GetAllClients getAllClients;
    private ChangePassword changePassword;
    private ChangePasswordValidator changePasswordValidator;
    private GetLogsForClient getLogsForClient;
    private UsernameValidator usernameValidator;
    private IsUserAdmin isUserAdmin;

    @Autowired
    public AdminController(GetAllClients getAllClients, ChangePassword changePassword, ChangePasswordValidator changePasswordValidator, GetLogsForClient getLogsForClient, UsernameValidator usernameValidator, IsUserAdmin isUserAdmin) {
        this.getAllClients = getAllClients;
        this.changePassword = changePassword;
        this.changePasswordValidator = changePasswordValidator;
        this.getLogsForClient = getLogsForClient;
        this.usernameValidator = usernameValidator;
        this.isUserAdmin = isUserAdmin;
    }

    @GetMapping("clients")
    public ResponseEntity getAllClients(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        boolean isAdmin = isUserAdmin.execute(token);
        if (isAdmin) {
            List<ClientDTO> clientDTOs = getAllClients.execute();
            return ResponseEntity.status(HttpStatus.OK).body(clientDTOs);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No user with this token: " + token);
        }
    }

    @GetMapping("logs/{username}")
    public ResponseEntity getLogsForClient(HttpServletRequest request, @PathVariable String username) {
        String token = request.getHeader("Authorization");
        boolean isAdmin = isUserAdmin.execute(token);
        if (isAdmin) {
            List<String> validationMessages = usernameValidator.validate(username);
            if (!validationMessages.isEmpty())
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(username);

            ResponseEntity response = getLogsForClient.execute(username);
            return response;
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

    }

    @PostMapping("password")
    public ResponseEntity changePassword(HttpServletRequest request, @RequestBody ChangePasswordRequest changePasswordRequest) {

        String token = request.getHeader("Authorization");
        boolean isAdmin = isUserAdmin.execute(token);
        if (isAdmin) {
            List<String> validationMessages = changePasswordValidator.validate(changePasswordRequest.getOldPassword(), changePasswordRequest.getNewPassword());
            if (!validationMessages.isEmpty())
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(changePasswordRequest);

            ResponseEntity response = changePassword.execute(changePasswordRequest);
            return response;
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

    }

}
