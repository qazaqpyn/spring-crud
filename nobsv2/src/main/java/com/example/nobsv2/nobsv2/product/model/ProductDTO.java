package com.example.nobsv2.nobsv2.product.model;

import java.util.UUID;

import lombok.Data;

@Data
public class ProductDTO {
    private UUID id;
    private String name;
    private String description;

    public ProductDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
    }

}
