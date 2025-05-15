package dev.theo.supermarket.theoneste_supermarket.repository;

import dev.theo.supermarket.theoneste_supermarket.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
}