package com.example.Logger.validator;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ChangePasswordValidator {

    public List<String> validate(String oldPassword, String newPassword){
        List<String> validationMessages = new ArrayList<>();

        if(oldPassword == null || oldPassword.isEmpty())
            validationMessages.add("Old password cannot be null");

        if(newPassword == null || newPassword.isEmpty())
            validationMessages.add("New password cannot be null");

        if (!validationMessages.isEmpty())
            return validationMessages;

        if(oldPassword.length() < 8 || !oldPassword.matches(RegisterClientRequestValidator.PASSWORD_LETTER_REG) || !oldPassword.matches(RegisterClientRequestValidator.PASSWORD_DIGIT_REG))
            validationMessages.add("Old password must be at least 8 character long and must have at least 1 letter and 1 digit.");

        if(newPassword.length() < 8 || !newPassword.matches(RegisterClientRequestValidator.PASSWORD_LETTER_REG) || !newPassword.matches(RegisterClientRequestValidator.PASSWORD_DIGIT_REG))
            validationMessages.add("New password must be at least 8 character long and must have at least 1 letter and 1 digit.");

        return validationMessages;
    }
}
