package com.pharmacy_store.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
public class Order_items {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderits_id;
    @ManyToOne
    @JoinColumn(name = "orders_id")
    private Orders orders_id;
    @ManyToOne
    private Product product_id;
    @NotBlank
    private long quantity;
    @NotBlank
    private long price;
    @NotBlank
    private long subtotal;
}
