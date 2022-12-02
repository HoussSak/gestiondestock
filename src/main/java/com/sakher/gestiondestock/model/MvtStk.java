package com.sakher.gestiondestock.model;

import com.fasterxml.jackson.annotation.JacksonInject;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "mvtstk")
public class MvtStk extends AbstractEntity{

    @ManyToOne
    @JoinColumn(name = "idarticle")
    private Article article;

    @Column(name = "datemvt")
    private Instant dateMvt;

    private BigDecimal quantite;


    @Column(name = "identreprise")
    private Integer idEntreprise;

}
