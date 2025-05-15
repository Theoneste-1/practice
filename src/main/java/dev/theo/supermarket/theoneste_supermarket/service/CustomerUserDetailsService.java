package dev.theo.supermarket.theoneste_supermarket.service;


import dev.theo.supermarket.theoneste_supermarket.exception.ResourceNotFoundException;
import dev.theo.supermarket.theoneste_supermarket.model.Customer;
import dev.theo.supermarket.theoneste_supermarket.model.CustomerUserDetails;
import dev.theo.supermarket.theoneste_supermarket.repository.CustomerRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerUserDetailsService implements UserDetailsService {

    private final CustomerRepository customerRepository;

    public CustomerUserDetailsService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Customer customer = null;
        try {
            customer = customerRepository.findByEmail(email)
                    .orElseThrow(() -> new ResourceNotFoundException("Customer not found with email: " + email));
        } catch (ResourceNotFoundException e) {
            throw new RuntimeException(e);
        }
        return new CustomerUserDetails(customer);
    }
}