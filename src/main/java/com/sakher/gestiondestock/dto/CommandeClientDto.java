package com.sakher.gestiondestock.dto;

import com.sakher.gestiondestock.model.CommandeClient;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;
@Data
@Builder
public class CommandeClientDto {
    private Integer id;

    private String code;

    private Instant dateCommande;


    private ClientDto client;

    private List<LigneCommandeClientDto> ligneCommandeClients;

    private Integer idEntreprise;



    public static CommandeClientDto fromEntity(CommandeClient commandeClient) {

        if (commandeClient == null) {
            return null;
        }
        return CommandeClientDto.builder()
                .id(commandeClient.getId())
                .dateCommande(commandeClient.getDateCommande())
                .code(commandeClient.getCode())
                .idEntreprise(commandeClient.getIdEntreprise())
                .build();

    }

    public static CommandeClient toEntity(CommandeClientDto commandeClientDto) {

        if (commandeClientDto == null) {
            return null;
        }
        CommandeClient commandeClient = new CommandeClient();
        commandeClient.setId(commandeClientDto.getId());
        commandeClient.setDateCommande(commandeClientDto.getDateCommande());
        commandeClient.setIdEntreprise(commandeClientDto.getIdEntreprise());
        commandeClient.setCode(commandeClientDto.getCode());

        return commandeClient;
    }
}

