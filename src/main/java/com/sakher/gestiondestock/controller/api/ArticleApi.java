package com.sakher.gestiondestock.controller.api;

import com.sakher.gestiondestock.dto.ArticleDto;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.sakher.gestiondestock.utils.Constants.APP_ROOT;

public interface ArticleApi {

    @PostMapping(value =APP_ROOT+"/articles/create",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    ArticleDto save (@Valid @RequestBody ArticleDto articleDto, BindingResult results);

    @GetMapping(value = APP_ROOT+"/articles/idArticle/{idArticle}",produces = MediaType.APPLICATION_JSON_VALUE)
    ArticleDto findById(@PathVariable(value = "idArticle") Integer id);

    @GetMapping(value = APP_ROOT+"/articles/codeArticle/{codeArticle}",produces = MediaType.APPLICATION_JSON_VALUE)
    ArticleDto findByCodeArticle(@PathVariable String codeArticle);

    @GetMapping(value = APP_ROOT+"/articles/all",produces = MediaType.APPLICATION_JSON_VALUE)
    List<ArticleDto> findAll();

    @DeleteMapping(APP_ROOT+"/articles/delete/{idArticle}")
    void delete( @PathVariable("idArticle")Integer id);
}
