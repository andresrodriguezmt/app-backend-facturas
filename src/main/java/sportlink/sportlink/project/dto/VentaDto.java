package sportlink.sportlink.project.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VentaDto {

    private Integer idVenta;
    private LocalDateTime fecha;
    private String nombreProducto;
    private String descripcion;
    private Integer cantidadStock;
    private Float precioUnitario;
    private Integer cantidadVentas;
    private Integer numOrden;
}
