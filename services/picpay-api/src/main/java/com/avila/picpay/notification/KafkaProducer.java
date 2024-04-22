package com.avila.picpay.notification;
import com.avila.picpay.model.Transaction;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service @AllArgsConstructor
public class KafkaProducer {
    private final KafkaTemplate<String, Transaction> template;
    public void sendTransaction(Transaction transaction) {
        String topic = "transactions";
        template.send(topic, transaction);
    }
}