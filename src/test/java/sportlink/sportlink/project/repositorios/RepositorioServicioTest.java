package sportlink.sportlink.project.repositorios;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RepositorioServicioTest {

    @Autowired
    CrudServicio crudServicio;

    @Autowired
    CrudEntrenador crudEntrenador;

    @Test
    @DisplayName("Obtener todos los servicios creados")
    void obtenerTodos() {
        Servicio servicio1 = Servicio.builder()
                .nombre("Clases de Yoga")
                .descripcion("Sesiones de yoga para principiantes")
                .precio(50.0F)
                .ubicacion("Bogotá")
                .entrenador(null)
                .build();

        Servicio servicio2 = Servicio.builder()
                .nombre("Entrenamiento Funcional")
                .descripcion("Rutina de ejercicios de alta intensidad")
                .precio(80.0F)
                .ubicacion("Medellín")
                .entrenador(null)
                .build();

        crudServicio.save(servicio1);
        crudServicio.save(servicio2);

        List<Servicio> listaServicios = crudServicio.findAll();

        assertThat(listaServicios).hasSize(2);
    }

    @Test
    @DisplayName("Obtener un servicio creado por su llave primaria")
    void obtenerPorPk() {
        Servicio servicio = Servicio.builder()
                .nombre("Clases de Boxeo")
                .descripcion("Entrenamiento básico de boxeo")
                .precio(100.0F)
                .ubicacion("Cali")
                .entrenador(null)
                .build();

        crudServicio.save(servicio);

        Servicio servicio1 = crudServicio.getReferenceById(1);

        assertThat(servicio1.getIdServicio()).isEqualTo(1);
        assertThat(servicio1.getNombre()).isEqualTo("Clases de Boxeo");
    }

    @Test
    @DisplayName("Crear un servicio")
    void crear() {
        Servicio servicio = Servicio.builder()
                .nombre("Entrenamiento Personalizado")
                .descripcion("Rutina individual con entrenador")
                .precio(120.0F)
                .ubicacion("Cartagena")
                .entrenador(null)
                .build();

        Servicio servicio1 = crudServicio.save(servicio);

        assertThat(servicio1).isNotNull();
    }

    @Test
    @DisplayName("Actualizar un servicio ya creado")
    void actualizar() {
        Servicio servicio = Servicio.builder()
                .nombre("Zumba")
                .descripcion("Clases grupales de zumba")
                .precio(60.0F)
                .ubicacion("Bogotá")
                .entrenador(null)
                .build();

        crudServicio.save(servicio);

        Servicio servicio1 = crudServicio.getReferenceById(1);

        servicio1.setNombre("Zumba Avanzado");
        servicio1.setPrecio(75.0F);

        crudServicio.save(servicio1);

        Servicio servicio2 = crudServicio.getReferenceById(1);

        assertThat(servicio2.getNombre()).isEqualTo("Zumba Avanzado");
        assertThat(servicio2.getPrecio()).isEqualTo(75.0F);
    }

    @Test
    @DisplayName("Eliminar un servicio creado")
    void eliminar() {
        Servicio servicio = Servicio.builder()
                .nombre("Natación")
                .descripcion("Clases de natación nivel básico")
                .precio(90.0F)
                .ubicacion("Manizales")
                .entrenador(null)
                .build();

        crudServicio.save(servicio);

        Servicio servicio1 = crudServicio.getReferenceById(1);

        assertThat(servicio1).isNotNull();

        crudServicio.delete(servicio1);

        List<Servicio> listaServicios = crudServicio.findAll();

        assertThat(listaServicios).isEmpty();
    }

    @Test
    @DisplayName("Eliminar todos los servicios creados")
    void eliminarTodos() {
        Servicio servicio1 = Servicio.builder()
                .nombre("Crossfit")
                .descripcion("Entrenamiento funcional avanzado")
                .precio(150.0F)
                .ubicacion("Cali")
                .entrenador(null)
                .build();

        Servicio servicio2 = Servicio.builder()
                .nombre("Pilates")
                .descripcion("Clases de pilates intermedio")
                .precio(70.0F)
                .ubicacion("Bogotá")
                .entrenador(null)
                .build();

        crudServicio.save(servicio1);
        crudServicio.save(servicio2);

        List<Servicio> listaServicios = crudServicio.findAll();

        assertThat(listaServicios).hasSize(2);

        crudServicio.deleteAll();

        List<Servicio> listaServicios1 = crudServicio.findAll();

        assertThat(listaServicios1).isEmpty();
    }
}