package com.avila.picpay.controller;
import com.avila.picpay.exception.CustomerNotFoundException;
import com.avila.picpay.exception.InvalidCustomerRegistryException;
import com.avila.picpay.model.Customer;
import com.avila.picpay.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController @AllArgsConstructor
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerRepository repository;

    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer){
        if (!customer.isCommonCustomer() && !customer.isSellerCustomer()){
            throw new InvalidCustomerRegistryException("Invalid customer type");
        }

        if (repository.existsByEmail(customer.email())){
            throw new InvalidCustomerRegistryException("Email already in use");
        }

        if (repository.existsByCpf(customer.cpf())){
            throw new InvalidCustomerRegistryException("CPF already in use");
        }

        return repository.save(customer);
    }

    @GetMapping
    public List<Customer> listAll(){
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Customer findById(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
    }
}