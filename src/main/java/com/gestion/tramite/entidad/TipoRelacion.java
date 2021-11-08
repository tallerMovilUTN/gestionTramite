package com.gestion.tramite.entidad;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "tiporelacion")
@Data
public class TipoRelacion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String descripcion;

}
