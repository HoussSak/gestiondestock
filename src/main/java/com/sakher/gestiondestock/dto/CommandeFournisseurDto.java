package com.sakher.gestiondestock.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sakher.gestiondestock.model.CommandeFournisseur;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
@Builder
public class CommandeFournisseurDto {
    private Integer id;


    private String code;

    private Instant dateCommande;

    private FournisseurDto fournisseur;

    private List<LigneCommandeFournisseurDto> ligneCommandeFournisseurs;

    private Integer idEntreprise;




    public static CommandeFournisseurDto fromEntity(CommandeFournisseur commandeFournisseur) {

        if (commandeFournisseur == null) {
            return null;
        }
        return CommandeFournisseurDto.builder()
                .id(commandeFournisseur.getId())
                .dateCommande(commandeFournisseur.getDateCommande())
                .code(commandeFournisseur.getCode())
                .idEntreprise(commandeFournisseur.getIdEntreprise())
                .build();

    }

    public static CommandeFournisseur toEntity(CommandeFournisseurDto commandeFournisseurDto) {

        if (commandeFournisseurDto == null) {
            return null;
        }
        CommandeFournisseur commandeFournisseur = new CommandeFournisseur();
        commandeFournisseur.setId(commandeFournisseurDto.getId());
        commandeFournisseur.setDateCommande(commandeFournisseurDto.getDateCommande());
        commandeFournisseur.setIdEntreprise(commandeFournisseurDto.getIdEntreprise());
        commandeFournisseur.setCode(commandeFournisseurDto.getCode());

        return commandeFournisseur;
    }
}
