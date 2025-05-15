package dev.theo.supermarket.theoneste_supermarket.service;

import dev.theo.supermarket.theoneste_supermarket.dto.QuantityDTO;
import dev.theo.supermarket.theoneste_supermarket.exception.ResourceNotFoundException;
import dev.theo.supermarket.theoneste_supermarket.model.Quantity;

public interface QuantityService {
    Quantity addQuantity(QuantityDTO quantityDTO) throws ResourceNotFoundException;
}