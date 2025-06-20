package com.pharmacy_store.domain;

import jakarta.persistence.Column;
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
@Table()
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long product_id;
    @NotBlank(message = "Tên thuốc không được để trống ")
    private String name;
    @NotBlank(message = "Phân loại thuốc bị sai")
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Categoryy category_id;
    @Column(columnDefinition = "MEDIUMTEXT")
    private String description;
    @NotBlank(message = "Giá thuốc không đưọc bỏ trống")
    private long price;

    private String image;

}
