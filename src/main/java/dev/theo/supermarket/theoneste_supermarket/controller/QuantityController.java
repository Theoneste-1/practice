package dev.theo.supermarket.theoneste_supermarket.controller;


import dev.theo.supermarket.theoneste_supermarket.dto.QuantityDTO;
import dev.theo.supermarket.theoneste_supermarket.exception.ResourceNotFoundException;
import dev.theo.supermarket.theoneste_supermarket.model.Quantity;
import dev.theo.supermarket.theoneste_supermarket.service.QuantityService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/quantities")
public class QuantityController {

    private final QuantityService quantityService;

    public QuantityController(QuantityService quantityService) {
        this.quantityService = quantityService;
    }

    @PostMapping
    public ResponseEntity<Quantity> addQuantity(@Valid @RequestBody QuantityDTO quantityDTO) throws ResourceNotFoundException {
        Quantity quantity = quantityService.addQuantity(quantityDTO);
        return new ResponseEntity<>(quantity, HttpStatus.CREATED);
    }
}