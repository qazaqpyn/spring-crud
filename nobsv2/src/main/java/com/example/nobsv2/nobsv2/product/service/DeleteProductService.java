package com.example.nobsv2.nobsv2.product.service;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.nobsv2.nobsv2.Command;
import com.example.nobsv2.nobsv2.product.ProductRepository;

@Service
public class DeleteProductService implements Command<UUID, Void> {
    private final ProductRepository productRepository;

    public DeleteProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<Void> execute(UUID id) {
        this.productRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
