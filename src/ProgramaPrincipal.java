import java.util.Objects;
import java.util.Scanner;

public class ProgramaPrincipal {
    public static void menuGeneral(){
        int menu=3;
        boolean fitnessApp=true,primera=true,inicio=true;
        String nombre;
        RegistroUsuarios registroUsuarios = new RegistroUsuarios();
        Usuario usuario;
        do {
            if(inicio) {
                if (primera) {
                    primera = false;
                    System.out.println("Bienvenido a FitnessApp, ¿Qué desea hacer?");
                } else {
                    System.out.println("¿Qué desea hacer?");
                }
                System.out.println("1. Registrar usuario");
                System.out.println("2. Iniciar sesión");
                System.out.println("3. Salir");
                menu = PedirDatos.pedirNumeroIntMaxMin(1, 3);
            }else{
                inicio = true;
            }
            switch (menu) {
                case (1):
                    registroUsuarios = iniciarSesion(registroUsuarios);
                    break;
                case (2):
                    System.out.println("Introduce el nombre: ");
                    nombre = PedirDatos.pedirPalabra("el nombre");
                    usuario = registroUsuarios.buscarUsuario(nombre);
                    if(usuario==null){
                        System.out.println("Necesitas iniciar sesión si no tienes usuario si tienes usuario introduce bien tu nombre");
                        System.out.println("Si no estas registrado y deseas registrarte presiona 1 ");
                        System.out.println("Si tienes usuario y has introducido mal tu nombre presiona 2 ");
                        menu = PedirDatos.pedirNumeroIntMaxMin(1,2);
                        inicio = false;
                    }else{
                        usuario = MenuUsuario.menuUsuario(usuario);
                        registroUsuarios.modificar(usuario);
                    }
                    break;
                case (3):
                    fitnessApp = false;
                    break;
            }
        }while(fitnessApp);
    }
    private static RegistroUsuarios iniciarSesion(RegistroUsuarios registroUsuarios) {
        System.out.println("Introduce el nombre: ");
        String nombre = PedirDatos.pedirPalabra("el nombre");
        System.out.println("Introduce el peso: ");
        double peso = PedirDatos.pedirNumeroDoubleMin(0);
        System.out.println("Introduce la edad: ");
        int edad = PedirDatos.pedirNumeroIntMin(0);
        Nivel nivel = PedirDatos.seleccionarNivel();
        Usuario nuevoUsuario = new Usuario(nombre,edad,peso,nivel);
        registroUsuarios.agregarUsuario(nuevoUsuario);
        System.out.println("Usuario registrado correctamente");
        return registroUsuarios;
    }
}
