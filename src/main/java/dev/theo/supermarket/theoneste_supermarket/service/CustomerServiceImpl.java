package dev.theo.supermarket.theoneste_supermarket.service;

import dev.theo.supermarket.theoneste_supermarket.dto.CustomerDTO;
import dev.theo.supermarket.theoneste_supermarket.exception.DuplicateResourceException;
import dev.theo.supermarket.theoneste_supermarket.exception.ResourceNotFoundException;
import dev.theo.supermarket.theoneste_supermarket.model.Customer;
import dev.theo.supermarket.theoneste_supermarket.model.CustomerUserDetails;
import dev.theo.supermarket.theoneste_supermarket.repository.CustomerRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    public CustomerServiceImpl(CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Customer registerCustomer(CustomerDTO customerDTO) throws DuplicateResourceException {
        if (customerRepository.findByEmail(customerDTO.getEmail()).isPresent()) {
            throw new DuplicateResourceException("Email already registered");
        }

        Customer customer = new Customer();
        customer.setFirstname(customerDTO.getFirstname());
        customer.setEmail(customerDTO.getEmail());
        customer.setPhone(customerDTO.getPhone());
        customer.setPassword(passwordEncoder.encode(customerDTO.getPassword()));
        return customerRepository.save(customer);
    }

    @Override
    public Customer getCustomerByEmail(String email) throws ResourceNotFoundException {
        return customerRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with email: " + email));
    }

    @Override
    public CustomerUserDetails loadUserByEmail(String email) throws ResourceNotFoundException {
        Customer customer = getCustomerByEmail(email);
        return new CustomerUserDetails(customer);
    }
}