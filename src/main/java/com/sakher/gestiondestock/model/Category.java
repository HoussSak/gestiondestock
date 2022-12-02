package com.sakher.gestiondestock.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "categorie")
public class Category extends AbstractEntity{

    @Column(name = "code")
    private String code;

    private String designation;

    @Column(name = "identreprise")
    private Integer idEntreprise;

    @OneToMany(mappedBy = "category")
    private List <Article> articles;


}
