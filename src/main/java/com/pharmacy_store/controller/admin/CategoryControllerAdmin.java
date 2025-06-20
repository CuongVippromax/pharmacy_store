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

import com.pharmacy_store.domain.Categoryy;
import com.pharmacy_store.service.CategoryService;

@RestController
@RequestMapping("/admin")
public class CategoryControllerAdmin {
    private final CategoryService categoryService;

    public CategoryControllerAdmin(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categoies")
    public ResponseEntity<List<Categoryy>> getAllCategory() {
        List<Categoryy> list = this.categoryService.getAllCategory();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping("/category")
    public ResponseEntity<Categoryy> createCategory(@RequestBody Categoryy category) {
        Categoryy newCategory = this.categoryService.createCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCategory);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<Categoryy> getCategoryById(@PathVariable long id) {
        Categoryy gCategoryy = this.categoryService.getCategoryById(id);
        return ResponseEntity.ok().body(gCategoryy);
    }

    @PutMapping("/category/{id}")
    public ResponseEntity<Categoryy> updateCategory(@RequestBody Categoryy category, @PathVariable long id) {
        Categoryy updateCategory = this.categoryService.updateCategoryy(id, category);
        return ResponseEntity.ok().body(updateCategory);
    }

    @DeleteMapping("/category/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable long id) {
        this.categoryService.deleteCategory(id);
        return ResponseEntity.ok().body("Delete Successfully !!!");
    }
}
