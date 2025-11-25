package sportlink.sportlink.project.repositorios.crud;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sportlink.sportlink.project.entidades.Venta;

import java.time.LocalDateTime;
import java.util.List;

public interface CrudVentas extends JpaRepository<Venta, Integer> {
    @Query(value = "SELECT * FROM VENTAS WHERE vent_fecha = ?1", nativeQuery = true)
    List<Venta> obtenerVentasPorFecha(LocalDateTime fecha);
}
