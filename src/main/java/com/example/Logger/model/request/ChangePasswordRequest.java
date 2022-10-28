package com.example.Logger.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChangePasswordRequest {

    String oldPassword;
    String newPassword;

}
