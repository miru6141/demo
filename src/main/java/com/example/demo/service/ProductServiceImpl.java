package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.modal.Product;
import com.example.demo.repository.ProductRepository;
import java.util.Optional;



@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepo;

    public ProductServiceImpl(ProductRepository productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public Product createProduct(Product product) {
        return productRepo.save(product);
    }

     @Override
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

     @Override
    public Optional<Product> getProductById(String id) {
        return productRepo.findById(id);
    }

     @Override
    public void deleteProductById(String id) {
        productRepo.deleteById(id);   // ✅ MongoRepository method
    }
}
