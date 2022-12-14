package com.sakher.gestiondestock.repository;

import com.sakher.gestiondestock.model.Category;
import com.sakher.gestiondestock.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Integer> {

    Optional<Client> findByNom(String nom);
}
