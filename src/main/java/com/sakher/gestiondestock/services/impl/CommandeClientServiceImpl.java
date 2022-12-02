package com.sakher.gestiondestock.services.impl;

import com.sakher.gestiondestock.dto.CommandeClientDto;
import com.sakher.gestiondestock.dto.LigneCommandeClientDto;
import com.sakher.gestiondestock.exception.EntityNotFoundException;
import com.sakher.gestiondestock.exception.ErrorCodes;
import com.sakher.gestiondestock.exception.InvalidEntityException;
import com.sakher.gestiondestock.model.Article;
import com.sakher.gestiondestock.model.Client;
import com.sakher.gestiondestock.model.CommandeClient;
import com.sakher.gestiondestock.model.LigneCommandeClient;
import com.sakher.gestiondestock.repository.ArticleRepository;
import com.sakher.gestiondestock.repository.ClientRepository;
import com.sakher.gestiondestock.repository.CommandeClientRepository;
import com.sakher.gestiondestock.repository.LigneCommandeClientRepository;
import com.sakher.gestiondestock.services.CommandeClientService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class CommandeClientServiceImpl implements CommandeClientService {

    private CommandeClientRepository commandeClientRepository;
    private LigneCommandeClientRepository ligneCommandeClientRepository;
    private ClientRepository clientRepository;
    private ArticleRepository articleRepository;


    @Override
    public CommandeClientDto save(CommandeClientDto commandeClientDto) {

        Optional<Client> client = clientRepository.findById(commandeClientDto.getClient().getId());
        if (client.isEmpty()) {
            log.warn("Client with ID {} was not found",commandeClientDto.getClient().getId());
            throw  new EntityNotFoundException("Aucun client avec l'ID"+commandeClientDto.getClient().getId()+"n'as été trouvé dans la BDD", ErrorCodes.CLIENT_NOT_FOUND);
        }
        List<String> articleErrors = new ArrayList<>();

        if (commandeClientDto.getLigneCommandeClients() !=null) {
            commandeClientDto.getLigneCommandeClients().forEach(ligneCmdClt-> {
                if(ligneCmdClt.getArticle() != null) {
                    Optional<Article> article = articleRepository.findById(ligneCmdClt.getArticle().getId());
                    if (article.isEmpty()) {
                        articleErrors.add("L'article avec l'ID "+ligneCmdClt.getArticle().getId()+" n'existe pas");
                    }
                } else  {
                    articleErrors.add("Impossible d'enregistrer une commande avec un article NULL");

                }
            });
        }
        if (!articleErrors.isEmpty()) {
            log.warn("");
            throw  new InvalidEntityException("Article n'existe pas dans la BDD",ErrorCodes.ARTICLE_NOT_FOUND,articleErrors);
        }

        CommandeClient savedCmdClt  = commandeClientRepository.save(CommandeClientDto.toEntity(commandeClientDto));


            commandeClientDto.getLigneCommandeClients().forEach(ligneCmdClt -> {
                LigneCommandeClient ligneCommandeClient = LigneCommandeClientDto.toEntity(ligneCmdClt);
                ligneCommandeClient.setCommandeClient(savedCmdClt);
                ligneCommandeClientRepository.save(ligneCommandeClient);
            });


        return CommandeClientDto.fromEntity(savedCmdClt);
    }

    @Override
    public CommandeClientDto findById(Integer id) {
        if (id == null) {
            log.error("La commande client est NULL");
            return null;
        }
        return commandeClientRepository.findById(id)
                .map(CommandeClientDto::fromEntity)
                .orElseThrow(()->
                new EntityNotFoundException("Commande client avec l'ID = "+id+" n'existe pas dans la BDD"
                        ,ErrorCodes.COMMANDE_CLIENT_NOT_FOUND)
                );
    }

    @Override
    public CommandeClientDto findByCode(String code) {
        if (code == null) {
            log.error("Commande code is NULL");

            return null;

        }
        return commandeClientRepository.findCommandeClientByCode(code)
                .map(CommandeClientDto::fromEntity)
                .orElseThrow(()->
                        new EntityNotFoundException("Commande client avc le code  = "+code+" n'existe pas dans la BDD",
                                ErrorCodes.COMMANDE_CLIENT_NOT_FOUND));
    }

    @Override
    public List<CommandeClientDto> findAll() {
        return commandeClientRepository.findAll().stream()
                .map(CommandeClientDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            log.error("Commande ID is NULL");
            return;
    }
        commandeClientRepository.deleteById(id);
}
}
