package com.avila.picpay.model;

public record Authorization(String message) {
    public boolean isAuthorized() {
        return message.equals("Autorizado");
    }
}