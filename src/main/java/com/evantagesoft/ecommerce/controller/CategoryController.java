package com.evantagesoft.ecommerce.controller;

import com.evantagesoft.ecommerce.dto.CategoryDto;
import com.evantagesoft.ecommerce.entity.Category;
import com.evantagesoft.ecommerce.response.Response;
import com.evantagesoft.ecommerce.service.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/addCategory")
    public ResponseEntity<Category> addCategory(@RequestBody CategoryDto categoryDto){
        String name = categoryDto.getName();
        if (name == null || name.isEmpty()) {
            return ResponseEntity.badRequest().body(null); // Handle empty name case
        }
       return ResponseEntity.ok( categoryService.addCategory(name));
    }

    @GetMapping("/getCategories")
    public ResponseEntity<List<Category>> getAllCategories(){
        return ResponseEntity.ok(categoryService.getAllCategories());
    }
}
