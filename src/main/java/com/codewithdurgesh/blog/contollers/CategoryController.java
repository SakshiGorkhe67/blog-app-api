package com.codewithdurgesh.blog.contollers;

import com.codewithdurgesh.blog.payloads.CategoryDto;
import com.codewithdurgesh.blog.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    //*************************** CreateCategory ************************
    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
      CategoryDto createdUserDto= this.categoryService.createCategory(categoryDto);
      return new ResponseEntity<>(createdUserDto,HttpStatus.CREATED);
    }
    //********************** Update Category ****************************
    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,@PathVariable Integer categoryId){
       CategoryDto updatedCategory= this.categoryService.updateCategory(categoryDto,categoryId);
        return  ResponseEntity.ok(updatedCategory);
    }

    //************************* GetAllCategory *************************************
    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getAllCategory(){
        return ResponseEntity.ok( this.categoryService.getAllCategory());
    }

    //*********************** GetCategoryById ********************************

    public ResponseEntity<CategoryDto> GetCategoryById()
}
