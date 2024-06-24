package org.ecommerce.order.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "orderId",
        "product"
})
@Generated("jsonschema2pojo")
public class Order {
    @JsonProperty("orderId")
    private int orderId;
    @JsonProperty("product")
    private Product product;

    public Order(int orderId, Product product) {
        this.orderId = orderId;
        this.product = product;
    }

    @JsonProperty("orderId")
    public int getOrderId() {
        return orderId;
    }

    @JsonProperty("orderId")
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @JsonProperty("product")
    public Product getProduct() {
        return product;
    }

    @JsonProperty("product")
    public void setProduct(Product product) {
        this.product = product;
    }
}