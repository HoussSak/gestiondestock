package com.sakher.gestiondestock.services.impl;

import com.sakher.gestiondestock.dto.ClientDto;
import com.sakher.gestiondestock.exception.EntityNotFoundException;
import com.sakher.gestiondestock.exception.ErrorCodes;
import com.sakher.gestiondestock.model.Client;
import com.sakher.gestiondestock.repository.ClientRepository;
import com.sakher.gestiondestock.services.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Slf4j
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public ClientDto save(ClientDto clientDto) {
        return ClientDto.fromEntity(clientRepository.save(ClientDto.toEntity(clientDto)));
    }

    @Override
    public ClientDto findById(Integer id) {

        if (id==null) {
            log.error("Client ID is null");
            return null;
        }
        Optional<Client> client = clientRepository.findById(id);
        ClientDto clientDto = ClientDto.fromEntity(client.get());

        return Optional.of(clientDto).orElseThrow(()->
                new EntityNotFoundException("Client with ID= "+id+" not found", ErrorCodes.CLIENT_NOT_FOUND)
                );
    }

    @Override
    public ClientDto findByNom(String nom) {
        if (nom == null) {
            log.error("Client name is null");
            return null;
        }
        Optional<Client> client = clientRepository.findByNom(nom);
        ClientDto clientDto = ClientDto.fromEntity(client.get());


        return Optional.of(clientDto).orElseThrow(()->
                new EntityNotFoundException("Client with name = "+nom+" not found",ErrorCodes.CLIENT_NOT_FOUND)
        );
    }

    @Override
    public List<ClientDto> findAll() {

        return clientRepository.findAll().stream()
                .map(ClientDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            log.error("Client id is null");
        }
        clientRepository.deleteById(id);
    }
}
