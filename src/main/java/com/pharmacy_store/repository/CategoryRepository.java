package com.pharmacy_store.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pharmacy_store.domain.Categoryy;

public interface CategoryRepository extends JpaRepository<Categoryy, Long> {
    Boolean existsByName(String name);
}