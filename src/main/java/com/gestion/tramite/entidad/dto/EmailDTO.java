package com.gestion.tramite.entidad.dto;

import lombok.Data;

@Data
public class EmailDTO {

    private String to;
    private String subject;
    //private String name;
    private String msg;
}
