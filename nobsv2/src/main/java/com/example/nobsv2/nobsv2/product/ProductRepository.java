package com.example.nobsv2.nobsv2.product;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.nobsv2.nobsv2.product.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

    List<Product> findByNameContaining(String name);

    @Query("SELECT p from Product p where p.name LIKE %:keyword% OR p.description LIKE %:keyword%")
    List<Product> findByNameOrDescriptionContaining(@Param("keyword") String name);

}
