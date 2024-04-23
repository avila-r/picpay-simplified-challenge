package com.avila.picpay.controller;
import com.avila.picpay.model.Transaction;
import com.avila.picpay.repository.TransactionRepository;
import com.avila.picpay.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @AllArgsConstructor
@RequestMapping("/transfer")
public class TransactionController {
    private final TransactionRepository repository;
    private final TransactionService service;

    @PostMapping
    public Transaction createTransaction(@RequestBody Transaction transaction){
        return service.create(transaction);
    }

    @GetMapping
    public List<Transaction> list(){
        return repository.findAll();
    }
}