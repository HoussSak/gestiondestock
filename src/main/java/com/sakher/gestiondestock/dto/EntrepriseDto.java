package com.sakher.gestiondestock.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sakher.gestiondestock.model.CommandeClient;
import com.sakher.gestiondestock.model.Entreprise;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class EntrepriseDto {
    private Integer id;

    private String nom;

    private String description;
    @JsonIgnore
    private AdresseDto adresse;

    @JsonIgnore
    private String photo;

    private String email;

    private String numTel;

    private String siteWeb;
    @JsonIgnore
    private List<UtilisateurDto> utilisateurs;



    public static EntrepriseDto fromEntity(Entreprise entreprise) {

        if (entreprise == null) {
            return null;
        }
        return EntrepriseDto.builder()
                .id(entreprise.getId())
                .nom(entreprise.getNom())
                .description(entreprise.getDescription())
                .email(entreprise.getEmail())
                .numTel(entreprise.getNumTel())
                .siteWeb(entreprise.getSiteWeb())
                .build();

    }

    public static Entreprise toEntity(EntrepriseDto entrepriseDto) {

        if (entrepriseDto == null) {
            return null;
        }
        Entreprise entreprise = new Entreprise();
        entreprise.setId(entrepriseDto.getId());
        entreprise.setNom(entrepriseDto.getNom());
        entreprise.setDescription(entrepriseDto.getDescription());
        entreprise.setEmail(entrepriseDto.getEmail());
        entreprise.setNumTel(entrepriseDto.getNumTel());
        entreprise.setSiteWeb(entrepriseDto.getSiteWeb());

        return entreprise;
    }
}
