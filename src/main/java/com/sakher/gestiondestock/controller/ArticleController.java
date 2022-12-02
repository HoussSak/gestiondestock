package com.sakher.gestiondestock.controller;

import com.sakher.gestiondestock.controller.api.ArticleApi;
import com.sakher.gestiondestock.dto.ArticleDto;
import com.sakher.gestiondestock.exception.ErrorCodes;
import com.sakher.gestiondestock.exception.InvalidEntityException;
import com.sakher.gestiondestock.services.ArticleService;
import lombok.AllArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class ArticleController implements ArticleApi {



    private ArticleService articleService;


    @Override
    public ArticleDto save(@Valid @RequestBody ArticleDto articleDto, BindingResult results) {

        List<String> errors=results.getFieldErrors()
                .stream()
                .map(e -> e.getDefaultMessage())
                .collect(Collectors.toList());


        if (!errors.isEmpty()) {

            System.out.println(errors);


            throw new InvalidEntityException("Article not valid", ErrorCodes.ARTICLE_NOT_VALID,errors);

        }


        return articleService.save(articleDto);
    }

    @Override
    public ArticleDto findById(Integer id) {
        return articleService.findById(id);
    }

    @Override
    public ArticleDto findByCodeArticle(String codeArticle) {
        return articleService.findByCodeArticle(codeArticle);
    }

    @Override
    public List<ArticleDto> findAll() {
        return articleService.findAll();
    }

    @Override
    public void delete(Integer id) {

        articleService.delete(id);

    }
}
