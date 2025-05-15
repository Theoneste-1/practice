package dev.theo.supermarket.theoneste_supermarket.dto;

public class AuthResponse {
    private Long id;
    private String email;
    private String firstname;

    public AuthResponse(Long id, String email, String firstname) {
        this.id = id;
        this.email = email;
        this.firstname = firstname;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstname() {
        return firstname;
    }
}