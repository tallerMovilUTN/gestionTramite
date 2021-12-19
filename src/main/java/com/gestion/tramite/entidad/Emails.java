package com.gestion.tramite.entidad;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "emails")
@Data
public class Emails {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private Long id;
    private String to;
    private String subject;
    private String msg;
    private Date fechaEnvio;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPersona")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Persona persona;


}
