package com.codewithdurgesh.blog.services;

import com.codewithdurgesh.blog.payloads.CategoryDto;

import java.util.List;

public interface CategoryService {
    public CategoryDto createCategory(CategoryDto categoryDto);
    public CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryId);
    public List<CategoryDto> getAllCategory();
    public  CategoryDto getCategory(Integer categoryId);
    public void deleteCategory(Integer categoryId);
}
