package org.ecommerce.product.api;

import org.ecommerce.product.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("products")
public class ProductController {
    @GetMapping(value = "/{productId}", produces = "application/json")
    public ResponseEntity<Product> getProductDetails(@PathVariable(value="productId")Integer productId) {
        Product product = new Product(productId, "Mobile Phone", 14999);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
}