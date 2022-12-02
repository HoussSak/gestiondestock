package com.sakher.gestiondestock.controller.api;

import com.sakher.gestiondestock.dto.FournisseurDto;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static com.sakher.gestiondestock.utils.Constants.APP_ROOT;

public interface FournisseurApi {


    @PostMapping(value = APP_ROOT+"/fournisseur/create",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    FournisseurDto save(@Valid @RequestBody  FournisseurDto fournisseurDto, BindingResult result);

    @GetMapping(value = APP_ROOT+"/fournisseur/id/{idFournisseur}",produces = MediaType.APPLICATION_JSON_VALUE)
    FournisseurDto findById(@PathVariable("idFournisseur") Integer id);


    @GetMapping(value = APP_ROOT+"/fournisseur/nom/{nom}",produces = MediaType.APPLICATION_JSON_VALUE)
    FournisseurDto findByNom(@PathVariable String nom);

    @GetMapping(value = APP_ROOT+"/fournisseur/all",produces = MediaType.APPLICATION_JSON_VALUE)
    List<FournisseurDto> findAll();

    @DeleteMapping(APP_ROOT+"/fournisseur/delete/{idFournisseur}")
    void deleteById(@PathVariable("idFournisseur") Integer id);
}
