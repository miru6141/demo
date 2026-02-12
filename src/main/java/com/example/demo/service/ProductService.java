package com.example.demo.service;

import java.util.List;

import com.example.demo.modal.Product;
import java.util.Optional;


public interface ProductService {
    Product createProduct(Product product);
     List<Product> getAllProducts(); 
     Optional<Product> getProductById(String id);
     
    void deleteProductById(String id); 
  
}
