package com.gestion.tramite.entidad;

import lombok.Data;

import java.io.Serializable;

@Data
public class ReciboDTO implements Serializable {

    private static final long serialVersionUID = 1636233039859366027L;
    String fecha;
    String apellido;
    String cantidadletra;
    String concepto;
    String cantidadNro;
    String nroCuota;
    String pagoEfectivo;
    String pagoCheque;
    String pagoTransferencia;

}
