package com.avila.picpay.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import java.math.BigDecimal;
import java.util.Objects;

@Table(name = "customers")
public record Customer(
        @Id Long id,
        String name,
        Long cpf,
        String email,
        String password,
        @Column("customer_type") String type,
        BigDecimal balance
    ) {

    public boolean isAbleToPay(BigDecimal value) {
        return balance.compareTo(value) >= 0;
    }

    public boolean isCommonCustomer() {
        return Objects.equals(type, "COMMON");
    }

    public boolean isSellerCustomer() {
        return Objects.equals(type, "SELLER");
    }

    public Customer debit(BigDecimal value) {
        return new Customer(id, name, cpf, email, password, type, balance.subtract(value));
    }

    public Customer credit(BigDecimal value) {
        return new Customer(id, name, cpf, email, password, type, balance.add(value));
    }
}