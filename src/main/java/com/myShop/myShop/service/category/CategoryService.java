package com.myShop.myShop.service.category;

import com.myShop.myShop.exception.AlreadyExistExeption;
import com.myShop.myShop.exception.ResourseNotFoundException;
import com.myShop.myShop.model.Category;
import com.myShop.myShop.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService{
    @Autowired
    private final  CategoryRepository categoryRepository;
    @Override
    public Category getCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(()-> new ResourseNotFoundException("Category Not Found!"));
    }

    @Override
    public Category getCategoryByName(String name) {
        return categoryRepository.findByName(name);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category addCategory(Category category) {
        return Optional.of(category).filter(c-> !categoryRepository.existsByName(c.getName()))
                .map(categoryRepository:: save)
                .orElseThrow(() -> new AlreadyExistExeption(category.getName() + " already exist!"));
    }

    @Override
    public Category updateCategory(Category category, Long id) {
        return Optional.ofNullable(getCategoryById(id))
                .map(oldCategory->{
                    oldCategory.setName(category.getName());
                    return categoryRepository.save(oldCategory);
                }).orElseThrow(()-> new ResourseNotFoundException("Category Not Found!"));
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.findById(id)
                .ifPresentOrElse(categoryRepository::delete, ()->{
                    throw new ResourseNotFoundException("Category not found!");
                });
    }
}
