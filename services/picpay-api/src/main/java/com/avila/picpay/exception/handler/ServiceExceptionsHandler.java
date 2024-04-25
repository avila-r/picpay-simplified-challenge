package com.avila.picpay.exception.handler;
import com.avila.picpay.exception.InvalidCustomerRegistryException;
import com.avila.picpay.exception.InvalidTransactionException;
import com.avila.picpay.exception.UnauthorizedTransactionException;
import com.avila.picpay.exception.model.ErrorResponseMessage;
import com.avila.picpay.model.Authorization;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ServiceExceptionsHandler {
    @ExceptionHandler(InvalidTransactionException.class)
    public ResponseEntity<@NotNull ErrorResponseMessage> handleInvalidTransactionException(
            @NotNull InvalidTransactionException e
    ) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Error-type", "Unsuccessful transaction");

        HttpStatus status = HttpStatus.BAD_REQUEST;

        return ResponseEntity
                .status(status)
                .headers(headers)
                .body(new ErrorResponseMessage(status.value(), e.getMessage())
                );
    }

    @ExceptionHandler(UnauthorizedTransactionException.class)
    public ResponseEntity<@NotNull Authorization> handleUnauthorizedTransactionException(
            @NotNull UnauthorizedTransactionException e
    ) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Error-type", "Unauthorized transaction");

        HttpStatus status = HttpStatus.UNAUTHORIZED;

        return ResponseEntity
                .status(status)
                .headers(headers)
                .body(new Authorization(e.getMessage())
                );
    }

    @ExceptionHandler(InvalidCustomerRegistryException.class)
    public ResponseEntity<@NotNull ErrorResponseMessage> handleInvalidCustomerRegistryException(
            @NotNull InvalidCustomerRegistryException e
    ) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Error-type", "Invalid customer registry");

        HttpStatus status = HttpStatus.BAD_REQUEST;

        return ResponseEntity
                .status(status)
                .headers(headers)
                .body(new ErrorResponseMessage(status.value(), e.getMessage())
                );
    }
}