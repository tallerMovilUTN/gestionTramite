package com.gestion.tramite.entidad;

        import lombok.Data;
        import org.hibernate.annotations.GenericGenerator;

        import javax.persistence.*;
        import java.util.Date;

@Entity
@Table(name = "documentos")
@Data
public class Documento
{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private Long id;
    private String nombre;
    private String descripcion;
    private Date fechaAlta;
    private Integer estado;

}
