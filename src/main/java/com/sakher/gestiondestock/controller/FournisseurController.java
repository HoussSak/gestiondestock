package com.sakher.gestiondestock.controller;

import com.sakher.gestiondestock.controller.api.FournisseurApi;
import com.sakher.gestiondestock.dto.FournisseurDto;
import com.sakher.gestiondestock.exception.ErrorCodes;
import com.sakher.gestiondestock.exception.InvalidEntityException;
import com.sakher.gestiondestock.services.FournisseurService;
import lombok.AllArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class FournisseurController implements FournisseurApi {

    private FournisseurService fournisseurService;
    @Override
    public FournisseurDto save(FournisseurDto fournisseurDto, BindingResult result) {
        List<String> errors = result.getFieldErrors().stream()
                .map(fieldError -> fieldError.getDefaultMessage())
                .collect(Collectors.toList());

        if (!errors.isEmpty()) {

            throw  new InvalidEntityException("Fournisseur is not valid", ErrorCodes.FOURNISSEUR_NOT_VALID,errors);
        }

        return fournisseurService.save(fournisseurDto) ;
    }

    @Override
    public FournisseurDto findById(Integer id) {
        return fournisseurService.findById(id);
    }

    @Override
    public FournisseurDto findByNom(String nom) {
        return fournisseurService.findByNom(nom);
    }

    @Override
    public List<FournisseurDto> findAll() {
        return fournisseurService.findAll();
    }

    @Override
    public void deleteById(Integer id) {

        fournisseurService.delete(id);

    }
}
