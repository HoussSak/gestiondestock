package com.sakher.gestiondestock.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sakher.gestiondestock.model.Entreprise;
import com.sakher.gestiondestock.model.Fournisseur;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class FournisseurDto {
    private Integer id;


    private String nom;

    private String prenom;
    @JsonIgnore
    private AdresseDto asresse;
    @JsonIgnore
    private String photo;

    private String mail;

    private String numTel;
    @JsonIgnore
    private List<CommandeFournisseurDto> commandeFournisseurs;

    private Integer idEntreprise;




    public static FournisseurDto fromEntity(Fournisseur fournisseur) {

        if (fournisseur == null) {
            return null;
        }
        return FournisseurDto.builder()
                .id(fournisseur.getId())
                .nom(fournisseur.getNom())
                .prenom(fournisseur.getPrenom())
                .mail(fournisseur.getMail())
                .numTel(fournisseur.getNumTel())
                .idEntreprise(fournisseur.getIdEntreprise())
                .build();

    }

    public static Fournisseur toEntity(FournisseurDto fournisseurDto) {

        if (fournisseurDto == null) {
            return null;
        }
        Fournisseur fournisseur = new Fournisseur();
        fournisseur.setId(fournisseurDto.getId());
        fournisseur.setNom(fournisseurDto.getNom());
        fournisseur.setPrenom(fournisseurDto.getPrenom());
        fournisseur.setMail(fournisseurDto.getMail());
        fournisseur.setIdEntreprise(fournisseurDto.getIdEntreprise());
        fournisseur.setNumTel(fournisseurDto.getNumTel());

        return fournisseur;
    }
}
