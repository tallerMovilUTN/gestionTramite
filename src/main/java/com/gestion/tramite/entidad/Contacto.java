package com.gestion.tramite.entidad;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "contacto")
@Data
public class Contacto
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
    private Date fechaDefuncion;
    private String lugarDefuncion;

    private String tipoDoc;

    private Integer dni;
    private String calleNombre;
    private Integer calleNro;
    private Integer cp;
    private String localidad;
    private String provincia;
    private Long celularNro;
    private Long telefonoParticular;
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

    private Date fechaNacDesde;
    private Date fechaNacHasta;
    private Date fechaMatDesde;
    private Date fechaMatHasta;
    private Date fechaDefDesde;
    private Date fechaDefHasta;
    private Integer actaNac;
    private Integer nroFolioNac;
    private String cmbFolioNac;
    private Integer nroTomoNac;
    private String cmbTomoNac;
    private String ofRegCivilNac;
    private String ciudadRegCivilNac;
    private String provRegCivilNac;
    private String paisRegCivilNac;

    private Integer actaMat;
    private Integer nroFolioMat;
    private String cmbFolioMat;
    private Integer nroTomoMat;
    private String cmbTomoMat;
    private String ofRegCivilMat;
    private String ciudadRegCivilMat;
    private String provRegCivilMat;
    private String paisRegCivilMat;

    private Integer actaDef;
    private Integer nroFolioDef;
    private String cmbFolioDef;
    private Integer nroTomoDef;
    private String cmbTomoDef;
    private String ofRegCivilDef;
    private String ciudadRegCivilDef;
    private String provRegCivilDef;
    private String paisRegCivilDef;

    private String apellidoNombrePadre;
    private String apellidoNombreMadre;



}
