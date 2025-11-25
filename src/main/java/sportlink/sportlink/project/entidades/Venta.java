package sportlink.sportlink.project.entidades;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "VENTAS")
@ToString
@Entity
@Builder
public class Venta {

    @Id
    @Column(name = "VEN_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_VENTAS")
    @SequenceGenerator(name = "SEQ_VENTAS", sequenceName = "SEQ_VENTAS", allocationSize = 1)
    private Integer idCliente;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "VENT_FECHA", nullable = false)
    private LocalDateTime fecha;

    @Column(name = "VENT_NOMBREPRODUCTO", nullable = false)
    private String nombreProducto;

    @Column(name = "VENT_DESCRIPCION", nullable = false)
    private String descripcion;

    @Column(name = "VENT_CANTIDADSTOCK", nullable = false)
    private Integer cantidadStock;

    @Column(name = "VENT_PRECIOUNITARIO", nullable = false)
    private Float precioUnitario;

    @Column(name = "VENT_CANTIDADVENTAS", nullable = false)
    private Integer cantidadVentas;

    @Column(name = "VENT_NUMORDEN", nullable = false)
    private Integer numOrden;
}
