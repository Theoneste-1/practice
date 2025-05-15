package dev.theo.supermarket.theoneste_supermarket.repository;

import dev.theo.supermarket.theoneste_supermarket.model.Quantity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuantityRepository extends JpaRepository<Quantity, Long> {
}