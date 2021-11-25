package com.gestion.tramite.entidad;


import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tipoTramite")
@Data
public class TipoTramite
{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private Long id;
    private String tipo;
    private String descripcion;
    private double presupuesto;
    private Date fechaAlta;
    private Integer estado;



}
