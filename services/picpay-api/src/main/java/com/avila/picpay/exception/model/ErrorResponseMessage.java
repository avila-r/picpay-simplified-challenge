package com.avila.picpay.exception.model;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor @Getter
public class ErrorResponseMessage{
    private int status;
    private String message;
}