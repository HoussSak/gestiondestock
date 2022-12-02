package com.sakher.gestiondestock.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "fournisseur")
public class Fournisseur extends AbstractEntity{

    @Column(name = "nom")
    private String nom;
    @Column(name = "prenom")

    private String prenom;

    @Embedded
    private Adresse asresse;

    @Column(name = "photo")
    private String photo;

    @Column(name = "mail")
    private String mail;

    @Column(name = "numtel")
    private String numTel;

    @Column(name = "identreprise")
    private Integer idEntreprise;

    @OneToMany(mappedBy = "fournisseur")
    private List<CommandeFournisseur> commandeFournisseurs;
}
