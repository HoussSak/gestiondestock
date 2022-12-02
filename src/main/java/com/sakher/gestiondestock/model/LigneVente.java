package com.sakher.gestiondestock.model;

import com.sakher.gestiondestock.dto.ArticleDto;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "lignevente")
public class LigneVente extends AbstractEntity{


    @ManyToOne
    @JoinColumn(name = "idventes")
    private Ventes ventes;

   private BigDecimal quantite;

   @Column(name = "prixunitaire")
   private BigDecimal prixUnitaire;

    @Column(name = "identreprise")
    private Integer idEntreprise;

    @ManyToOne
    @JoinColumn(name = "idarticle")
    private Article article;


}
