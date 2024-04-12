import java.util.ArrayList;
import java.util.Objects;

public class RegistroUsuarios {
    private ArrayList<Usuario> usuarios;

    public RegistroUsuarios() {
        //Hacer la correspondiente exception
        this.usuarios = new ArrayList<>();
    }
    public void agregarUsuario(Usuario usuario){
        boolean existe = false;
        for (Usuario value : usuarios) {
            if (Objects.equals(value.getNombre(), usuario.getNombre())) {
                existe = true;
                break;
            }
        }
        if(!existe) {
            this.usuarios.add(usuario);
        }
    }
    public Usuario buscarUsuario(String nombre){
        Usuario usuario = null;
        for (Usuario u : usuarios) {
            if (Objects.equals(value.getNombre(), nombre)) {
                usuario = u;
            }
        }
        return usuario;
    }

    public void modificar(Usuario usuario) {
        for (int i = 0;i<usuarios.size();i++) {
            if (Objects.equals(usuarios.get(i).getNombre(), usuario.getNombre())) {
                usuarios.set(i, usuario);
            }
        }
    }
}
