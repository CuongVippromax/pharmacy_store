package com.pharmacy_store.controller.user;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pharmacy_store.domain.Categoryy;
import com.pharmacy_store.service.CategoryService;

@RestController
@RequestMapping("/api")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categoies")
    public ResponseEntity<List<Categoryy>> getAllCategory() {
        List<Categoryy> list = this.categoryService.getAllCategory();
        return ResponseEntity.ok().body(list);
    }

}
