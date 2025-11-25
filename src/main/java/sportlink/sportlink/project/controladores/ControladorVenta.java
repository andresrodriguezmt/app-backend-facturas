package sportlink.sportlink.project.controladores;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import sportlink.sportlink.project.dto.VentaDto;
import sportlink.sportlink.project.servicios.ServicioVenta;

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

    @GetMapping("/obtener/{pkVenta}")
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
