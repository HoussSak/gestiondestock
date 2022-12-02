package com.sakher.gestiondestock.services;


import com.sakher.gestiondestock.dto.FournisseurDto;

import java.util.List;


public interface FournisseurService {
    
    
    FournisseurDto save( FournisseurDto fournisseurDto);

    FournisseurDto findById(Integer id);


    FournisseurDto findByNom(String nom);


    List<FournisseurDto> findAll();

    void delete(Integer id);
}
