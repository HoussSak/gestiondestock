package com.sakher.gestiondestock.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "entreprises")
public class Entreprise extends AbstractEntity {

    private String nom;

    private String description;

    @Embedded
    private Adresse adresse;

    private String photo;

    private String email;

    @Column(name = "numtel")
    private String numTel;

    @Column(name = "siteweb")
    private String siteWeb;

    @OneToMany(mappedBy = "entreprise")
    private List<Utilisateur> utilisateurs;
}
