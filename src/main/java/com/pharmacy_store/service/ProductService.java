package com.pharmacy_store.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.pharmacy_store.domain.Product;
import com.pharmacy_store.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(Product product) {
        Product newProduct = new Product();
        newProduct.setName(product.getName());
        newProduct.setCategory_id(product.getCategory_id());
        newProduct.setPrice(product.getPrice());
        newProduct.setDescription(product.getDescription());
        newProduct.setImage(product.getImage());
        return newProduct;
    }

    public List<Product> getAllProducts() {
        List<Product> list = this.productRepository.findAll();
        return list;
    }

    public Product getProductById(long id) {
        Optional<Product> product = this.productRepository.findById(id);
        if (product.isPresent()) {
            Product getProduct = product.get();
            return getProduct;
        } else
            return null;
    }

    public Product updateProduct(long id, Product product) {
        Optional<Product> gproduct = this.productRepository.findById(id);
        if (gproduct.isPresent()) {
            Product updateProduct = gproduct.get();
            updateProduct.setName(product.getName());
            updateProduct.setCategory_id(product.getCategory_id());
            updateProduct.setPrice(product.getPrice());
            updateProduct.setDescription(product.getDescription());
            updateProduct.setImage(product.getImage());
            return updateProduct;
        } else
            return null;

    }

    public void deleteProduct(long id) {
        this.productRepository.deleteById(id);
    }
}
