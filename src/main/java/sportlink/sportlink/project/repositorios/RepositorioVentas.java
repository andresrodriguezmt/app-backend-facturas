package sportlink.sportlink.project.repositorios;

import org.springframework.stereotype.Repository;
import sportlink.sportlink.project.entidades.Venta;
import sportlink.sportlink.project.repositorios.crud.CrudVentas;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class RepositorioVentas {

    private final CrudVentas crudVentas;

    public RepositorioVentas(CrudVentas crudVentas){
        this.crudVentas = crudVentas;
    }

    public List<Venta> obtenerTodos(){
        return crudVentas.findAll();
    }
    public List<Venta> obtenerVentasPorFecha(LocalDateTime fecha){
        return crudVentas.obtenerVentasPorFecha(fecha);
    }
    public Optional<Venta> obtenerPorPk (int pkVentas){
        return crudVentas.findById(pkVentas);
    }
    public Venta crear(Venta venta){
        return crudVentas.save(venta);
    }

    public Venta actualizar(Venta venta){
        return crudVentas.save(venta);
    }

    public void eliminarTodos(){
        crudVentas.deleteAll();
    }

    public void eliminarVenta(Venta venta){
        crudVentas.delete(venta);
    }




}

