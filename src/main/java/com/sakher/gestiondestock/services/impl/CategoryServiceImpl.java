package com.sakher.gestiondestock.services.impl;

import com.sakher.gestiondestock.dto.CategoryDto;
import com.sakher.gestiondestock.exception.EntityNotFoundException;
import com.sakher.gestiondestock.exception.ErrorCodes;
import com.sakher.gestiondestock.model.Category;
import com.sakher.gestiondestock.repository.CategoryRepository;
import com.sakher.gestiondestock.services.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public CategoryDto save(CategoryDto categoryDto) {

        return CategoryDto.fromEntity(categoryRepository.save(CategoryDto.toEntity(categoryDto)));
    }

    @Override
    public CategoryDto findById(Integer id) {

        if (id == null) {
            log.error("Category ID is null");
            return null;
        }
        Optional<Category> category = categoryRepository.findById(id);

        CategoryDto categoryDto = CategoryDto.fromEntity(category.get());


        /*categoryRepository.findById(id)
                .map(CategoryDto::fromEntity)
                .orElseThrow(()->
                        new EntityNotFoundException("Category with ID ="+id+" not found", ErrorCodes.CATEGORY_NOT_FOUND)
                );*/



        return Optional.of(categoryDto).orElseThrow(()->
                       new EntityNotFoundException("Category with ID ="+id+" not found", ErrorCodes.CATEGORY_NOT_FOUND)
                );
    }

    @Override
    public CategoryDto findByCode(String code) {

        if (code == null) {
            log.error("Category code is null");
            return null;
        }
        Optional<Category> category= categoryRepository.findByCode(code);

        CategoryDto categoryDto = CategoryDto.fromEntity(category.get());

        return Optional.of(categoryDto).orElseThrow(()->
                new EntityNotFoundException("Category with Code = "+code+" not foound",ErrorCodes.CATEGORY_NOT_FOUND));
    }

    @Override
    public List<CategoryDto> findAll() {
        return categoryRepository.findAll().stream()
                .map(CategoryDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            log.error("Category id is null");
        }

        categoryRepository.deleteById(id);
    }
}
