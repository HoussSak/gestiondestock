package com.sakher.gestiondestock.dto;

import com.sakher.gestiondestock.model.Adresse;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Builder
@Data
public class AdresseDto {

    @NotBlank(message = "Le champ adresse1 est obligatoire")
    private String adresse1;

    private String adresse2;

    @NotBlank(message = "Le champ ville est obligatoire")
    private String ville;

    @NotBlank(message = "Le champ code postal est obligatoire")
    @Size(min = 4,max = 5,message = "le code postal est invalide ! ")
    private String codePostal;

    @NotBlank(message = "Le champ pays est obligatoire")
    private String pays;

    public AdresseDto fromEntity(Adresse adresse)  {
        if (adresse == null) {
            return null;
        }

        return AdresseDto.builder()
                .adresse1(adresse.getAdresse1())
                .adresse2(adresse.getAdresse2())
                .codePostal(adresse.getCodePostal())
                .ville(adresse.getVille())
                .pays(adresse.getPays())
                .build();
    }

    public  Adresse toEntity(AdresseDto adresseDto) {
        if (adresseDto == null) {
            return null;
        }
        Adresse adresse = new Adresse();

        adresse.setAdresse1(adresseDto.getAdresse1());
        adresse.setAdresse2(adresseDto.getAdresse2());
        adresse.setCodePostal(adresseDto.getCodePostal());
        adresse.setVille(adresseDto.getVille());
        adresse.setPays(adresseDto.getPays());

        return adresse;
    }
}
