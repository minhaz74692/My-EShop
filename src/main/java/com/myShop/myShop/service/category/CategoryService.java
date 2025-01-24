package com.myShop.myShop.service.category;

import com.myShop.myShop.exception.CategoryNotFoundException;
import com.myShop.myShop.model.Category;
import com.myShop.myShop.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService{
    private final  CategoryRepository categoryRepository;
    @Override
    public Category getCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(()-> new CategoryNotFoundException("Category Not Found!"));
    }
}
