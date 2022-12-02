package com.sakher.gestiondestock.services.impl;

import com.sakher.gestiondestock.dto.FournisseurDto;
import com.sakher.gestiondestock.exception.EntityNotFoundException;
import com.sakher.gestiondestock.exception.ErrorCodes;
import com.sakher.gestiondestock.model.Fournisseur;
import com.sakher.gestiondestock.repository.FournisseurRepository;
import com.sakher.gestiondestock.services.FournisseurService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class FournisseurServiceImpl implements FournisseurService {

    private FournisseurRepository fournisseurRepository;

    @Override
    public FournisseurDto save(FournisseurDto fournisseurDto) {

        return FournisseurDto.fromEntity(fournisseurRepository.save(FournisseurDto.toEntity(fournisseurDto)));
    }

    @Override
    public FournisseurDto findById(Integer id) {

        if (id == null) {
            log.error("Fournisseur ID is null");
            return null;
        }

        Optional<Fournisseur> fournisseur = fournisseurRepository.findById(id);
        FournisseurDto fournisseurDto = FournisseurDto.fromEntity(fournisseur.get());

        return Optional.of(fournisseurDto).orElseThrow(()->
                new EntityNotFoundException("Fournisseur with ID= "+id+" not found", ErrorCodes.FOURNISSEUR_NOT_FOUND)
                );
    }

    @Override
    public FournisseurDto findByNom(String nom) {
        if (nom == null) {
            log.error("Fournisseur nom is null");
            return null;
        }

        Optional<Fournisseur> fournisseur = fournisseurRepository.findByNom(nom);
        FournisseurDto fournisseurDto = FournisseurDto.fromEntity(fournisseur.get());

        return Optional.of(fournisseurDto).orElseThrow(()->
                new EntityNotFoundException("Fournisseur with ID= "+nom+" not found", ErrorCodes.FOURNISSEUR_NOT_FOUND)
        );
    }

    @Override
    public List<FournisseurDto> findAll() {
        return fournisseurRepository.findAll().stream()
                .map(FournisseurDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {

        fournisseurRepository.deleteById(id);

    }
}
