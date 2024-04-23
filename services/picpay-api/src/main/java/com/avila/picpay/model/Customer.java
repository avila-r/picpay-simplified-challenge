package com.avila.picpay.model;
import jakarta.persistence.Table;
import lombok.Getter;
import java.math.BigDecimal;

@Table(name = "customers")
public record Customer(
        Long id,
        String name,
        Long cpf,
        String email,
        String password,
        CustomerType type,
        BigDecimal balance
    ) {

    public enum CustomerType {
        COMMON, SELLER;
        CustomerType() { }
    }

    public boolean isAbleToPay(BigDecimal value) {
        return balance.compareTo(value) >= 0;
    }

    public boolean isCommonCustomer() {
        return type == CustomerType.COMMON;
    }

    public Customer debit(BigDecimal value) {
        return new Customer(id, name, cpf, email, password, type, balance.subtract(value));
    }

    public Customer credit(BigDecimal value) {
        return new Customer(id, name, cpf, email, password, type, balance.add(value));
    }
}