package com.evantagesoft.ecommerce.service.category;

import com.evantagesoft.ecommerce.entity.Category;

import java.util.List;

public interface CategoryService {
    Category addCategory(String name);

    List<Category> getAllCategories();
}
