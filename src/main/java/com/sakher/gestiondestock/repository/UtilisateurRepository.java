package com.sakher.gestiondestock.repository;

import com.sakher.gestiondestock.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtilisateurRepository extends JpaRepository<Utilisateur,Integer > {

    Optional<Utilisateur> findByCode(String code);
}
