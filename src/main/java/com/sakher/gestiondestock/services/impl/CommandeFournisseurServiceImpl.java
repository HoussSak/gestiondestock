package com.sakher.gestiondestock.services.impl;

import com.sakher.gestiondestock.dto.CommandeFournisseurDto;
import com.sakher.gestiondestock.dto.LigneCommandeFournisseurDto;
import com.sakher.gestiondestock.exception.EntityNotFoundException;
import com.sakher.gestiondestock.exception.ErrorCodes;
import com.sakher.gestiondestock.exception.InvalidEntityException;
import com.sakher.gestiondestock.model.*;
import com.sakher.gestiondestock.repository.*;
import com.sakher.gestiondestock.services.CommandeFournisseurService;
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
public class CommandeFournisseurServiceImpl implements CommandeFournisseurService {

    private CommandeFournisseurRepository commandeFournisseurRepository;
    private FournisseurRepository fournisseurRepository;
    private LigneCommandeFournisseurRepository ligneCommandeFournisseurRepository;
    private ArticleRepository articleRepository;
    @Override
    public CommandeFournisseurDto save(CommandeFournisseurDto commandeFournisseurDto) {

        Optional<Fournisseur> fournisseur = fournisseurRepository.findById(commandeFournisseurDto.getFournisseur().getId());

        if (fournisseur.isEmpty()) {
            log.warn("Fournisseur with ID {} not found.",commandeFournisseurDto.getFournisseur().getId());
            throw new EntityNotFoundException("Aucun fournisseur avec ID = "+commandeFournisseurDto.getFournisseur().getId()+" n'as été trouvé dand la BDD");
        }
        List<String> articleErrors = new ArrayList<>();

        if (commandeFournisseurDto.getLigneCommandeFournisseurs() != null) {
            commandeFournisseurDto.getLigneCommandeFournisseurs().forEach(cmdFrd ->{
                if(cmdFrd.getArticle() != null) {
                    Optional<Article> article = articleRepository.findById(cmdFrd.getArticle().getId());
                    if (article.isEmpty()) {
                        articleErrors.add("L'article avec l'ID "+cmdFrd.getArticle().getId()+" n'existe pas");
                    }
                } else  {
                    articleErrors.add("Impossible d'enregistrer une commande avec un article NULL");

                }
            });
        }
        if (!articleErrors.isEmpty()) {
            log.warn("");
            throw  new InvalidEntityException("Article n'existe pas dans la BDD", ErrorCodes.ARTICLE_NOT_FOUND,articleErrors);
        }
        CommandeFournisseur savecCmdFrs = CommandeFournisseurDto.toEntity(commandeFournisseurDto);

            commandeFournisseurDto.getLigneCommandeFournisseurs().forEach(ligneCmdFrs-> {
            LigneCommandeFournisseur ligneCommandeFournisseur = LigneCommandeFournisseurDto.toEntity(ligneCmdFrs);
            ligneCommandeFournisseur.setCommandeFournisseur(savecCmdFrs);
            ligneCommandeFournisseurRepository.save(ligneCommandeFournisseur);
        });

        return CommandeFournisseurDto.fromEntity(savecCmdFrs);
    }

    @Override
    public CommandeFournisseurDto findById(Integer id) {
        if (id == null) {
            log.error("La Commande Fournisseur est NULL");
            return null;
        }
        return commandeFournisseurRepository.findById(id)
                .map(CommandeFournisseurDto::fromEntity)
                .orElseThrow(()->
                        new EntityNotFoundException("Commande fournisseur avec l'ID = "+id+" n'existe pas dans la BDD"
                                ,ErrorCodes.COMMANDE_FOURNISSEUR_NOT_FOUND)
                );
    }

    @Override
    public CommandeFournisseurDto findByCode(String code) {
        if (code == null) {
            log.error("Commande code is NULL");

            return null;
        }
        return commandeFournisseurRepository.findCommandeFournisseurByCode(code)
                .map(CommandeFournisseurDto::fromEntity)
                .orElseThrow(()->
                        new EntityNotFoundException("Commande fournisseur avc le code  = "+code+" n'existe pas dans la BDD",
                                ErrorCodes.COMMANDE_FOURNISSEUR_NOT_FOUND));
    }

    @Override
    public List<CommandeFournisseurDto> findAll() {

        return commandeFournisseurRepository.findAll().stream()
                .map(CommandeFournisseurDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null ) {
            log.error("Commande ID in NULL");
        }

        commandeFournisseurRepository.deleteById(id);

    }
}
