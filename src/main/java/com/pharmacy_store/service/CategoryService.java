package com.pharmacy_store.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.pharmacy_store.domain.Categoryy;
import com.pharmacy_store.repository.CategoryRepository;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Categoryy createCategory(Categoryy categoryy) {
        if (this.categoryRepository.existsByName(categoryy.getName())) {
            return null;
        } else {
            Categoryy newCategoryy = new Categoryy();
            newCategoryy.setName(categoryy.getName());
            return newCategoryy;
        }

    }

    public List<Categoryy> getAllCategory() {
        List<Categoryy> listCategoryies = this.categoryRepository.findAll();
        return listCategoryies;
    }

    public Categoryy getCategoryById(long id) {
        Optional<Categoryy> getCategory = this.categoryRepository.findById(id);
        if (getCategory.isPresent()) {
            Categoryy gCategory = getCategory.get();
            return gCategory;
        } else {
            return null;
        }
    }

    public Categoryy updateCategoryy(long id, Categoryy categoryy) {
        Optional<Categoryy> getCategory = this.categoryRepository.findById(id);
        if (getCategory.isPresent()) {
            Categoryy gCategory = getCategory.get();
            gCategory.setName(categoryy.getName());
            return gCategory;
        } else {
            return null;
        }
    }

    public void deleteCategory(long id) {
        this.categoryRepository.deleteById(id);
    }
}
