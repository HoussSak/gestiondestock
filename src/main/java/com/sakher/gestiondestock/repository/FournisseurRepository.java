package com.sakher.gestiondestock.repository;

import com.sakher.gestiondestock.model.Fournisseur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FournisseurRepository extends JpaRepository<Fournisseur, Integer> {

    Optional<Fournisseur> findByNom(String nom);
}
