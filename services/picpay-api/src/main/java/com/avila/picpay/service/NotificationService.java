package com.avila.picpay.service;
import com.avila.picpay.exception.KafkaCommunicationException;
import com.avila.picpay.model.Transaction;
import com.avila.picpay.notification.KafkaProducer;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service @AllArgsConstructor
public class NotificationService {
    private final Logger logger = LoggerFactory.getLogger(NotificationService.class);
    private final KafkaProducer kafka;

    public void notifyTransaction(Transaction transaction){
        logger.info("Sending transaction notification to Kafka: {}", setTransactionNotificationTemplate(transaction));
        try {
            kafka.sendTransaction(transaction);
        } catch (Exception e) {
            logger.error("Error sending transaction notification to Kafka: {}", e.getMessage());
            throw new KafkaCommunicationException("Error sending transaction notification to Kafka");
        }
    }

    private @NotNull String setTransactionNotificationTemplate(@NotNull Transaction transaction){
        return "Transaction" +
                "{" +
                "id: " + transaction.id() + ", " +
                "sender: " + transaction.payer() + ", " +
                "receiver: " + transaction.payee() + ", " +
                "value: " + transaction.value() + ", " +
                "timestamp: " + transaction.createdAt() +
                "}";
    }
}