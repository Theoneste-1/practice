package dev.theo.supermarket.theoneste_supermarket.service;

import dev.theo.supermarket.theoneste_supermarket.dto.ProductDTO;
import dev.theo.supermarket.theoneste_supermarket.exception.ResourceNotFoundException;
import dev.theo.supermarket.theoneste_supermarket.model.Product;

import java.util.List;

public interface ProductService {
    Product addProduct(ProductDTO productDTO);
    Product getProduct(String code) throws ResourceNotFoundException;
    List<Product> getAllProducts();
    Product updateProduct(String code, ProductDTO productDTO) throws ResourceNotFoundException;
    void deleteProduct(String code) throws ResourceNotFoundException;
}