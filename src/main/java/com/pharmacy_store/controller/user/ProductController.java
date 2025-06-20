package com.pharmacy_store.controller.user;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.pharmacy_store.domain.Product;
import com.pharmacy_store.service.ProductService;

@RestController
@RequestMapping("/api")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
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

}
