package com.pharmacy_store.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pharmacy_store.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
