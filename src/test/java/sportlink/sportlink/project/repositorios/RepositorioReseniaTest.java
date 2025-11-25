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
class RepositorioReseniaTest {

    @Autowired
    CrudResenia crudResenia;
    @Autowired
    CrudEntrenador crudEntrenador;
    @Autowired
    CrudCliente crudCliente;
    @Test
    @DisplayName("Obtener información de todas las resenias")
    void obtenerTodos() {
        Resenia resenia = Resenia
                .builder()
                .calificacion(5F)
                .comentario("Buen entranamiento")
                .cliente(null)
                .entrenador(null)
                .build();

        Resenia resenia1 = Resenia
                .builder()
                .calificacion(4F)
                .comentario("Falto un poco de logistica, pero buen entranamiento")
                .cliente(null)
                .entrenador(null)
                .build();

        crudResenia.save(resenia);
        crudResenia.save(resenia1);

        List<Resenia> listaResenias = crudResenia.findAll();

        assertThat(listaResenias).hasSize(2);
    }

    @Test
    @DisplayName("Obtener una resenia creada por su llave primaria")
    void obtenerPorPk() {
        Resenia resenia = Resenia
                .builder()
                .calificacion(5F)
                .comentario("Buen entrenamiento")
                .cliente(null)
                .entrenador(null)
                .build();
        crudResenia.save(resenia);

        Resenia resenia1 = crudResenia.getReferenceById(1);

        assertThat(resenia1.getIdResenia()).isEqualTo(1);
        assertThat(resenia1.getComentario()).isEqualTo("Buen entrenamiento");
    }

    @Test
    @DisplayName("Crear una resenia")
    void crear() {
        Resenia resenia = Resenia
                .builder()
                .calificacion(5F)
                .comentario("Buen entranamiento")
                .cliente(null)
                .entrenador(null)
                .build();

        Resenia resenia1 = crudResenia.save(resenia);

        assertThat(resenia1).isNotNull();
    }

    @Test
    @DisplayName("Actualizar una resenia ya creada")
    void actualizar() {
        Resenia resenia = Resenia
                .builder()
                .calificacion(5F)
                .comentario("Buen entranamiento")
                .cliente(null)
                .entrenador(null)
                .build();
        crudResenia.save(resenia);

        Resenia resenia1 = crudResenia.getReferenceById(1);

        resenia1.setCalificacion(2F);
        resenia1.setComentario("Pesimo servicio");

        crudResenia.save(resenia1);

        Resenia resenia2 = crudResenia.getReferenceById(1);

        assertThat(resenia2.getComentario()).isEqualTo("Pesimo servicio");
        assertThat(resenia2.getCalificacion()).isEqualTo(2);
    }

    @Test
    @DisplayName("Eliminar una resenia creada")
    void eliminar() {
        Resenia resenia = Resenia
                .builder()
                .calificacion(5F)
                .comentario("Buen entranamiento")
                .cliente(null)
                .entrenador(null)
                .build();
        crudResenia.save(resenia);

        Resenia resenia1 = crudResenia.getReferenceById(1);

        assertThat(resenia1).isNotNull();

        crudResenia.delete(resenia1);

        List<Resenia> listaResenias = crudResenia.findAll();

        assertThat(listaResenias).isEmpty();
    }

    @Test
    @DisplayName("Eliminar todas las resenias creadas")
    void eliminarTodos() {

        Resenia resenia = Resenia
                .builder()
                .calificacion(5F)
                .comentario("Buen entranamiento")
                .cliente(null)
                .entrenador(null)
                .build();

        Resenia resenia1 = Resenia
                .builder()
                .calificacion(4F)
                .comentario("Falto un poco de logistica, pero buen entranamiento")
                .cliente(null)
                .entrenador(null)
                .build();

        crudResenia.save(resenia);
        crudResenia.save(resenia1);

        List<Resenia> listaResenias = crudResenia.findAll();

        assertThat(listaResenias).hasSize(2);

        crudResenia.deleteAll();

        List<Resenia> listaResenias1 = crudResenia.findAll();

        assertThat(listaResenias1).isEmpty();
    }
}