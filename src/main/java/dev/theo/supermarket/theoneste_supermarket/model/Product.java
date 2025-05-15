package dev.theo.supermarket.theoneste_supermarket.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;


@Entity
@Table(name = "products")
public class Product {
    @Id
    private String code;

    @Column(nullable = false)
    private String name;

    @Column(name = "product_type", nullable = false)
    private String productType;

    @Column(nullable = false)
    private Double price;

    @Column(name = "in_date", nullable = false)
    private LocalDate inDate;

    @Column(nullable = false)
    private String image;

    // Getters and Setters
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public LocalDate getInDate() {
        return inDate;
    }

    public void setInDate(LocalDate inDate) {
        this.inDate = inDate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}