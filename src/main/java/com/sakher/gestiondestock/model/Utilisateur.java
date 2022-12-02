package com.sakher.gestiondestock.model;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "utilisateur")
public class Utilisateur extends AbstractEntity{

    private String code;

    private String nom;

    private String prenom;

    private String email;

    @Column(name = "datedenaissance")
    private Instant dateDeNaissance;

    @Embedded
    private Adresse adresse;

    private String photo;

    @ManyToOne
    @JoinColumn(name = "identreprise")
    private Entreprise entreprise;

    @OneToMany(mappedBy = "utilisateur")
    private List<Roles> roles;

}
