package com.avila.picpay.model;
import com.avila.picpay.exception.UnauthorizedTransactionException;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
public record Transaction(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id,
        Long payer,
        Long payee,
        BigDecimal value,
        @CreatedDate LocalDateTime createdAt) {

    public Transaction {
        value = value.setScale(2);
    }

    public void refuse() {
        throw new UnauthorizedTransactionException("Transaction refused by authorization service");
    }
}