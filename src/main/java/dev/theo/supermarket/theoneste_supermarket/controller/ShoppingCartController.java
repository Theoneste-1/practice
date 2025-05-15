package dev.theo.supermarket.theoneste_supermarket.controller;

import dev.theo.supermarket.theoneste_supermarket.dto.CartItemDTO;
import dev.theo.supermarket.theoneste_supermarket.dto.PurchaseDTO;
import dev.theo.supermarket.theoneste_supermarket.exception.ResourceNotFoundException;
import dev.theo.supermarket.theoneste_supermarket.model.Purchased;
import dev.theo.supermarket.theoneste_supermarket.service.ShoppingCartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @PostMapping("/{email}/items")
    public ResponseEntity<Void> addToCart(@PathVariable String email, @Valid @RequestBody CartItemDTO cartItemDTO) throws ResourceNotFoundException {
        shoppingCartService.addToCart(email, cartItemDTO);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{email}/checkout")
    public ResponseEntity<List<Purchased>> checkout(@PathVariable String email, @Valid @RequestBody List<PurchaseDTO> purchases) throws ResourceNotFoundException {
        List<Purchased> purchasedItems = shoppingCartService.checkout(email, purchases);
        return new ResponseEntity<>(purchasedItems, HttpStatus.CREATED);
    }
}