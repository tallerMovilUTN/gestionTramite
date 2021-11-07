package com.gestion.tramite.entidad;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "codpospro")
@Data
public class CodPosPro {

    @EmbeddedId
    private CodPosProId id;

    private String desCorta;

    private String descripcion;

    private Integer activo;


}
