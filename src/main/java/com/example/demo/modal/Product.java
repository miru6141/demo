package com.example.demo.modal;

import org.springframework.data.annotation.Id;


@Entity
public class Product {

    @Id
    private String id; 
    private String productName;
    private String productDescription;
    private double productPrice;

    // getters & setters

     public String getId() {
        return id;
    }

     public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }
}
