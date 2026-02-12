package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.modal.Product;
import com.example.demo.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    // Constructor injection (BEST practice)
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // CREATE PRODUCT
    // @PostMapping("/create")
    // public ResponseEntity<Product> createProduct(@RequestBody Product product) {
    // Product savedProduct = productService.createProduct(product);
    // return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    // }
    @PostMapping("/create")
    public ResponseEntity<?> createProduct(@RequestBody Product product) {
        if (product.getProductName() == null || product.getProductName().isBlank()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Product name is required");
        }

        Product savedProduct = productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }

    @GetMapping("/getAllProduct")
    public ResponseEntity<?> getAllProducts() {

        List<Product> products = productService.getAllProducts();

        if (products.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204
        }

        return ResponseEntity.ok(products); // 200
    }
    


      // 🔹 GET product by ID
    // GET PRODUCT BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable String id) {

        Optional<Product> product = productService.getProductById(id);

        return product
                .map(p -> ResponseEntity.ok(p))                // 200 OK
                .orElseGet(() -> ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .build());                              // 404 NOT FOUND
    }
   
    // ✅ DELETE BY ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id) {

        if (productService.getProductById(id).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        productService.deleteProductById(id);
        return ResponseEntity.noContent().build(); // 204
    }

}
