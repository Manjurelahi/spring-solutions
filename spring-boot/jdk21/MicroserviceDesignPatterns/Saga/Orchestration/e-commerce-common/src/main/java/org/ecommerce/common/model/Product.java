package org.ecommerce.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product {
    private Integer productId;
    private String productName;
    private Integer productValue;
    private Boolean isInStock;
}