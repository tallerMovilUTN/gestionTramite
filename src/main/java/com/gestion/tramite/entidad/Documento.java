package com.gestion.tramite.entidad;

        import lombok.Data;

        import javax.persistence.*;
        import java.util.Date;

@Entity
@Table(name = "documentos")
@Data
public class Documento
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String nombre;
    private String descripcion;
    private Date fechaAlta;
    private Integer estado;

}
