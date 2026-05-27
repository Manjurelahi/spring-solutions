package circuitbreaker.frameworkretry.model;

import org.springframework.stereotype.Component;

@Component
public class Product {
    private int productId;
    private String productName;
    private int productValue;

    public Product() {}

    public Product(int productId, String productName, int productValue) {
        this.productId = productId;
        this.productName = productName;
        this.productValue = productValue;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductValue() {
        return productValue;
    }

    public void setProductValue(int productValue) {
        this.productValue = productValue;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productValue=" + productValue +
                '}';
    }
}