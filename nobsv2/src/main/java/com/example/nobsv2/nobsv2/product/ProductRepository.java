package com.example.nobsv2.nobsv2.product;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.nobsv2.nobsv2.product.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

}
