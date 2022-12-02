package com.sakher.gestiondestock.services;

import com.sakher.gestiondestock.dto.ArticleDto;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

public interface ArticleService {

     ArticleDto save (ArticleDto articleDto);

     ArticleDto findById(Integer id);


     ArticleDto findByCodeArticle(String codeArticle);


     List<ArticleDto> findAll();

     void delete(Integer id);


}
