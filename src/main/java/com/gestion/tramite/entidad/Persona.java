package com.gestion.tramite.entidad;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "persona")
@Data
public class Persona
{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private Long id;
    private String apellido;
    private String nombre;
    private String email;
    private Date fechaNac;
    private String lugarNac;

    private String tipoDoc;

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
    private String idfotoFrente;
    private String idfotoDorso;
    private Date fechaAlta;
    private Integer estado;

    private Integer actaNac;
    private Integer nroFolioNac;
    private Integer nroTomoNac;
    private String ofRegCivilNac;
    private String ciudadRegCivilNac;
    private String provRegCivilNac;
    private String paisRegCivilNac;




}
