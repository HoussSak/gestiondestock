package com.sakher.gestiondestock.dto;



import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sakher.gestiondestock.model.Fournisseur;
import com.sakher.gestiondestock.model.LigneCommandeClient;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
@Data
@Builder
public class LigneCommandeClientDto {

    private Integer id;
    @JsonIgnore
    private ArticleDto article;
    @JsonIgnore
    private CommandeClientDto commandeClient;

    private BigDecimal quantite;

    private BigDecimal prixUnitaire;

    private Integer idEntreprise;




    public LigneCommandeClientDto fromEntity(LigneCommandeClient ligneCommandeClient) {

        if (ligneCommandeClient == null) {
            return null;
        }
        return LigneCommandeClientDto.builder()
                .id(ligneCommandeClient.getId())
                .quantite(ligneCommandeClient.getQuantite())
                .prixUnitaire(ligneCommandeClient.getPrixUnitaire())
                .idEntreprise(ligneCommandeClient.getIdEntreprise())
                .build();

    }

    public static LigneCommandeClient toEntity(LigneCommandeClientDto ligneCommandeClientDto) {

        if (ligneCommandeClientDto == null) {
            return null;
        }
        LigneCommandeClient ligneCommandeClient = new LigneCommandeClient();
        ligneCommandeClient.setId(ligneCommandeClientDto.getId());
        ligneCommandeClient.setQuantite(ligneCommandeClientDto.getQuantite());
        ligneCommandeClient.setIdEntreprise(ligneCommandeClient.getIdEntreprise());
        ligneCommandeClient.setPrixUnitaire(ligneCommandeClientDto.getPrixUnitaire());

        return ligneCommandeClient;
    }
}
