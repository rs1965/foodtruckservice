package com.radient.ftsapp.utils;


import lombok.Getter;
import lombok.Setter;

public class ResponseObject<T> {

    private boolean isSuccess;
    @Setter
    @Getter
    private String message;
    @Setter
    @Getter
    private T data;

    // Constructors
    public ResponseObject(boolean isSuccess, String message, T data) {
        this.isSuccess = isSuccess;
        this.message = message;
        this.data = data;
    }

    public ResponseObject(boolean isSuccess, String message) {
        this(isSuccess, message, null);
    }

    public ResponseObject(boolean isSuccess, T data) {
        this(isSuccess, null, data);
    }

    // Getters and Setters
    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

}
