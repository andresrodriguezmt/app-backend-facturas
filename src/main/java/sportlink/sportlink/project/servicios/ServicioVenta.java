package sportlink.sportlink.project.servicios;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import sportlink.sportlink.project.dto.VentaDto;
import sportlink.sportlink.project.entidades.Venta;
import sportlink.sportlink.project.repositorios.RepositorioVentas;

@Service
public class ServicioVenta {

    private final ModelMapper modelMapper = new ModelMapper();
    private final RepositorioVentas repositorioVentas;

    public ServicioVenta(RepositorioVentas repositorioVentas){
        this.repositorioVentas = repositorioVentas;
    }

    public List<VentaDto> obtenerTodos(){
        TypeToken<List<VentaDto>> typeToken = new TypeToken<>(){};
        return modelMapper.map(repositorioVentas.obtenerTodos(), typeToken.getType());
    }

    public Optional<VentaDto> obtenerPorPk(int pkVenta){
        return Optional.ofNullable(modelMapper.map(repositorioVentas.obtenerPorPk(pkVenta).orElse(null), VentaDto.class));
    }

    public List<VentaDto> obtenerVentasPorFecha(LocalDateTime fecha){
        TypeToken<List<VentaDto>> typeToken = new TypeToken<>(){};
        return modelMapper.map(repositorioVentas.obtenerVentasPorFecha(fecha), typeToken.getType());
    }

    public VentaDto crear(VentaDto ventaDto){
        if(ventaDto.getIdVenta() == null){
            Venta venta = repositorioVentas.crear(modelMapper.map(ventaDto, Venta.class));
            return modelMapper.map(venta, VentaDto.class);
        }else{
            Optional<VentaDto> temporal = obtenerPorPk(ventaDto.getIdVenta());
            if(temporal.isPresent()){
                return ventaDto;
            }else{
                Venta venta = repositorioVentas.crear(modelMapper.map(ventaDto, Venta.class));
                return modelMapper.map(venta, VentaDto.class);
            }
        }
    }

    public VentaDto actualizar(VentaDto ventaDto){
        if(ventaDto.getIdVenta() != null){

            Optional<VentaDto> nuevaVenta = obtenerPorPk(ventaDto.getIdVenta());

            if(nuevaVenta.isPresent()) {

                if (ventaDto.getFecha() != null) nuevaVenta.get().setFecha(ventaDto.getFecha());
                if (ventaDto.getNombreProducto() != null) nuevaVenta.get().setNombreProducto(ventaDto.getNombreProducto());
                if (ventaDto.getDescripcion() != null) nuevaVenta.get().setDescripcion(ventaDto.getDescripcion());
                if (ventaDto.getCantidadVentas() != null) nuevaVenta.get().setCantidadVentas(ventaDto.getCantidadVentas());
                if (ventaDto.getPrecioUnitario() != null) nuevaVenta.get().setPrecioUnitario(ventaDto.getPrecioUnitario());
                if (ventaDto.getCantidadStock() != null) nuevaVenta.get().setCantidadStock(ventaDto.getCantidadStock());
                if (ventaDto.getNumOrden() != null) nuevaVenta.get().setNumOrden(ventaDto.getNumOrden());

                Venta venta = repositorioVentas.actualizar(modelMapper.map(nuevaVenta.get(), Venta.class));
                return modelMapper.map(venta, VentaDto.class);
            }
        }
        return ventaDto;
    }

    public boolean eliminarTodos(){
        try {
            repositorioVentas.eliminarTodos();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean eliminar(int pkVenta){
        return obtenerPorPk(pkVenta).map(ventaDto -> {
            repositorioVentas.eliminarVenta(modelMapper.map(ventaDto, Venta.class));
            return true;
        }).orElse(false);
    }

}
