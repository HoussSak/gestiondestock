package com.sakher.gestiondestock.controller;

import com.sakher.gestiondestock.controller.api.ClientApi;
import com.sakher.gestiondestock.dto.ClientDto;
import com.sakher.gestiondestock.exception.ErrorCodes;
import com.sakher.gestiondestock.exception.InvalidEntityException;
import com.sakher.gestiondestock.services.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class ClientController implements ClientApi {

    private ClientService clientService;


    @Override
    public ClientDto save(@Valid @RequestBody ClientDto clientDto, BindingResult result) {

        List<String> errors = result.getFieldErrors().stream()
                .map(fieldError -> fieldError.getDefaultMessage())
                .collect(Collectors.toList());

        if (!errors.isEmpty()) {
            System.out.println(errors);

            throw new InvalidEntityException("Client is not valid", ErrorCodes.CLIENT_NOT_VALID,errors);
        }

        return clientService.save(clientDto);
    }

    @Override
    public ClientDto findById(Integer id) {return clientService.findById(id);}

    @Override
    public ClientDto findByNom(String nom) {return clientService.findByNom(nom);}

    @Override
    public List<ClientDto> findAll() {return clientService.findAll();}

    @Override
    public void deleteById(Integer id) {clientService.delete(id);}
}
