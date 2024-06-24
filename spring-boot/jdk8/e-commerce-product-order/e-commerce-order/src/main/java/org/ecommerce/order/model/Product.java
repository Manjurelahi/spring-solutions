package org.ecommerce.order.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "productId",
        "productName",
        "productValue"
})
@Generated("jsonschema2pojo")
public class Product {
    @JsonProperty("productId")
    private int productId;
    @JsonProperty("productName")
    private String productName;
    @JsonProperty("productValue")
    private int productValue;

    public Product() {}

    public Product(int productId, String productName, int productValue) {
        this.productId = productId;
        this.productName = productName;
        this.productValue = productValue;
    }

    @JsonProperty("productId")
    public int getProductId() {
        return productId;
    }

    @JsonProperty("productId")
    public void setProductId(int productId) {
        this.productId = productId;
    }

    @JsonProperty("productName")
    public String getProductName() {
        return productName;
    }

    @JsonProperty("productName")
    public void setProductName(String productName) {
        this.productName = productName;
    }

    @JsonProperty("productValue")
    public int getProductValue() {
        return productValue;
    }

    @JsonProperty("productValue")
    public void setProductValue(int productValue) {
        this.productValue = productValue;
    }
}