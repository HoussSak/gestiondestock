package com.sakher.gestiondestock.services.impl;

import com.sakher.gestiondestock.dto.LigneVenteDto;
import com.sakher.gestiondestock.dto.VentesDto;
import com.sakher.gestiondestock.exception.EntityNotFoundException;
import com.sakher.gestiondestock.exception.ErrorCodes;
import com.sakher.gestiondestock.exception.InvalidEntityException;
import com.sakher.gestiondestock.model.Article;
import com.sakher.gestiondestock.model.LigneVente;
import com.sakher.gestiondestock.model.Ventes;
import com.sakher.gestiondestock.repository.ArticleRepository;
import com.sakher.gestiondestock.repository.LigneVenteRepository;
import com.sakher.gestiondestock.repository.VentesRepository;
import com.sakher.gestiondestock.services.VentesService;
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
public class VentesServiceImpl implements VentesService {

    private ArticleRepository articleRepository;
    private VentesRepository ventesRepository;
    private LigneVenteRepository ligneVenteRepository;


    @Override
    public VentesDto save(VentesDto ventesDto) {

        List<String> articleErrors = new ArrayList<>();

        ventesDto.getLigneVentes().forEach(ligneVenteDto -> {
            Optional<Article> article = articleRepository.findById(ligneVenteDto.getArticle().getId());

            if (article.isEmpty()) {
                articleErrors.add("Aucun article avec L'ID "+ligneVenteDto.getArticle().getId()+" n'as pas été trouvé dans la BDD");
            }
        });

        if (!articleErrors.isEmpty()) {
            log.error("One oerrr more articles were not found in DB");
            throw  new InvalidEntityException("Article n'existe pas dans la BDD", ErrorCodes.ARTICLE_NOT_FOUND,articleErrors);
        }

        Ventes savedVentes = ventesRepository.save(VentesDto.toEntity(ventesDto));
        ventesDto.getLigneVentes().forEach(ligneVenteDto -> {
            LigneVente ligneVente = LigneVenteDto.toEntity(ligneVenteDto);
            ligneVente.setVentes(savedVentes);
            ligneVenteRepository.save(ligneVente);
        });
        return VentesDto.fromEntity(savedVentes);
    }

    @Override
    public VentesDto findById(Integer id) {
        if (id == null) {
            log.error("La vente ID est NULL");
            return null;
        }
        return ventesRepository.findById(id)
                .map(VentesDto::fromEntity)
                .orElseThrow(()->
                        new EntityNotFoundException("Vente avec l'ID = "+id+" n'existe pas dans la BDD"
                                ,ErrorCodes.VENTE_NOT_FOUND)
                );
    }

    @Override
    public VentesDto findByCode(String code) {
        if (code == null) {
            log.error("La vente code est NULL");
            return null;
        }
        return ventesRepository.findVentesByCode(code)
                .map(VentesDto::fromEntity)
                .orElseThrow(()->
                        new EntityNotFoundException("Vente avec le code = "+code+" n'existe pas dans la BDD"
                                ,ErrorCodes.VENTE_NOT_FOUND)
                );
    }

    @Override
    public List<VentesDto> findAll() {
        return ventesRepository.findAll().stream()
                .map(VentesDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {

        if (id==null) {
            log.error("Vente ID is NULL");
            return;
        }
        ventesRepository.deleteById(id);

    }
}
