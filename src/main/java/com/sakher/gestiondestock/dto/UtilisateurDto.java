package com.sakher.gestiondestock.dto;



import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sakher.gestiondestock.model.Utilisateur;
import com.sakher.gestiondestock.model.Ventes;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.time.Instant;
import java.util.List;

@Data
@Builder
public class UtilisateurDto {
    private Integer id;


    @NotBlank(message = "Veuillez renseigner le code")
    private String code;

    @NotBlank(message = "Veuillez renseigner le nom")
    private String nom;

    @NotBlank(message = "Veuillez renseigner le prenom")
    private String prenom;

    @NotBlank(message = "Veuillez renseigner l''adresse mail")
    @Email(message = "Veuillez saisir une adresse \\u00e9lectronique correcte")
    private String email;

    @NotBlank(message = "Veuillez renseigner la date de naissance")
    @Past(message = "La date de naissance doit etre dans la passé !")
    private Instant dateDeNaissance;

    @JsonIgnore
    @NotBlank(message = "Veuillez renseigner l''adresse de l'utilisateur")
    private AdresseDto adresse;

    @JsonIgnore
    private String photo;

    @JsonIgnore
    private EntrepriseDto entreprise;
    @JsonIgnore
    private List<RolesDto> roles;



    public static UtilisateurDto fromEntity(Utilisateur utilisateur) {

        if (utilisateur == null) {
            return null;
        }
        return UtilisateurDto.builder()
                .id(utilisateur.getId())
                .nom(utilisateur.getNom())
                .prenom(utilisateur.getPrenom())
                .email(utilisateur.getEmail())
                .dateDeNaissance(utilisateur.getDateDeNaissance())
                .build();

    }

    public static Utilisateur toEntity(UtilisateurDto utilisateurDto) {

        if (utilisateurDto == null) {
            return null;
        }
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(utilisateurDto.getId());
        utilisateur.setNom(utilisateurDto.getNom());
        utilisateur.setPrenom(utilisateurDto.getPrenom());
        utilisateur.setEmail(utilisateurDto.getEmail());
        utilisateur.setDateDeNaissance(utilisateurDto.getDateDeNaissance());

        return utilisateur;
    }

}
