package com.example.Logger.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LogDTO {

    private String message;
    private String logTypeValue;
    private String createdDate;
}
