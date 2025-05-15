package dev.theo.supermarket.theoneste_supermarket.service;

import dev.theo.supermarket.theoneste_supermarket.dto.CartItemDTO;
import dev.theo.supermarket.theoneste_supermarket.dto.PurchaseDTO;
import dev.theo.supermarket.theoneste_supermarket.exception.ResourceNotFoundException;
import dev.theo.supermarket.theoneste_supermarket.model.Purchased;

import java.util.List;

public interface ShoppingCartService {
    void addToCart(String customerEmail, CartItemDTO cartItemDTO) throws ResourceNotFoundException;
    List<Purchased> checkout(String customerEmail, List<PurchaseDTO> purchases) throws ResourceNotFoundException;
}