package sportlink.sportlink.project.entidades;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
    private Integer idVenta;

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
