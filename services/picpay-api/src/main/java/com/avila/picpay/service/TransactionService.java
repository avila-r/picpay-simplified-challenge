package com.avila.picpay.service;
import com.avila.picpay.exception.InvalidTransactionException;
import com.avila.picpay.model.Customer;
import com.avila.picpay.model.Transaction;
import com.avila.picpay.repository.CustomerRepository;
import com.avila.picpay.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service @AllArgsConstructor
public class TransactionService {
    private final Logger logger = LoggerFactory.getLogger(TransactionService.class);
    private final CustomerRepository customerRepository;
    private final TransactionRepository transactionRepository;
    private final AuthorizationService authorizationService;
    private final NotificationService notificationService;

    @Transactional
    public Transaction create(Transaction transaction){
        validate(transaction);
        Transaction createdTransaction = transactionRepository.save(transaction);
        logger.info("Transaction created {}", transaction);
        makeTransaction(transaction);
        authorizationService.authorize(transaction);
        notificationService.notifyTransaction(createdTransaction);
        return createdTransaction;
    }

    private void makeTransaction(@NotNull Transaction transaction){
        if (customerRepository.findById(transaction.payer()).isPresent()){
            Customer sender = customerRepository.findById(transaction.payer()).get();
            customerRepository.save(sender.debit(transaction.value()));
        }

        if (customerRepository.findById(transaction.payee()).isPresent()){
            Customer receiver = customerRepository.findById(transaction.payee()).get();
            customerRepository.save(receiver.credit(transaction.value()));
        }
    }


    private void validate(@NotNull Transaction transaction){
        logger.info("Validating transaction {}", transaction);
        customerRepository.findById(transaction.payee())
                .map(payee -> customerRepository.findById(transaction.payer())
                        .map(payer -> isPayerValid(transaction, payer) ? transaction : null)
                        .orElseThrow(() -> new InvalidTransactionException("Invalid transaction - " + transaction))
                ).orElseThrow(() -> new InvalidTransactionException("Invalid transaction - " + transaction));
    }

    private boolean isPayerValid(@NotNull Transaction transaction, @NotNull Customer payer){
        return payer.isCommonCustomer() &&
                payer.isAbleToPay(transaction.value()) &&
                !payer.id().equals(transaction.payee());
    }
}