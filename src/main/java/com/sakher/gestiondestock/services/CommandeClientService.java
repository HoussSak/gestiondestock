package com.sakher.gestiondestock.services;


import com.sakher.gestiondestock.dto.CommandeClientDto;

import java.util.List;

public interface CommandeClientService {

    CommandeClientDto save (CommandeClientDto commandeClientDto);

    CommandeClientDto findById(Integer id);


    CommandeClientDto findByCode(String nom);


    List<CommandeClientDto> findAll();

    void delete(Integer id);
}
