package com.gestion.tramite.entidad;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "usuario")
@Data
public class User
{

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private Long id;

    private String user;

    private String password;

    private String token;

    private int estado;


}
