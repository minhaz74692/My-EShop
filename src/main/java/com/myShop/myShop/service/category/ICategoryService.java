package com.myShop.myShop.service.category;

import com.myShop.myShop.model.Category;

import java.util.List;

public interface ICategoryService{
    Category getCategoryById(Long categoryId);
    Category getCategoryByName(String name);
    List<Category> getAllCategories();
    Category addCategory(Category category);
    Category updateCategory(Category category, Long id);
    void deleteCategory(Long id);
}
