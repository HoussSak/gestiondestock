package com.sakher.gestiondestock.services;

import com.sakher.gestiondestock.dto.CategoryDto;

import java.util.List;

public interface CategoryService {

    CategoryDto save (CategoryDto categoryDto);

    CategoryDto findById(Integer id);


    CategoryDto findByCode(String nom);


    List<CategoryDto> findAll();

    void delete(Integer id);
}
