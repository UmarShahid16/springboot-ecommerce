package com.evantagesoft.ecommerce.service.category;

import com.evantagesoft.ecommerce.entity.Category;
import com.evantagesoft.ecommerce.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements  CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public Category addCategory(String name) {

        Category category = new Category();
        category.setName(name);
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> getAllCategories() {

        return categoryRepository.findAll();
    }

}
