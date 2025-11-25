package sportlink.sportlink.project.repositorios;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import sportlink.sportlink.project.repositorios.crud.*;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RepositorioEntrenadorTest {

    @Autowired
    CrudEntrenador crudEntrenador;
    @Autowired
    CrudCliente crudCliente;
    @Autowired
    CrudResenia crudResenia;
    @Autowired
    CrudServicio crudServicio;
    @Autowired
    CrudSesion crudSesion;

    @Test
    @DisplayName("Obtener los entrenadores registrados")
    void obtenerTodos() {
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
        Entrenador entrenador1 = Entrenador
                .builder()
                .nombres("Julian")
                .apellidos("Grosso")
                .correo("julianG@gmail.com")
                .contrasenia("contrasenia12")
                .especialidad(null)
                .certificaciones(null)
                .fotoPerfil(null)
                .fechaRegistro("04/10/2025")
                .resenias(null)
                .sesiones(null)
                .servicios(null)
                .build();

        crudEntrenador.save(entrenador);
        crudEntrenador.save(entrenador1);

        List<Entrenador> listaEntrenadores = crudEntrenador.findAll();

        assertThat(listaEntrenadores).hasSize(2);
        assertThat(listaEntrenadores.getLast().getApellidos()).isEqualTo("Grosso");
    }

    @Test
    @DisplayName("Obtener el entrenador que se creo")
    void obtenerPorPk() {
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

        crudEntrenador.save(entrenador);

        Entrenador entrenador1 = crudEntrenador.getReferenceById(1);

        assertThat(entrenador1).isNotNull();
        assertThat(entrenador1.getIdEntrenador()).isEqualTo(1);
        assertThat(entrenador1.getNombres()).isEqualTo("Juan Pablo");
    }

    @Test
    @DisplayName("Obtener el id de un entrenador por su correo y contraseña")
    void obtenerIdEntrenadorPorCorreoContrasenia() {
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

        crudEntrenador.save(entrenador);

        Integer idEntrenador = crudEntrenador.obtenerIdEntrenadorPorCorreoContrasenia("juanp@gmail.com", "contrasenia123");

        assertThat(idEntrenador).isEqualTo(1);
    }

    @Test
    @DisplayName("Crear un entrenador")
    void crear() {
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

        Entrenador entrenador1 = crudEntrenador.save(entrenador);

        assertThat(entrenador1).isNotNull();
        assertThat(entrenador1.getCorreo()).isEqualTo("juanp@gmail.com");
    }

    @Test
    @DisplayName("Actualizar un cliente recien creado")
    void actualizar() {
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

        crudEntrenador.save(entrenador);

        Integer idEntrenador = crudEntrenador.obtenerIdEntrenadorPorCorreoContrasenia("juanp@gmail.com", "contrasenia123");

        Entrenador entrenador1 = crudEntrenador.getReferenceById(idEntrenador);

        entrenador1.setCorreo("juan1@gmail.com");

        Entrenador entrenadorActualizado = crudEntrenador.save(entrenador1);

        assertThat(entrenadorActualizado.getCorreo()).isEqualTo("juan1@gmail.com");

    }

    @Test
    @DisplayName("Eliminar registro de un entrenador creado")
    void eliminar() {

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

        Entrenador entrenador1 = crudEntrenador.save(entrenador);

        List<Entrenador> listaEntrenadores = crudEntrenador.findAll();

        assertThat(listaEntrenadores).isNotNull();

        crudEntrenador.delete(entrenador1);

        List<Entrenador> listaEntrenadores1 = crudEntrenador.findAll();

        assertThat(listaEntrenadores1).isEmpty();
    }

    @Test
    void eliminarTodos() {
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
        Entrenador entrenador1 = Entrenador
                .builder()
                .nombres("Julian")
                .apellidos("Grosso")
                .correo("julianG@gmail.com")
                .contrasenia("contrasenia12")
                .especialidad(null)
                .certificaciones(null)
                .fotoPerfil(null)
                .fechaRegistro("04/10/2025")
                .resenias(null)
                .sesiones(null)
                .servicios(null)
                .build();

        crudEntrenador.save(entrenador);
        crudEntrenador.save(entrenador1);

        List<Entrenador> listaEntrenadores = crudEntrenador.findAll();

        assertThat(listaEntrenadores).isNotNull();

        crudEntrenador.deleteAll();

        List<Entrenador> listaEntrenadores1 = crudEntrenador.findAll();

        assertThat(listaEntrenadores1).isEmpty();

    }
}