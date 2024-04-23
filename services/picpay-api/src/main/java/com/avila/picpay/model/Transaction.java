package com.avila.picpay.model;
import com.avila.picpay.exception.UnauthorizedTransactionException;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Table(name = "transactions")
public record Transaction(
        @Id Long id,
        Long payer,
        Long payee,
        BigDecimal value,
        @Column("created_at") @CreatedDate LocalDateTime createdAt) {

    public void refuse() {
        throw new UnauthorizedTransactionException("Transaction refused by authorization service");
    }

    @Override
    public String toString(){
        return "{payer: " + this.payer + ", payee: " + this.payee + ", value: " + this.value + "}";
    }
}