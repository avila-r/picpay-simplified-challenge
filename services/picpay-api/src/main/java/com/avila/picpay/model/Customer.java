package com.avila.picpay.model;
import jakarta.persistence.Table;
import lombok.Getter;
import java.math.BigDecimal;

@Getter
@Table(name = "customers")
public record Customer(
        Long id,
        String name,
        Long cpf,
        String email,
        String password,
        int type,
        BigDecimal balance
    ) {

    public enum CustomerType {
        COMMON(1), SELLER(2);
        private final int value;
        CustomerType(int value) {
            this.value = value;
        }
    }

    public Customer debit(BigDecimal value) {
        return new Customer(id, name, cpf, email, password, type, balance.subtract(value));
    }

    public Customer credit(BigDecimal value) {
        return new Customer(id, name, cpf, email, password, type, balance.add(value));
    }
}