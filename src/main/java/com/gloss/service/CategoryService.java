package com.gloss.service;

import com.gloss.model.Category;
import com.gloss.repository.CategoryCrudRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryCrudRepository categoryCrudRepository;

    // Dodaj uzytkownika do bazy
    public Category addCategory(String categoryName) {
        Category category = Category.builder()
                .categoryName(categoryName)
                .build();
        return categoryCrudRepository.save(category);
    }

    public Optional<Category> getCategory(int id){
        return categoryCrudRepository.findById(id);
    }

    public List<Category> getCategories(){
        return categoryCrudRepository.findAll();
    }

}
