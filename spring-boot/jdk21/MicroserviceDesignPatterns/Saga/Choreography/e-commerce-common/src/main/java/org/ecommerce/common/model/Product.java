package org.ecommerce.common.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product {
    private Integer productId;
    private String productName;
    private Integer productValue;
    private Boolean isInStock;
}