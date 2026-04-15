package com.codewithdurgesh.blog.services.impl;

import com.codewithdurgesh.blog.entities.Category;
import com.codewithdurgesh.blog.exceptions.ResourceNotFoundException;
import com.codewithdurgesh.blog.payloads.CategoryDto;
import com.codewithdurgesh.blog.repositories.CategoryReporitory;
import com.codewithdurgesh.blog.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.stream.Collectors;

public class categoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryReporitory categoryReporitory ;
    @Autowired
    private ModelMapper modelMapper;

    @Override

    //create Category
    public CategoryDto createCategory(CategoryDto categoryDto) {
       Category cat= this.modelMapper.map(categoryDto, Category.class);
       Category addedCategory=this.categoryReporitory.save(cat);
       return this.modelMapper.map(addedCategory,CategoryDto.class);
    }
    //*********************** UpdateCategory ***********************
    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
        Category cat=this.categoryReporitory.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Category Id",categoryId));
        cat.setCategoryTitle(categoryDto.getCategoryTitle());
        cat.setCategoryDescription(categoryDto.getCategoryDescription());
        Category updatedCategory=this.categoryReporitory.save(cat);
        return this.modelMapper.map(updatedCategory,CategoryDto.class);
    }
    //************************** GetAllCategory ******************************
    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category> categories=this.categoryReporitory.findAll() ;
        List<CategoryDto> categoryDtos=categories.stream().map((cat)->this.modelMapper.map(cat,CategoryDto.class)).collect(Collectors.toList());
        return categoryDtos;
    }
    //************************** GetCategoryById **************************
    @Override
    public CategoryDto getCategory(Integer categoryId) {
     Category cat=this.categoryReporitory.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Category Id",categoryId));
        return this.modelMapper.map(cat,CategoryDto.class);

    }
    //********************DeleteCategory*****************************
    @Override
    public void deleteCategory(Integer categoryId) {
        Category cat=this.categoryReporitory.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Category Id",categoryId));
        this.categoryReporitory.delete(cat);

    }
}
