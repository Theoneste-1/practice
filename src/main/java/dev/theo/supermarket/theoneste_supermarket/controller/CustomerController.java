package dev.theo.supermarket.theoneste_supermarket.controller;

import dev.theo.supermarket.theoneste_supermarket.dto.CustomerDTO;
import dev.theo.supermarket.theoneste_supermarket.exception.DuplicateResourceException;
import dev.theo.supermarket.theoneste_supermarket.exception.ResourceNotFoundException;
import dev.theo.supermarket.theoneste_supermarket.model.Customer;
import dev.theo.supermarket.theoneste_supermarket.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/register")
    public ResponseEntity<Customer> registerCustomer(@Valid @RequestBody CustomerDTO customerDTO) throws DuplicateResourceException {
        Customer customer = customerService.registerCustomer(customerDTO);
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }

    @GetMapping("/{email}")
    public ResponseEntity<Customer> getCustomerByEmail(@PathVariable String email) throws ResourceNotFoundException {
        Customer customer = customerService.getCustomerByEmail(email);
        return ResponseEntity.ok(customer);
    }
}