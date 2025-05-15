package dev.theo.supermarket.theoneste_supermarket.controller;

import dev.theo.supermarket.theoneste_supermarket.dto.ProductDTO;
import dev.theo.supermarket.theoneste_supermarket.exception.ResourceNotFoundException;
import dev.theo.supermarket.theoneste_supermarket.model.Product;
import dev.theo.supermarket.theoneste_supermarket.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@Valid @RequestBody ProductDTO productDTO) {
        Product product = productService.addProduct(productDTO);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @GetMapping("/{code}")
    public ResponseEntity<Product> getProduct(@PathVariable String code) throws ResourceNotFoundException {
        Product product = productService.getProduct(code);
        return ResponseEntity.ok(product);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @PutMapping("/{code}")
    public ResponseEntity<Product> updateProduct(@PathVariable String code, @Valid @RequestBody ProductDTO productDTO) throws ResourceNotFoundException {
        Product product = productService.updateProduct(code, productDTO);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String code) throws ResourceNotFoundException {
        productService.deleteProduct(code);
        return ResponseEntity.noContent().build();
    }
}