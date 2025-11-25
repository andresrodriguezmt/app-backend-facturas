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
class RepositorioClienteTest {

    @Autowired
    CrudCliente crudCliente;
    @Autowired
    CrudEntrenador crudEntrenador;
    @Autowired
    CrudResenia crudResenia;
    @Autowired
    CrudSesion crudSesiones;

    @Test
    @DisplayName("Obtener todos los clientes creados")
    void obtenerTodos() {
        Cliente cliente1 = Cliente
                .builder()
                .nombres("Andres Felipe")
                .apellidos("Rodriguez Ramirez")
                .correo("andres@gmail.com")
                .contrasenia("Contrasenia123")
                .fotoPerfil(null)
                .fechaNacimiento("20/05/2003")
                .estatura(1.72F)
                .peso(54.3F)
                .telefono("3226685421")
                .ubicacion("Engativa")
                .fechaRegistro("03/10/2025")
                .sesiones(null)
                .resenias(null)
                .build();

        Cliente cliente2 = Cliente
                .builder()
                .nombres("Carlos")
                .apellidos("Gomez Perez")
                .correo("carlos@gmail.com")
                .contrasenia("Contrasenia1234")
                .fotoPerfil(null)
                .fechaNacimiento("23/09/2001")
                .estatura(1.77F)
                .peso(64.3F)
                .telefono("3226685421")
                .ubicacion("Engativa")
                .fechaRegistro("03/10/2025")
                .sesiones(null)
                .resenias(null)
                .build();

        crudCliente.save(cliente1);
        crudCliente.save(cliente2);

        List<Cliente> listaClientes = crudCliente.findAll();

        if(listaClientes.isEmpty()){
            listaClientes = null;
        }
        assertThat(listaClientes).isNotNull();
    }

    @Test
    @DisplayName("Obtener cliente por su llave primaria")
    void obtenerPorPk() {
        Cliente cliente1 = Cliente
                .builder()
                .nombres("Andres Felipe")
                .apellidos("Rodriguez Ramirez")
                .correo("andres@gmail.com")
                .contrasenia("Contrasenia123")
                .fotoPerfil(null)
                .fechaNacimiento("20/05/2003")
                .estatura(1.72F)
                .peso(54.3F)
                .telefono("3226685421")
                .ubicacion("Engativa")
                .fechaRegistro("03/10/2025")
                .sesiones(null)
                .resenias(null)
                .build();

        crudCliente.save(cliente1);

        Cliente cliente = crudCliente.getReferenceById(1);

        assertThat(cliente).isNotNull();
        assertThat(cliente.getIdCliente()).isEqualTo(1);
    }

    @Test
    @DisplayName("Obtener llave primaria del cliente por su correo o contraseña")
    void obtenerIdClientePorCorreoContrasenia() {
        Cliente cliente = Cliente
                .builder()
                .nombres("Carlos")
                .apellidos("Gomez Perez")
                .correo("carlos@gmail.com")
                .contrasenia("Contrasenia1234")
                .fotoPerfil(null)
                .fechaNacimiento("23/09/2001")
                .estatura(1.77F)
                .peso(64.3F)
                .telefono("3226685421")
                .ubicacion("Engativa")
                .fechaRegistro("03/10/2025")
                .sesiones(null)
                .resenias(null)
                .build();
        crudCliente.save(cliente);

        Integer id = crudCliente.obtenerIdClientePorCorreoContrasenia(cliente.getCorreo(), cliente.getContrasenia());

        assertThat(id).isEqualTo(1);
    }

    @Test
    @DisplayName("Crear un cliente con los datos básicos")
    void crear() {
        Cliente cliente = Cliente
                .builder()
                .nombres("Andres Felipe")
                .apellidos("Rodriguez Ramirez")
                .correo("andres@gmail.com")
                .contrasenia("Contrasenia123")
                .fotoPerfil(null)
                .fechaNacimiento("20/05/2003")
                .estatura(1.72F)
                .peso(54.3F)
                .telefono("3226685421")
                .ubicacion("Engativa")
                .fechaRegistro("03/10/2025")
                .sesiones(null)
                .resenias(null)
                .build();

        Cliente cliente1 = crudCliente.save(cliente);

        assertThat(cliente1).isNotNull();
        assertThat(cliente1.getNombres()).isEqualTo("Andres Felipe");
    }

    @Test
    @DisplayName("Actualizar un cliente ya creado y existente en la BD")
    void actualizar() {
        Cliente cliente = Cliente
                .builder()
                .nombres("Andres Felipe")
                .apellidos("Rodriguez Ramirez")
                .correo("andres@gmail.com")
                .contrasenia("Contrasenia123")
                .fotoPerfil(null)
                .fechaNacimiento("20/05/2003")
                .estatura(1.72F)
                .peso(54.3F)
                .telefono("3226685421")
                .ubicacion("Engativa")
                .fechaRegistro("03/10/2025")
                .sesiones(null)
                .resenias(null)
                .build();

        Cliente cliente1 = crudCliente.save(cliente);

        cliente1.setApellidos("Gomez Perez");
        cliente1.setUbicacion("Bosa el recreo");

        Cliente clienteActualizado = crudCliente.save(cliente1);

        assertThat(clienteActualizado.getIdCliente()).isEqualTo(1);
        assertThat(clienteActualizado.getApellidos()).isEqualTo("Gomez Perez");
        assertThat(clienteActualizado.getUbicacion()).isEqualTo("Bosa el recreo");
    }



    @Test
    @DisplayName("Eliminar un cliente que fue creado")
    void eliminar() {
        Cliente cliente = Cliente
                .builder()
                .nombres("Andres Felipe")
                .apellidos("Rodriguez Ramirez")
                .correo("andres@gmail.com")
                .contrasenia("Contrasenia123")
                .fotoPerfil(null)
                .fechaNacimiento("20/05/2003")
                .estatura(1.72F)
                .peso(54.3F)
                .telefono("3226685421")
                .ubicacion("Engativa")
                .fechaRegistro("03/10/2025")
                .sesiones(null)
                .resenias(null)
                .build();

        Cliente cliente1 = crudCliente.save(cliente);

        assertThat(cliente1).isNotNull();

        crudCliente.delete(cliente1);

        List<Cliente> listaClientes = crudCliente.findAll();

        assertThat(listaClientes).isEmpty();

    }

    @Test
    @DisplayName("Eliminar todos los clientes creado")
    void eliminarTodos() {
        Cliente cliente1 = Cliente
                .builder()
                .nombres("Andres Felipe")
                .apellidos("Rodriguez Ramirez")
                .correo("andres@gmail.com")
                .contrasenia("Contrasenia123")
                .fotoPerfil(null)
                .fechaNacimiento("20/05/2003")
                .estatura(1.72F)
                .peso(54.3F)
                .telefono("3226685421")
                .ubicacion("Engativa")
                .fechaRegistro("03/10/2025")
                .sesiones(null)
                .resenias(null)
                .build();

        Cliente cliente2 = Cliente
                .builder()
                .nombres("Carlos")
                .apellidos("Gomez Perez")
                .correo("carlos@gmail.com")
                .contrasenia("Contrasenia1234")
                .fotoPerfil(null)
                .fechaNacimiento("23/09/2001")
                .estatura(1.77F)
                .peso(64.3F)
                .telefono("3226685421")
                .ubicacion("Engativa")
                .fechaRegistro("03/10/2025")
                .sesiones(null)
                .resenias(null)
                .build();

        crudCliente.save(cliente1);
        crudCliente.save(cliente2);

        List<Cliente> listaClientes = crudCliente.findAll();

        assertThat(listaClientes).hasSize(2);

        crudCliente.deleteAll();

        List<Cliente> listaClientes1 = crudCliente.findAll();

        assertThat(listaClientes1).isEmpty();

    }
}