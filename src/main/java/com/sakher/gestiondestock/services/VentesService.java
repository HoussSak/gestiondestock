package com.sakher.gestiondestock.services;


import com.sakher.gestiondestock.dto.VentesDto;

import java.util.List;

public interface VentesService {

    VentesDto save (VentesDto ventesDto);

    VentesDto findById(Integer id);


    VentesDto findByCode(String nom);


    List<VentesDto> findAll();

    void delete(Integer id);
}
