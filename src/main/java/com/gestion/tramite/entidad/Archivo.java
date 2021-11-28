package com.gestion.tramite.entidad;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "archivos")
@Data
public class Archivo {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private Long id;
    private String nombre;
    private String url;
    private Integer dni;

    private Date fechaAlta;

    private Integer estado;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idDocumento")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Documento documento;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idGestionTramite")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private GestionTramite gestionTramite;



}
