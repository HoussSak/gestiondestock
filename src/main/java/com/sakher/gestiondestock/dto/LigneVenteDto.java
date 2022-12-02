package com.sakher.gestiondestock.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sakher.gestiondestock.model.LigneVente;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class LigneVenteDto {

    private Integer id;

    @JsonIgnore
    private VentesDto ventes;

    private BigDecimal quantite;

    private ArticleDto article;

    private BigDecimal prixUnitaire;

    private Integer idEntreprise;


    public LigneVenteDto fromEntity(LigneVente ligneVente) {

        if (ligneVente == null) {
            return null;
        }
        return LigneVenteDto.builder()
                .id(ligneVente.getId())
                .quantite(ligneVente.getQuantite())
                .prixUnitaire(ligneVente.getPrixUnitaire())
                .idEntreprise(ligneVente.getIdEntreprise())
                .build();

    }

    public static LigneVente toEntity(LigneVenteDto ligneVenteDto) {

        if (ligneVenteDto == null) {
            return null;
        }
        LigneVente ligneVente = new LigneVente();
        ligneVente.setId(ligneVenteDto.getId());
        ligneVente.setQuantite(ligneVenteDto.getQuantite());
        ligneVente.setIdEntreprise(ligneVenteDto.getIdEntreprise());
        ligneVente.setPrixUnitaire(ligneVenteDto.getPrixUnitaire());

        return ligneVente;
    }
}
