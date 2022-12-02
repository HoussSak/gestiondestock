package com.sakher.gestiondestock.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sakher.gestiondestock.model.Client;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;


@Data
@Builder
public class ClientDto {
    private Integer id;

    @NotBlank(message = "Veuillez renseigner le nom du client")
    private String nom;

    @NotBlank(message = "Veuillez renseigner le prenom du client")
    private String prenom;

    private AdresseDto adresse;

    private String photo;

    @NotBlank(message = "Veuillez renseigner l'adresse mail du client")
    @Email(message = "Veuillez saisir une adresse \\u00e9lectronique correcte")
    private String mail;

    @NotBlank(message = "Veuillez renseigner le numero téléphone du client")
    @Size(min = 10, max = 13,message = "le numero téléphone du client est incorrecte")
    private String numTel;

    @JsonIgnore
    private List<CommandeClientDto> commandeClients;
    private Integer idEntreprise;


    public static ClientDto fromEntity(Client client) {

        if (client == null) {
            return null;
        }
        return ClientDto.builder()
                .id(client.getId())
                .nom(client.getNom())
                .prenom(client.getPrenom())
                .photo(client.getPhoto())
                .mail(client.getMail())
                .numTel(client.getNumTel())
                .idEntreprise(client.getIdEntreprise())
                .build();

    }

    public static Client toEntity(ClientDto clientDto) {

        if (clientDto == null) {
            return null;
        }

        Client client = new Client();
        client.setId(clientDto.getId());
        client.setNom(clientDto.getNom());
        client.setPrenom(clientDto.getPrenom());
        client.setPhoto(clientDto.getPhoto());
        client.setMail(clientDto.getMail());
        client.setIdEntreprise(clientDto.getIdEntreprise());
        client.setNumTel(clientDto.getNumTel());


        return client;
    }
}
