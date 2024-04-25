package com.avila.picpay.exception;

public class InvalidCustomerTypeException extends RuntimeException {
    public InvalidCustomerTypeException(String message) {
        super(message);
    }
}