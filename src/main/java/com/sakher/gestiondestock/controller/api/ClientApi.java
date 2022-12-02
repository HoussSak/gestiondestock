package com.sakher.gestiondestock.controller.api;

import com.sakher.gestiondestock.dto.ClientDto;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static com.sakher.gestiondestock.utils.Constants.APP_ROOT;

public interface ClientApi {


    @PostMapping(value = APP_ROOT+"/clients/create",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    ClientDto save(@Valid @RequestBody ClientDto clientDto, BindingResult result);

    @GetMapping(value = APP_ROOT+"/clients/id/{idClient}",produces = MediaType.APPLICATION_JSON_VALUE)
    ClientDto findById(@PathVariable("idClient") Integer id);

    @GetMapping(APP_ROOT+"/clients/nom/{nom}")
    ClientDto findByNom(@PathVariable String nom);


    @GetMapping(value = APP_ROOT+"/clients/all",produces = MediaType.APPLICATION_JSON_VALUE)
    List<ClientDto> findAll();


    @DeleteMapping(APP_ROOT+"/clients/delete/{idClient}")
    void deleteById(@PathVariable("idClient") Integer id);
}
