package com.gestion.tramite.entidad;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class FiltroMovDTO 
{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private Long idContabilidad;

    private Integer dni;

    private String tipoTramite;

    private Integer estado;

    private Date fechaVtoDesde;

    private Date fechaVtoHasta;

    private Date fechaPagoDesde;

    private Date fechaPagoHasta;

}
