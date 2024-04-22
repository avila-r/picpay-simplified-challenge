package com.avila.picpay.notification;
import com.avila.picpay.exception.NotificationUnstableServiceException;
import com.avila.picpay.model.Notification;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import java.util.Objects;

@Service
public class KafkaConsumer {
    private final RestClient client;

    public KafkaConsumer(RestClient.Builder client) {
        this.client = client
                .baseUrl("https://run.mocky.io/v3/54dc2cf1-3add-45b5-b5a9-6bf7e7f1f4a6").build();
    }

    @KafkaListener(topics = "transactions", groupId = "picpay-api")
    public void consume(){
        ResponseEntity<Notification> notification = client.get()
                .retrieve().toEntity(Notification.class);
        if ((notification.getStatusCode().isError()) || !(Objects.requireNonNull(notification.getBody()).message())) {
            throw new NotificationUnstableServiceException("Notification service is unstable.");
        }
    }
}