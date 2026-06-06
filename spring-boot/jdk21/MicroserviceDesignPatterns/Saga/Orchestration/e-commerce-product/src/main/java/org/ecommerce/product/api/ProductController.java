package org.ecommerce.product.api;

import org.ecommerce.common.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {
    private List<Product> getProductList() {
        return List.of(new Product(1, "Mobile Phone", 14999, Boolean.TRUE),
                new Product(2, "Laptop System", 50000, Boolean.TRUE),
                new Product(3, "FM Radio", 1200, Boolean.FALSE));
    }
    @GetMapping(value = "/{productId}")
    public ResponseEntity<Product> getProductDetails(@PathVariable(value="productId")Integer productId) {
        Product product = getProductList().stream().filter(p -> p.getProductId().equals(productId)).findFirst()
                .orElse(null);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
}