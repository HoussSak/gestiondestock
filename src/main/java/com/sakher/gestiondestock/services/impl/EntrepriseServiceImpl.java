package com.sakher.gestiondestock.services.impl;

import com.sakher.gestiondestock.dto.EntrepriseDto;
import com.sakher.gestiondestock.exception.EntityNotFoundException;
import com.sakher.gestiondestock.exception.ErrorCodes;
import com.sakher.gestiondestock.model.Entreprise;
import com.sakher.gestiondestock.repository.EntrepriseRepository;
import com.sakher.gestiondestock.services.EntrepriseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EntrepriseServiceImpl implements EntrepriseService{

    @Autowired
    private EntrepriseRepository entrepriseRepository;

    @Override
    public EntrepriseDto save(EntrepriseDto entrepriseDto) {

        return EntrepriseDto.fromEntity(entrepriseRepository.save(EntrepriseDto.toEntity(entrepriseDto)));
    }

    @Override
    public EntrepriseDto findById(Integer id) {
        if (id == null) {
            log.error("Entreprise ID is null");
            return null;
        }
        Optional<Entreprise> entreprise =entrepriseRepository.findById(id);
        EntrepriseDto entrepriseDto = EntrepriseDto.fromEntity(entreprise.get());


        return Optional.of(entrepriseDto).orElseThrow(()->
                new EntityNotFoundException("Entreprise with ID= "+id+" not found", ErrorCodes.ENTREPRISE_NOT_FOUND));
    }

    @Override
    public EntrepriseDto findByNom(String nom) {
        if (nom == null) {
            log.error("Entreprise name is null");
            return null;
        }
        Optional<Entreprise> entreprise =entrepriseRepository.findByNom(nom);
        EntrepriseDto entrepriseDto = EntrepriseDto.fromEntity(entreprise.get());


        return Optional.of(entrepriseDto).orElseThrow(()->
                new EntityNotFoundException("Entreprise with NOM= "+nom+" not found", ErrorCodes.ENTREPRISE_NOT_FOUND));
    }

    @Override
    public List<EntrepriseDto> findAll() {
        return entrepriseRepository.findAll().stream()
                .map(EntrepriseDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            log.error("Entreprise ID is null");
        }
        entrepriseRepository.deleteById(id);

    }
}
