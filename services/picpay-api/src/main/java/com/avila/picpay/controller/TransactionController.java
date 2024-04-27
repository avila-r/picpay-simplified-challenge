package com.avila.picpay.controller;
import com.avila.picpay.exception.TransactionNotFoundException;
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
    public List<Transaction> listAll(){
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Transaction getById(@PathVariable Long id){
        return repository.findById(id)
                .orElseThrow(() -> new TransactionNotFoundException("Transaction not found"));
    }
}