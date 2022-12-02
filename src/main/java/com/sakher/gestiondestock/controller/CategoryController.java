package com.sakher.gestiondestock.controller;

import com.sakher.gestiondestock.controller.api.CategoryApi;
import com.sakher.gestiondestock.dto.CategoryDto;
import com.sakher.gestiondestock.exception.ErrorCodes;
import com.sakher.gestiondestock.exception.InvalidEntityException;
import com.sakher.gestiondestock.services.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class CategoryController implements CategoryApi {

    private CategoryService categoryService;
    @Override
    public CategoryDto save(CategoryDto categoryDto, BindingResult result) {
        List<String> errors = result.getFieldErrors().stream()
                .map(fieldError -> fieldError.getDefaultMessage())
                .collect(Collectors.toList());

        if (!errors.isEmpty()) {

            throw new InvalidEntityException("Category not valid", ErrorCodes.CATEGORY_NOT_VALID,errors);
        }
        return categoryService.save(categoryDto);
    }

    @Override
    public CategoryDto findById(Integer id) {return categoryService.findById(id);}

    @Override
    public CategoryDto findByCode(String code) {return categoryService.findByCode(code);}

    @Override
    public List<CategoryDto> findAll() {return categoryService.findAll();}

    @Override
    public void deleteById(Integer id) {categoryService.delete(id);}
}
