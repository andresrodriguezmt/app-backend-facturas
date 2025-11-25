package sportlink.sportlink.project.repositorios;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RepositorioSesionTest {

    @Autowired
    CrudSesion crudSesion;

    @Autowired
    CrudCliente crudCliente;

    @Autowired
    CrudEntrenador crudEntrenador;

    private Cliente crearCliente() {
        Cliente cliente = Cliente.builder()
                .nombres("Andres Felipe")
                .apellidos("Rodriguez Ramirez")
                .correo("andres@gmail.com")
                .contrasenia("Contrasenia123")
                .fechaNacimiento("20/05/2003")
                .estatura(1.72F)
                .peso(54.3F)
                .telefono("3226685421")
                .ubicacion("Engativa")
                .fechaRegistro("03/10/2025")
                .build();
        return crudCliente.save(cliente);
    }

    private Entrenador crearEntrenador() {
        Entrenador entrenador = Entrenador
                .builder()
                .nombres("Juan Pablo")
                .apellidos("Montes Leon")
                .correo("juanp@gmail.com")
                .contrasenia("contrasenia123")
                .especialidad(null)
                .certificaciones(null)
                .fotoPerfil(null)
                .fechaRegistro("04/10/2025")
                .resenias(null)
                .sesiones(null)
                .servicios(null)
                .build();
        return crudEntrenador.save(entrenador);
    }

    @Test
    @DisplayName("Obtener todas las sesiones creadas")
    void obtenerTodos() {
        Cliente cliente = crearCliente();
        Entrenador entrenador = crearEntrenador();

        Sesion sesion1 = Sesion.builder()
                .fechaHora(LocalDateTime.now())
                .estado("PROGRAMADA")
                .cliente(cliente)
                .entrenador(entrenador)
                .build();

        Sesion sesion2 = Sesion.builder()
                .fechaHora(LocalDateTime.now().plusDays(1))
                .estado("FINALIZADA")
                .cliente(cliente)
                .entrenador(entrenador)
                .build();

        crudSesion.save(sesion1);
        crudSesion.save(sesion2);

        List<Sesion> listaSesiones = crudSesion.findAll();

        assertThat(listaSesiones).isNotEmpty();
        assertThat(listaSesiones.size()).isGreaterThanOrEqualTo(2);
    }

    @Test
    @DisplayName("Obtener sesión por PK")
    void obtenerPorPk() {
        Cliente cliente = crearCliente();
        Entrenador entrenador = crearEntrenador();

        Sesion sesion = Sesion.builder()
                .fechaHora(LocalDateTime.now())
                .estado("PROGRAMADA")
                .cliente(cliente)
                .entrenador(entrenador)
                .build();

        Sesion guardada = crudSesion.save(sesion);

        Optional<Sesion> encontrada = crudSesion.findById(guardada.getIdSesion());

        assertThat(encontrada).isPresent();
        assertThat(encontrada.get().getEstado()).isEqualTo("PROGRAMADA");
    }

    @Test
    @DisplayName("Crear sesión")
    void crear() {
        Cliente cliente = crearCliente();
        Entrenador entrenador = crearEntrenador();

        Sesion sesion = Sesion.builder()
                .fechaHora(LocalDateTime.now())
                .estado("PROGRAMADA")
                .cliente(cliente)
                .entrenador(entrenador)
                .build();

        Sesion guardada = crudSesion.save(sesion);

        assertThat(guardada).isNotNull();
        assertThat(guardada.getIdSesion()).isNotNull();
    }

    @Test
    @DisplayName("Actualizar sesión")
    void actualizar() {
        Cliente cliente = crearCliente();
        Entrenador entrenador = crearEntrenador();

        Sesion sesion = Sesion.builder()
                .fechaHora(LocalDateTime.now())
                .estado("PROGRAMADA")
                .cliente(cliente)
                .entrenador(entrenador)
                .build();

        Sesion guardada = crudSesion.save(sesion);

        // actualizar estado
        guardada.setEstado("CANCELADA");
        Sesion actualizada = crudSesion.save(guardada);

        assertThat(actualizada.getEstado()).isEqualTo("CANCELADA");
    }

    @Test
    @DisplayName("Eliminar sesión por ID")
    void eliminar() {
        Cliente cliente = crearCliente();
        Entrenador entrenador = crearEntrenador();

        Sesion sesion = Sesion.builder()
                .fechaHora(LocalDateTime.now())
                .estado("PROGRAMADA")
                .cliente(cliente)
                .entrenador(entrenador)
                .build();

        Sesion guardada = crudSesion.save(sesion);

        crudSesion.deleteById(guardada.getIdSesion());

        Optional<Sesion> eliminada = crudSesion.findById(guardada.getIdSesion());

        assertThat(eliminada).isEmpty();
    }

    @Test
    @DisplayName("Eliminar todas las sesiones")
    void eliminarTodos() {
        Cliente cliente = crearCliente();
        Entrenador entrenador = crearEntrenador();

        crudSesion.save(Sesion.builder()
                .fechaHora(LocalDateTime.now())
                .estado("PROGRAMADA")
                .cliente(cliente)
                .entrenador(entrenador)
                .build());

        crudSesion.save(Sesion.builder()
                .fechaHora(LocalDateTime.now().plusDays(1))
                .estado("FINALIZADA")
                .cliente(cliente)
                .entrenador(entrenador)
                .build());

        crudSesion.deleteAll();

        List<Sesion> listaSesiones = crudSesion.findAll();

        assertThat(listaSesiones).isEmpty();
    }
}