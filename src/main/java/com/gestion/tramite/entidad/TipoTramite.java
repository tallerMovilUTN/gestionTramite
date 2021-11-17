package com.gestion.tramite.entidad;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tipoTramite")
@Data
public class TipoTramite
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String tipo;
    private String descripcion;
    private double presupuesto;
    private Date fechaAlta;
    private Integer estado;



}
