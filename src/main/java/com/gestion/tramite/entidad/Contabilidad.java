package com.gestion.tramite.entidad;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "contabilidad")
@Data
public class Contabilidad {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private Long id;

    private Integer cantidadCuotas;
    private Integer diasVencimiento;
    private double importeTotal;
    public String concepto;
    private Date fechaAlta;
    private Integer estado;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idGestionTramite")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private GestionTramite gestionTramite;

}
