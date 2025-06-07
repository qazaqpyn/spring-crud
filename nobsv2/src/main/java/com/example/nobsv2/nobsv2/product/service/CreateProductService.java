package com.example.nobsv2.nobsv2.product.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.nobsv2.nobsv2.Command;
import com.example.nobsv2.nobsv2.product.ProductRepository;
import com.example.nobsv2.nobsv2.product.model.Product;
import com.example.nobsv2.nobsv2.product.model.ProductDTO;
import com.example.nobsv2.nobsv2.product.validators.ProductValidator;

@Service
public class CreateProductService implements Command<Product, ProductDTO> {
    private final ProductRepository productRepository;

    public CreateProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<ProductDTO> execute(Product product) {
        ProductValidator.execute(product);

        Product created = productRepository.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ProductDTO(created));
    }
}
