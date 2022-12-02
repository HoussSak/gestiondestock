package com.sakher.gestiondestock.services.impl;

import com.sakher.gestiondestock.dto.UtilisateurDto;
import com.sakher.gestiondestock.exception.ErrorCodes;
import com.sakher.gestiondestock.model.Utilisateur;
import com.sakher.gestiondestock.repository.UtilisateurRepository;
import com.sakher.gestiondestock.services.UtilisateurService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.sakher.gestiondestock.exception.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class UtilisateurServiceImpl implements UtilisateurService {

    private UtilisateurRepository utilisateurRepository;
    @Override
    public UtilisateurDto save(UtilisateurDto utilisateurDto) {
        return UtilisateurDto.fromEntity(utilisateurRepository.save(UtilisateurDto.toEntity(utilisateurDto)));
    }

    @Override
    public UtilisateurDto findById(Integer id) {

        if (id==null) {
            log.error("Utilisateur ID is null");
            return null;
        }

        Optional<Utilisateur> utilisateur = utilisateurRepository.findById(id);

        UtilisateurDto utilisateurDto = UtilisateurDto.fromEntity(utilisateur.get());

        return Optional.of(utilisateurDto).orElseThrow(()->
               new  EntityNotFoundException("Utilisateur with ID = "+id+" not found", ErrorCodes.UTILISATEUR_NOT_FOUND));
    }

    @Override
    public UtilisateurDto findByCode(String code) {

        if (code == null) {
            log.error("utilisateur ID is null");

            return  null;
        }

        Optional<Utilisateur> utilisateur = utilisateurRepository.findByCode(code);

        UtilisateurDto utilisateurDto = UtilisateurDto.fromEntity(utilisateur.get());

        return Optional.of(utilisateurDto).orElseThrow(()->
                new  EntityNotFoundException("Utilisateur with code = "+code+" not found", ErrorCodes.UTILISATEUR_NOT_FOUND));
    }

    @Override
    public List<UtilisateurDto> findAll() {

        List<UtilisateurDto> utilisateurs = utilisateurRepository.findAll().stream()
                .map(UtilisateurDto::fromEntity).
                collect(Collectors.toList());
        return utilisateurs;
    }

    @Override
    public void delete(Integer id) {

        if (id == null) {
            log.error("Utilisateur with ID = "+id+" not found ");
        }
        utilisateurRepository.deleteById(id);

    }
}
