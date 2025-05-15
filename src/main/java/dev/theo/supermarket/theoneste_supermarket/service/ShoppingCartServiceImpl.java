package dev.theo.supermarket.theoneste_supermarket.service;

import dev.theo.supermarket.theoneste_supermarket.dto.CartItemDTO;
import dev.theo.supermarket.theoneste_supermarket.dto.PurchaseDTO;
import dev.theo.supermarket.theoneste_supermarket.exception.ResourceNotFoundException;
import dev.theo.supermarket.theoneste_supermarket.model.Product;
import dev.theo.supermarket.theoneste_supermarket.model.Purchased;
import dev.theo.supermarket.theoneste_supermarket.model.Quantity;
import dev.theo.supermarket.theoneste_supermarket.repository.ProductRepository;
import dev.theo.supermarket.theoneste_supermarket.repository.PurchasedRepository;
import dev.theo.supermarket.theoneste_supermarket.repository.QuantityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ProductRepository productRepository;
    private final PurchasedRepository purchasedRepository;
    private final QuantityRepository quantityRepository;
    private final CustomerService customerService;

    public ShoppingCartServiceImpl(ProductRepository productRepository,
                                   PurchasedRepository purchasedRepository,
                                   QuantityRepository quantityRepository,
                                   CustomerService customerService) {
        this.productRepository = productRepository;
        this.purchasedRepository = purchasedRepository;
        this.quantityRepository = quantityRepository;
        this.customerService = customerService;
    }

    @Override
    public String addToCart(String customerEmail, CartItemDTO cartItemDTO) throws ResourceNotFoundException {
        // Verify customer exists
        customerService.getCustomerByEmail(customerEmail);

        // Verify product exists
        Product product = productRepository.findById(cartItemDTO.getProductCode())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with code: " + cartItemDTO.getProductCode()));

        Purchased purchased = new Purchased();
        purchased.setDate(LocalDate.now());
        purchased.setProductCode(product.getCode());
        purchased.setQuantity(cartItemDTO.getQuantity());
        purchased.setTotal(cartItemDTO.getQuantity() * product.getPrice());

        purchasedRepository.save(purchased);

        return "Product Saved";

        // In a real application, you would add items to a shopping cart entity
        // For this implementation, we'll just validate the request
    }

    @Override
    @Transactional
    public List<Purchased> checkout(String customerEmail, List<PurchaseDTO> purchases) throws ResourceNotFoundException {
        // Verify customer exists
        customerService.getCustomerByEmail(customerEmail);

        List<Purchased> purchasedItems = new ArrayList<>();
        LocalDate now = LocalDate.now();

        for (PurchaseDTO purchase : purchases) {
            Product product = productRepository.findById(purchase.getProductCode())
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found with code: " + purchase.getProductCode()));

            // Calculate total
            double total = product.getPrice() * purchase.getQuantity();

            // Create purchased record
            Purchased purchased = new Purchased();
            purchased.setProductCode(product.getCode());
            purchased.setQuantity(purchase.getQuantity());
            purchased.setTotal(total);
            purchased.setDate(now);
            purchasedItems.add(purchasedRepository.save(purchased));

            // Update quantity (assuming operation "SALE" for checkout)
            Quantity quantity = new Quantity();
            quantity.setProductCode(product.getCode());
            quantity.setQuantity(purchase.getQuantity());
            quantity.setOperation("SALE");
            quantity.setDate(now);
            quantityRepository.save(quantity);
        }

        return purchasedItems;
    }
}
