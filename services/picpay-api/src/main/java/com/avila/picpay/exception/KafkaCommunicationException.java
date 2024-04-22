package com.avila.picpay.exception;

public class KafkaCommunicationException extends RuntimeException {
    public KafkaCommunicationException(String message) {
        super(message);
    }
}