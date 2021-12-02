package com.gestion.tramite.entidad;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;


@Data
public class User
{
    String user;
    String token;


}
