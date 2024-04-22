package com.avila.picpay.service;
import com.avila.picpay.model.Authorization;
import com.avila.picpay.model.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import java.util.Objects;

@Service
public class AuthorizationService {
    private final Logger logger = LoggerFactory.getLogger(AuthorizationService.class);
    private final RestClient client;

    public AuthorizationService(RestClient.Builder client) {
        this.client = client
            .baseUrl("https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc")
            .build();
    }

    public void authorize(Transaction transaction){
        logger.info("Authorizing transaction: {}", transaction);
        ResponseEntity<Authorization> authorization = client.get()
            .retrieve().toEntity(Authorization.class);
        if ((authorization.getStatusCode().isError()) && !(Objects.requireNonNull(authorization.getBody()).isAuthorized())) {
            transaction.refuse();
        }
    }
}