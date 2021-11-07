package com.gestion.tramite.entidad;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class CodPosProId implements Serializable {

    @Column(name = "idCodPostal")
    private Integer idCodPostal;

    @Column(name = "idProvincia")
    private Integer idProvincia;
}
