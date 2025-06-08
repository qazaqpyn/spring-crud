package com.example.nobsv2.nobsv2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.example.nobsv2.nobsv2.product.ProductRepository;
import com.example.nobsv2.nobsv2.product.model.Product;
import com.example.nobsv2.nobsv2.product.model.ProductDTO;
import com.example.nobsv2.nobsv2.product.service.GetProductService;

public class GetProductServiceTest {

    @Mock // what to mock the response of -> need this dependency to run the test
    private ProductRepository productRepository;

    @InjectMocks // the thing we are testing
    private GetProductService getProductService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void given_product_exists_when_get_product_service_return_product_dto() {
        Product product = new Product();
        product.setId(new UUID(0, 0));
        product.setName("Product name");
        product.setDescription("Product Description Testing");
        product.setPrice(9.009);

        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));

        ResponseEntity<ProductDTO> response = getProductService.execute(product.getId());

        assertEquals(ResponseEntity.ok(new ProductDTO(product)), response);
        verify(productRepository, times(1)).findById(product.getId());
    }

}
