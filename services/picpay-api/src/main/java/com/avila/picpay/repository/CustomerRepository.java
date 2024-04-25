package com.avila.picpay.repository;
import com.avila.picpay.model.Customer;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends ListCrudRepository<Customer, Long> {
    Customer findByCpf(Long cpf);
    Customer findByEmail(String email);
    boolean existsByCpf(Long cpf);
    boolean existsByEmail(String email);
}