package com.example.nobsv2.nobsv2.product.service;

import java.util.Optional;

import org.slf4j.*;
import org.springframework.cache.annotation.CachePut;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.nobsv2.nobsv2.Query;
import com.example.nobsv2.nobsv2.exceptions.ProductNotFoundException;
import com.example.nobsv2.nobsv2.product.ProductRepository;
import com.example.nobsv2.nobsv2.product.model.Product;
import com.example.nobsv2.nobsv2.product.model.ProductDTO;
import com.example.nobsv2.nobsv2.product.model.UpdateProductCommand;

@Service
public class UpdateProductService implements Query<UpdateProductCommand, ProductDTO> {
    private final ProductRepository productRepository;

    private static final Logger logger = LoggerFactory.getLogger(UpdateProductService.class);

    public UpdateProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @CachePut(value = "productCache", key = "#input.getId()")
    public ResponseEntity<ProductDTO> execute(UpdateProductCommand input) {

        Optional<Product> productOptional = this.productRepository.findById(input.getId());

        if (productOptional.isPresent()) {
            Product product = input.getProduct();
            product.setId(productOptional.get().getId());

            this.productRepository.save(product);

            logger.info("Product %s is updated", input.getId().toString());

            return ResponseEntity.ok(new ProductDTO(product));
        }

        throw new ProductNotFoundException();
    }

}
