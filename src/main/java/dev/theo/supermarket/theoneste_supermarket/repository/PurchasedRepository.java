package dev.theo.supermarket.theoneste_supermarket.repository;
import dev.theo.supermarket.theoneste_supermarket.model.Purchased;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchasedRepository extends JpaRepository<Purchased, Long> {
}