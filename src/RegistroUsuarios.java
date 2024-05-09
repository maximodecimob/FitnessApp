import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;


/**
 * La clase RegistroUsuarios gestiona un registro de usuarios.
 * Permite agregar, buscar y modificar usuarios en el registro.
 */
public class RegistroUsuarios implements Serializable {

    /** La lista de usuarios registrados. */
    private ArrayList<Usuario> usuarios;

    /**
     * Obtiene la lista de usuarios registrados.
     *
     * @return la lista de usuarios registrados
     */
    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    /**
     * Constructor para la clase RegistroUsuarios.
     */
    public RegistroUsuarios() {
        this.usuarios = new ArrayList<>();
    }

    /**
     * Agrega un usuario al registro, evitando agregar usuarios con el mismo nombre.
     *
     * @param usuario el usuario a agregar
     */
    public void agregarUsuario(Usuario usuario) {
        boolean existe = false;
        for (Usuario value : usuarios) {
            if (Objects.equals(value.getNombre(), usuario.getNombre())) {
                existe = true;
                break;
            }
        }
        if (!existe) {
            this.usuarios.add(usuario);
        }
    }

    /**
     * Busca un usuario en el registro por nombre.
     *
     * @param nombre el nombre del usuario a buscar
     * @return el usuario encontrado o null si no se encuentra
     */
    public Usuario buscarUsuario(String nombre) {
        Usuario usuario = null;
        for (Usuario u : usuarios) {
            if (Objects.equals(u.getNombre(), nombre)) {
                usuario = u;
            }
        }
        return usuario;
    }

    /**
     * Modifica un usuario en el registro.
     *
     * @param usuario el usuario modificado
     */
    public void modificar(Usuario usuario) {
        for (int i = 0; i < usuarios.size(); i++) {
            if (Objects.equals(usuarios.get(i).getNombre(), usuario.getNombre())) {
                usuarios.set(i, usuario);
            }
        }
    }
}
