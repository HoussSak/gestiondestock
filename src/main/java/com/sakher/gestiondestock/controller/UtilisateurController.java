package com.sakher.gestiondestock.controller;

import com.sakher.gestiondestock.controller.api.UtilisateurApi;
import com.sakher.gestiondestock.dto.UtilisateurDto;
import com.sakher.gestiondestock.exception.ErrorCodes;
import com.sakher.gestiondestock.exception.InvalidEntityException;
import com.sakher.gestiondestock.services.UtilisateurService;
import lombok.AllArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class UtilisateurController implements UtilisateurApi {

    private UtilisateurService utilisateurService;

    @Override
    public UtilisateurDto save(UtilisateurDto utilisateurDto, BindingResult result) {
        List<String> errors = result.getFieldErrors().stream()
                .map(fieldError -> fieldError.getDefaultMessage())
                .collect(Collectors.toList());

        if (!errors.isEmpty()) {
            throw  new InvalidEntityException("Utilisateur not valid", ErrorCodes.FOURNISSEUR_NOT_VALID,errors);
        }
        return utilisateurService.save(utilisateurDto);
    }

    @Override
    public UtilisateurDto findById(Integer id) {
        return utilisateurService.findById(id);
    }

    @Override
    public UtilisateurDto findByCodeUtilisateur(String code) {
        return utilisateurService.findByCode(code);
    }

    @Override
    public void delete(Integer id) {

        utilisateurService.delete(id);

    }
}
