package com.example.Logger.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ClientDTO {
    private Long clientId;
    private String username;
    private String email;
    private Boolean isAdmin;
}
