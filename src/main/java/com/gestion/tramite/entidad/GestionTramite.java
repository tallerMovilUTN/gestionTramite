package com.gestion.tramite.entidad;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "gestiontramite")
@Data
public class GestionTramite {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPersona")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Persona persona;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idTipoTramite")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private TipoTramite tipoTramite;

    private Integer estado;
    private String comentario;
    private Date fechaAlta;
    private Date fechaCambioEstado;

}
