package com.sakher.gestiondestock.services;

import com.sakher.gestiondestock.dto.CommandeFournisseurDto;

import java.util.List;

public interface CommandeFournisseurService {

    CommandeFournisseurDto save (CommandeFournisseurDto commandeFournisseurDto);

    CommandeFournisseurDto findById(Integer id);


    CommandeFournisseurDto findByCode(String nom);


    List<CommandeFournisseurDto> findAll();

    void delete(Integer id);
}
