package com.avila.picpay.controller;
import com.avila.picpay.exception.InvalidCustomerTypeException;
import com.avila.picpay.model.Customer;
import com.avila.picpay.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController @AllArgsConstructor
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerRepository repository;

    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer){
        if (customer.isCommonCustomer() || customer.isSellerCustomer()){
            return repository.save(customer);
        }
        throw new InvalidCustomerTypeException("Invalid customer type");
    }
}