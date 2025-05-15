package dev.theo.supermarket.theoneste_supermarket.repository;


import dev.theo.supermarket.theoneste_supermarket.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByEmail(String email);
}