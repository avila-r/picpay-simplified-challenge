package com.avila.picpay.exception.handler;
import com.avila.picpay.exception.InvalidTransactionException;
import com.avila.picpay.exception.model.ErrorResponseMessage;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ServiceExceptionsHandler {
    @ExceptionHandler(InvalidTransactionException.class)
    public ResponseEntity<@NotNull ErrorResponseMessage> handleInvalidTransactionException(InvalidTransactionException e) {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Error-type", "Unsuccessful transaction");

        HttpStatus status = HttpStatus.BAD_REQUEST;

        return ResponseEntity
                .status(status)
                .headers(headers)
                .body(new ErrorResponseMessage(status.value(), e.getMessage())
                );
    }
}