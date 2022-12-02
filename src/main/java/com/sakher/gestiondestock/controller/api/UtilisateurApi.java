package com.sakher.gestiondestock.controller.api;

import com.sakher.gestiondestock.dto.UtilisateurDto;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.sakher.gestiondestock.utils.Constants.APP_ROOT;

public interface UtilisateurApi {

    @PostMapping(value = APP_ROOT+"/utilisateur/create",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    UtilisateurDto save(@Valid @RequestBody UtilisateurDto utilisateurDto, BindingResult result);

    @GetMapping(value = APP_ROOT+"/utilisateur/id/{idUtilisateur}",produces = MediaType.APPLICATION_JSON_VALUE)
    UtilisateurDto findById(@PathVariable("idUtilisateur") Integer id);

    @GetMapping(value = APP_ROOT+"/utilisateur/code/{code}",produces =MediaType.APPLICATION_JSON_VALUE )
    UtilisateurDto findByCodeUtilisateur(@PathVariable String code);

    @DeleteMapping(value = APP_ROOT+"/utilisateur/delete/{idUtilisateur}",produces = MediaType.APPLICATION_JSON_VALUE)
    void delete(@PathVariable("idUtilisateur") Integer id );
}
