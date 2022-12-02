package com.sakher.gestiondestock.controller.api;

import com.sakher.gestiondestock.dto.CategoryDto;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static com.sakher.gestiondestock.utils.Constants.APP_ROOT;

public interface CategoryApi {


    @PostMapping(value = APP_ROOT+"/category/create",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    CategoryDto save(@Valid @RequestBody CategoryDto categoryDto, BindingResult result);


    @GetMapping(value = APP_ROOT+"/category/id/{idCategory}",produces = MediaType.APPLICATION_JSON_VALUE)
    CategoryDto findById(@PathVariable("idCategory") Integer id);


    @GetMapping(value = APP_ROOT+"/category/code/{codeCategory}",produces = MediaType.APPLICATION_JSON_VALUE)
    CategoryDto findByCode(@PathVariable("codeCategory") String code);

    @GetMapping(value = APP_ROOT+"/category/all",produces = MediaType.APPLICATION_JSON_VALUE)
    List<CategoryDto> findAll();

    @DeleteMapping(APP_ROOT+"/category/delete/{idCategory}")
    void deleteById(@PathVariable("idCategory") Integer id);
}
