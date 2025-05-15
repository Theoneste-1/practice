package dev.theo.supermarket.theoneste_supermarket.service;

import dev.theo.supermarket.theoneste_supermarket.dto.ProductDTO;
import dev.theo.supermarket.theoneste_supermarket.exception.ResourceNotFoundException;
import dev.theo.supermarket.theoneste_supermarket.model.Product;
import dev.theo.supermarket.theoneste_supermarket.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product addProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setCode(productDTO.getCode());
        product.setName(productDTO.getName());
        product.setProductType(productDTO.getProductType());
        product.setPrice(productDTO.getPrice());
        product.setInDate(productDTO.getInDate());
        product.setImage(productDTO.getImage());

        return productRepository.save(product);
    }

    @Override
    public Product getProduct(String code) throws ResourceNotFoundException {
        return productRepository.findById(code)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with code: " + code));
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product updateProduct(String code, ProductDTO productDTO) throws ResourceNotFoundException {
        Product product = getProduct(code);
        product.setName(productDTO.getName());
        product.setProductType(productDTO.getProductType());
        product.setPrice(productDTO.getPrice());
        product.setInDate(productDTO.getInDate());
        product.setImage(productDTO.getImage());

        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(String code) throws ResourceNotFoundException {
        Product product = getProduct(code);
        productRepository.delete(product);
    }
}