package com.avila.picpay.repository;
import com.avila.picpay.model.Transaction;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends ListCrudRepository<Transaction, Long>{ }