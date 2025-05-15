package dev.theo.supermarket.theoneste_supermarket.service;


import dev.theo.supermarket.theoneste_supermarket.dto.CustomerDTO;
import dev.theo.supermarket.theoneste_supermarket.exception.DuplicateResourceException;
import dev.theo.supermarket.theoneste_supermarket.exception.ResourceNotFoundException;
import dev.theo.supermarket.theoneste_supermarket.model.Customer;
import dev.theo.supermarket.theoneste_supermarket.model.CustomerUserDetails;

public interface CustomerService {
    Customer registerCustomer(CustomerDTO customerDTO) throws DuplicateResourceException;
    Customer getCustomerByEmail(String email) throws ResourceNotFoundException;
    CustomerUserDetails loadUserByEmail(String email) throws ResourceNotFoundException;

}