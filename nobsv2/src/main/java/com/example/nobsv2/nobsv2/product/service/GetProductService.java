package com.example.nobsv2.nobsv2.product.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.nobsv2.nobsv2.Query;
import com.example.nobsv2.nobsv2.exceptions.ProductNotFoundException;
import com.example.nobsv2.nobsv2.product.ProductRepository;
import com.example.nobsv2.nobsv2.product.model.Product;
import com.example.nobsv2.nobsv2.product.model.ProductDTO;

@Service
public class GetProductService implements Query<UUID, ProductDTO> {

    private final ProductRepository productRepository;

    public GetProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<ProductDTO> execute(UUID id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent())
            return ResponseEntity.ok(new ProductDTO(product.get()));

        throw new ProductNotFoundException();
    }

}
