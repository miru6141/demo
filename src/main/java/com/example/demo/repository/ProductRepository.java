package com.example.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.demo.modal.Product;

public interface ProductRepository extends MongoRepository<Product, String> {
}