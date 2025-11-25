package sportlink.sportlink.project.controladores;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sportlink.sportlink.project.dto.VentaDto;
import sportlink.sportlink.project.servicios.ServicioVenta;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/appAqrSisII/Venta")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class ControladorVenta {
    private final ServicioVenta servicioVenta;

    public ControladorVenta(ServicioVenta servicioVenta) {
        this.servicioVenta = servicioVenta;
    }

    @GetMapping("/obtenerTodos")
    public List<VentaDto> obtenerTodos() {
        return servicioVenta.obtenerTodos();
    }

    @GetMapping("/obtener/{pkCliente}")
    public Optional<VentaDto> obtenerVenta(@PathVariable("pkVenta") int pkVenta) {
        return servicioVenta.obtenerPorPk(pkVenta);
    }

    @GetMapping("/obtenerPorFecha/{fecha}")
    public List<VentaDto> enviarCorreoElectronico(@PathVariable("fecha") LocalDateTime fecha){
        return servicioVenta.obtenerVentasPorFecha(fecha);
    }

    @PostMapping("/crear")
    @ResponseStatus(HttpStatus.CREATED)
    public VentaDto crear(@RequestBody VentaDto ventaDto){
        return servicioVenta.crear(ventaDto);
    }

    @PutMapping("/actualizar")
    @ResponseStatus(HttpStatus.CREATED)
    public VentaDto actualizar(@RequestBody VentaDto ventaDto){
        return servicioVenta.actualizar(ventaDto);
    }

    @DeleteMapping("/eliminarTodos")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean eliminarTodos(){
        return servicioVenta.eliminarTodos();
    }

    @DeleteMapping("/eliminar/{pkVenta}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean eliminar(@PathVariable("pkVenta") int pkVenta){
        return servicioVenta.eliminar(pkVenta
        );
    }
}
