package com.HaEventApi.EventApi.util;

public class LoginInvalidException extends RuntimeException {
    public LoginInvalidException(String message){
        super(message);
    }
}
