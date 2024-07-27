package com.radient.ftsapp.config;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ApiResponsesConfig {
    private boolean isSuccess;
    private String message;

    // Constructors
    public ApiResponsesConfig(boolean isSuccess, String message) {
        this.isSuccess = isSuccess;
        this.message = message;
    }

    // Getters and Setters
//    public boolean isSuccess() {
//        return isSuccess;
//    }
//
//    public void setSuccess(boolean success) {
//        isSuccess = success;
//    }
//
//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;

}
