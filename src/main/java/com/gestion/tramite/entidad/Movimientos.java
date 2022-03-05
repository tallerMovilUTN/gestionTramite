package com.gestion.tramite.entidad;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "movimientos")
@Data
public class Movimientos {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private Long id;

    private String tipoMovimiento;
    private Integer nroCuota;
    private String concepto;

    private double importe;

    private Date fechaVencimiento;
    private Date fechaMovimiento;
    private Date fechaPago;

    private String urlArchivo;

    private Integer notificado;

    private Integer estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idContabilidad")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Contabilidad contabilidad;



}
