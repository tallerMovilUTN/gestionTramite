package com.gestion.tramite.entidad;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "provincias")
@Data
public class Provincias
{
    @Id
    private Integer id;

    private String desCorta;

    private String descripcion;

    private Integer estado;

}
