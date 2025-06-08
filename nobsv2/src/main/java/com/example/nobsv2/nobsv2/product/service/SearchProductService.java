package com.example.nobsv2.nobsv2.product.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.nobsv2.nobsv2.Query;
import com.example.nobsv2.nobsv2.product.ProductRepository;
import com.example.nobsv2.nobsv2.product.model.ProductDTO;

@Service
public class SearchProductService implements Query<String, List<ProductDTO>> {
    private final ProductRepository productRepository;

    public SearchProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<List<ProductDTO>> execute(String input) {
        return ResponseEntity.ok(productRepository.findByNameOrDescriptionContaining(input)
                .stream()
                .map(ProductDTO::new)
                .toList());
    }

}
