package com.avila.picpay.controller;
import com.avila.picpay.model.Transaction;
import com.avila.picpay.repository.TransactionRepository;
import com.avila.picpay.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController @AllArgsConstructor
@RequestMapping("/transaction")
public class TransactionController {
    private final TransactionRepository repository;
    private final TransactionService service;

    @PostMapping
    public Transaction createTransaction(Transaction transaction){
        return service.create(transaction);
    }

    @GetMapping
    public List<Transaction> list(){
        return repository.findAll();
    }
}