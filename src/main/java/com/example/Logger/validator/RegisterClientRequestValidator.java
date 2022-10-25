package com.example.Logger.validator;

import com.example.Logger.model.request.RegisterClientRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RegisterClientRequestValidator {

    private static final String EMAIL_REG = "^[a-zA-Z0-9_+&*-] + (?:\\.[a-zA-Z0-9_+&*-] + )*@(?:[a-zA-Z0-9-]+\\.) + [a-zA-Z]{2, 7}";
    public static final String PASSWORD_LETTER_REG = ".*[a-zA-Z].*";
    public static final String PASSWORD_DIGIT_REG = ".*[0-9].*";

    public List<String> validate(RegisterClientRequest request) {

        List<String> validationMessages= new ArrayList<>();

        if(request.getEmail() == null || request.getEmail().isEmpty())
            validationMessages.add("Email cannot be null");

        if(request.getUserName() == null || request.getUserName().isEmpty())
            validationMessages.add("Username cannot be null");

        if(request.getPassword() == null || request.getPassword().isEmpty())
            validationMessages.add("Password cannot be null");

        if(!request.getEmail().matches(EMAIL_REG))
            validationMessages.add("Email invalid.");

        if(request.getUserName().length() < 3)
            validationMessages.add("Username must be at least 3 character long.");

        if(request.getPassword().length() < 8 || !request.getPassword().matches(PASSWORD_LETTER_REG) || !request.getPassword().matches(PASSWORD_DIGIT_REG))
            validationMessages.add("Username must be at least 8 character long and must have at least 1 letter and 1 digit.");

        return validationMessages;
    }

    public List<String> validateUsernameAndPassword(String username, String password) {
        List<String> validationMessages = new ArrayList<>();
        if (username == null || username.isEmpty())
            validationMessages.add("Email cannot be null");

        if (password == null || password.isEmpty())
            validationMessages.add("Email cannot be null");

        if (!validationMessages.isEmpty())
            return validationMessages;

        if (username.length() < 3)
            validationMessages.add("Username must be at least 3 character long.");

        if (password.length() < 8 || !password.matches(PASSWORD_LETTER_REG) || !password.matches(PASSWORD_DIGIT_REG))
            validationMessages.add("Username must be at least 8 character long and must have at least 1 letter and 1 digit.");

        return validationMessages;
    }


}
