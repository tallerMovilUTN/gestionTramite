package com.gestion.tramite.entidad;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "contacto")
@Data
public class Contacto
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String apellido;
    private String nombre;
    private String email;
    private Date fechaNac;
    private String lugarNac;
    private Date fechaDefuncion;
    private String lugarDefuncion;
    private Integer dni;
    private String calleNombre;
    private Integer calleNro;
    private Integer cp;
    private String localidad;
    private String provincia;
    private Long celularNro;
    private Long telefonoParticular;
    private String estadocivil;
    private Date fechaMatrimonio;
    private String lugarMatrimonio;
    private Date fechaAlta;
    private Integer estado;
    private String observacion;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPersona")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Persona persona;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idTipoRelacion")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private TipoRelacion tipoRelacion;



}
