package com.sakher.gestiondestock.dto;

import com.sakher.gestiondestock.model.MvtStk;
import com.sakher.gestiondestock.model.TypeMvtStk;
import lombok.Builder;
import lombok.Data;


import java.math.BigDecimal;
import java.time.Instant;
@Data
@Builder
public class MvtStkDto {

    private Integer id;

    private ArticleDto article;

    private Instant dateMvt;

    private BigDecimal quantite;

    private TypeMvtStk typeMvt;

    public  static MvtStkDto fromEntity(MvtStk mvtStk) {
        if(mvtStk == null) {
            return  null;
        }

        return MvtStkDto.builder()
                .id(mvtStk.getId())
                .dateMvt(mvtStk.getDateMvt())
                .quantite(mvtStk.getQuantite())
                .article(ArticleDto.fromEntity(mvtStk.getArticle()))
                .build();

    }

    public  static  MvtStk toEntity(MvtStkDto mvtStkDto) {

        if (mvtStkDto == null) {
            return null;
        }

        MvtStk mvtStk = new MvtStk();
        mvtStk.setId(mvtStkDto.getId());
        mvtStk.setDateMvt(mvtStkDto.getDateMvt());
        mvtStk.setArticle(ArticleDto.toEntity(mvtStkDto.getArticle()));
        mvtStk.setQuantite(mvtStkDto.getQuantite());

        return mvtStk;
    }
}
