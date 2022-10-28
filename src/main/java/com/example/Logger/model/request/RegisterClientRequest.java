package com.example.Logger.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegisterClientRequest {

    private String username;
    private String password;
    private String email;
    private Boolean isAdmin;

}
