package com.example.nobsv2.nobsv2.product;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.nobsv2.nobsv2.product.model.Product;
import com.example.nobsv2.nobsv2.product.model.ProductDTO;
import com.example.nobsv2.nobsv2.product.model.UpdateProductCommand;
import com.example.nobsv2.nobsv2.product.service.CreateProductService;
import com.example.nobsv2.nobsv2.product.service.DeleteProductService;
import com.example.nobsv2.nobsv2.product.service.GetProductService;
import com.example.nobsv2.nobsv2.product.service.GetProductsService;
import com.example.nobsv2.nobsv2.product.service.UpdateProductService;

@RestController
public class ProductController {
    private final CreateProductService createProductService;
    private final UpdateProductService updateProductService;
    private final GetProductsService getProductsService;
    private final GetProductService getProductService;
    private final DeleteProductService deleteProductService;

    public ProductController(CreateProductService createProductService, UpdateProductService updateProductService,
            GetProductsService getProductsService, DeleteProductService deleteProductService,
            GetProductService getProductService) {
        this.createProductService = createProductService;
        this.updateProductService = updateProductService;
        this.getProductsService = getProductsService;
        this.getProductService = getProductService;
        this.deleteProductService = deleteProductService;
    }

    @PostMapping("/product")
    public ResponseEntity<ProductDTO> createProduct(@RequestBody Product product) {
        return this.createProductService.execute(product);
    }

    @GetMapping("/product")
    public ResponseEntity<List<ProductDTO>> getProducts() {
        return this.getProductsService.execute(null);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable UUID id) {
        return this.getProductService.execute(id);
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable UUID id, @RequestBody Product product) {
        return this.updateProductService.execute(new UpdateProductCommand(id, product));
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable UUID id) {
        return this.deleteProductService.execute(id);
    }

}
