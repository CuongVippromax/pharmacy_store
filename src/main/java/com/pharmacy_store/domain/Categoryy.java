package com.pharmacy_store.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "category")
@Getter
@Setter
public class Categoryy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long category_id;
    @NotBlank
    private String name;
}
