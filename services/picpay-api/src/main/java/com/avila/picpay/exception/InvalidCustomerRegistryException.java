package com.avila.picpay.exception;

public class InvalidCustomerRegistryException extends RuntimeException {
    public InvalidCustomerRegistryException(String message) {
        super(message);
    }
}