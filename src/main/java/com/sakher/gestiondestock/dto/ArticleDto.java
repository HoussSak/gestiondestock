package com.sakher.gestiondestock.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sakher.gestiondestock.model.Article;
import com.sakher.gestiondestock.model.Category;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Builder
@Data
public class ArticleDto {
    private Integer id;

    @NotBlank(message = "Veuillez saisir le code de l'article")
    private String codeArticle;

    @NotBlank(message = "Veuillez saisir la désignation de l'article")
    private String designation;

    @NotNull(message = "Please provide a price")
    private BigDecimal prixUnitaireHt;

    @NotNull(message = "Please provide a price")
    private BigDecimal tauxTva;

    @NotNull(message = "Please provide a price")
    @DecimalMin("1.00")
    private BigDecimal prixUnitaireTtc;
    @JsonIgnore
    private String photo;

    @JsonIgnore
    private CategoryDto category;

    private Integer idEntreprise;


    public static ArticleDto fromEntity(Article article) {

        if (article == null) {
            return null;
        }
        return ArticleDto.builder()
                .id(article.getId())
                .codeArticle(article.getCodeArticle())
                .designation(article.getDesignation())
                .prixUnitaireHt(article.getPrixUnitaireHt())
                .prixUnitaireTtc(article.getPrixUnitaireTtc())
                .idEntreprise(article.getIdEntreprise())
                .tauxTva(article.getTauxTva())
                .build();

    }

    public static Article toEntity(ArticleDto articleDto) {

        if (articleDto == null) {
            return null;
        }

        Article article = new Article();
        article.setId(articleDto.getId());
        article.setCodeArticle(articleDto.getCodeArticle());
        article.setDesignation(articleDto.getDesignation());
        article.setPrixUnitaireHt(articleDto.getPrixUnitaireHt());
        article.setPrixUnitaireTtc(articleDto.getPrixUnitaireTtc());
        article.setIdEntreprise(articleDto.getIdEntreprise());
        article.setTauxTva(articleDto.getTauxTva());

        return article;
    }

}
