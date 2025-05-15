package dev.theo.supermarket.theoneste_supermarket.service;

import dev.theo.supermarket.theoneste_supermarket.dto.QuantityDTO;
import dev.theo.supermarket.theoneste_supermarket.exception.ResourceNotFoundException;
import dev.theo.supermarket.theoneste_supermarket.model.Quantity;
import dev.theo.supermarket.theoneste_supermarket.repository.ProductRepository;
import dev.theo.supermarket.theoneste_supermarket.repository.QuantityRepository;
import org.springframework.stereotype.Service;

@Service
public class QuantityServiceImpl implements QuantityService {

    private final QuantityRepository quantityRepository;
    private final ProductRepository productRepository;

    public QuantityServiceImpl(QuantityRepository quantityRepository, ProductRepository productRepository) {
        this.quantityRepository = quantityRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Quantity addQuantity(QuantityDTO quantityDTO) throws ResourceNotFoundException {
        // Check if product exists
        if (!productRepository.existsById(quantityDTO.getProductCode())) {
            throw new ResourceNotFoundException("Product not found with code: " + quantityDTO.getProductCode());
        }

        Quantity quantity = new Quantity();
        quantity.setProductCode(quantityDTO.getProductCode());
        quantity.setQuantity(quantityDTO.getQuantity());
        quantity.setOperation(quantityDTO.getOperation());
        quantity.setDate(quantityDTO.getDate());

        return quantityRepository.save(quantity);
    }
}