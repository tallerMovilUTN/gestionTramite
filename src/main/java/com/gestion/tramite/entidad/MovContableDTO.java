package com.gestion.tramite.entidad;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class MovContableDTO {
    /*
select
        c.id idContabilidad,
        p.apellido, p.nombre, p.dni, t.tipo tipoTramite,
        m.nroCuota,c.importeTotal,c.diasVencimiento,c.concepto conceptoContable,c.cantidadCuotas,
        m.estado ,m.importe,m.fechaVencimiento,m.fechaPago,m.concepto conceptoMov

from contabilidad c,
    movimientos m,
    gestionTramite g,
    persona p,
    tipoTramite t
where m.idContabilidad = c.id
    and c.estado = 1
    and c.idGestionTramite=g.id
    and g.idPersona = p.id
    and g.idTipoTramite = t.id
    order by m.nroCuota
     */

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private Long idContabilidad;
    private Integer nroCuota;

    private String apellido;
    private String nombre;
    private Integer dni;
    private String tipoTramite;

    private double importeTotal;
    private Integer diasVencimiento;
    private String conceptoContable;
    private Integer cantidadCuotas;
    private Integer estado;
    private double importe;
    private Date fechaVencimiento;
    private Date fechaPago;
    private String conceptoMov;

}
