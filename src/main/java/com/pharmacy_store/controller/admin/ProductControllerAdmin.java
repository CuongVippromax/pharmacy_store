package com.pharmacy_store.controller.admin;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pharmacy_store.domain.Product;
import com.pharmacy_store.service.ProductService;

@RestController
@RequestMapping("/api/admin")
public class ProductControllerAdmin {
    private final ProductService productService;

    public ProductControllerAdmin(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/product")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product newProduct = this.productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProduct() {
        List<Product> list = this.productService.getAllProducts();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/product")
    public ResponseEntity<Product> getProduct(long id) {
        Product getProduct = this.productService.getProductById(id);
        return ResponseEntity.ok().body(getProduct);
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable long id, @RequestBody Product product) {
        Product updateProduct = this.productService.updateProduct(id, product);
        return ResponseEntity.ok().body(updateProduct);
    }

    @DeleteMapping("/product/{id}")
    public void deleteProduct(@PathVariable long id) {
        this.productService.deleteProduct(id);
    }
}
