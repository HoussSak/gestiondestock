package com.sakher.gestiondestock.dto;

import com.sakher.gestiondestock.model.LigneVente;
import com.sakher.gestiondestock.model.Ventes;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
@Builder
public class VentesDto {
    private Integer id;

    private String code;

    private Instant dateVente;

    private String commentaire;

    private List<LigneVenteDto> ligneVentes;

    public static VentesDto fromEntity(Ventes ventes) {

        if (ventes == null) {
            return null;
        }
        return VentesDto.builder()
                .id(ventes.getId())
                .code(ventes.getCode())
                .dateVente(ventes.getDateVente())
                .commentaire(ventes.getCommentaire())
                .build();

    }

    public static Ventes toEntity(VentesDto ventesDto) {

        if (ventesDto == null) {
            return null;
        }
        Ventes ventes = new Ventes();
        ventes.setId(ventesDto.getId());
        ventes.setDateVente(ventes.getDateVente());
        ventes.setCode(ventesDto.getCode());
        ventes.setCommentaire(ventesDto.getCommentaire());

        return ventes;
    }
}
