package com.sakher.gestiondestock.services.impl;

import com.sakher.gestiondestock.dto.ArticleDto;
import com.sakher.gestiondestock.exception.EntityNotFoundException;
import com.sakher.gestiondestock.exception.ErrorCodes;
import com.sakher.gestiondestock.model.Article;
import com.sakher.gestiondestock.repository.ArticleRepository;
import com.sakher.gestiondestock.services.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Slf4j
public class ArticleServiceImpl implements ArticleService {


    @Autowired
    private ArticleRepository articleRepository;
    @Override
    public ArticleDto save( ArticleDto articleDto) {


        return ArticleDto.fromEntity(articleRepository.save(ArticleDto.toEntity(articleDto)));
    }

    @Override
    public ArticleDto findById(Integer id) {

        if (id == null) {

            log.error("Article ID is null");

            return null;
        }

        Optional<Article> article = articleRepository.findById(id);

        ArticleDto articleDto = ArticleDto.fromEntity(article.get());


        return Optional.of(articleDto).orElseThrow(()->
                new EntityNotFoundException("Article with id = "+ id+" not found",ErrorCodes.ARTICLE_NOT_FOUND)
                );
    }

    @Override
    public ArticleDto findByCodeArticle(String codeArticle) {

        if (codeArticle == null) {

            log.error("Article codeArticle is null");

            return null;
        }

        Optional<Article> article = articleRepository.findByCodeArticle(codeArticle);

        ArticleDto articleDto = ArticleDto.fromEntity(article.get());


        return Optional.of(articleDto).orElseThrow(()->

                new EntityNotFoundException("Article with codeArticle = "+codeArticle+" not found",ErrorCodes.ARTICLE_NOT_FOUND)

                );
    }

    @Override
    public List<ArticleDto> findAll() {
        return articleRepository.findAll().stream()
                .map(ArticleDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            log.error("Article ID is null");
            return;
        }
        articleRepository.deleteById(id);

    }
}
