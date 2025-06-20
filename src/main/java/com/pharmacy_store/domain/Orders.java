package com.pharmacy_store.domain;

import com.pharmacy_store.util.constant.StatusEnum;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orders_id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user_id;
    @Enumerated(EnumType.STRING)
    private StatusEnum status;
    private long total_price;
}
