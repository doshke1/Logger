package com.example.Logger.validator;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UsernameValidator {

    public List<String> validate(String username){
        List<String> validationMessages = new ArrayList<>();

        if(username == null || username.isEmpty())
            validationMessages.add("Email cannot be null");

        if(username.length() < 3)
            validationMessages.add("Username must be at least 3 character long.");

        return validationMessages;
    }
}
