import java.io.*;
import java.util.ArrayList;
import Ejercicios.IntensidadIncorrectaException;

/**
 * Clase que representa el programa principal de FitnessApp.
 * Permite registrar usuarios, iniciar sesión y mostrar un menú general de opciones.
 */
public class ProgramaPrincipal throws IntensidadIncorrectaException {

    /**
     * Muestra el menú general de FitnessApp.
     * Permite al usuario registrar un nuevo usuario, iniciar sesión o salir del programa.
     */
    public static void menuGeneral() throws IntensidadIncorrectaException{
        String nombreArchivo = "registroUsuarios.dat";
        int menu;
        boolean fitnessApp = true, primera = true;
        RegistroUsuarios registroUsuarios = new RegistroUsuarios();
        do {
            if (primera) {
                primera = false;
                System.out.println("Bienvenido a FitnessApp, ¿Qué desea hacer?");
            } else {
                System.out.println("¿Qué desea hacer?");
            }
            System.out.println("1. Registrar usuario");
            System.out.println("2. Iniciar sesión");
            System.out.println("3. Guardar los datos en un archivo");
            System.out.println("4. Descargar información de todos los usuarios");
            System.out.println("5. Hacer un informe general de todos los usuarios");
            System.out.println("6. Salir");
            menu = PedirDatos.pedirNumeroIntMaxMin(1, 6);
            registroUsuarios = switchMenu(menu, registroUsuarios, nombreArchivo);
            if (registroUsuarios == null) {
                fitnessApp = false;
            }
        } while (fitnessApp);
    }

    /**
     * Gestiona las opciones del menú general.
     *
     * @param menu             la opción seleccionada por el usuario
     * @param registroUsuarios el registro de usuarios de FitnessApp
     * @return el registro de usuarios actualizado
     */
    private static RegistroUsuarios switchMenu(int menu, RegistroUsuarios registroUsuarios,String nombreArchivo) throws IntensidadIncorrectaException{
        switch (menu) {
            case (1):
                registrarse(registroUsuarios);
                break;
            case (2):
                registroUsuarios = inicioSesion(registroUsuarios,nombreArchivo);
                break;
            case (3):
                guardarDatos(registroUsuarios,nombreArchivo);
                break;
            case(4):
                registroUsuarios = leerDatos(nombreArchivo);
                break;
            case(5):
                generarInformeGeneral(registroUsuarios);
                break;
            case(6):
                registroUsuarios = null;
                break;
        }
        return registroUsuarios;
    }

    private static void generarInformeGeneral(RegistroUsuarios registroUsuarios) {
        try {
            PrintWriter pw = new PrintWriter("Informe.txt");
            pw.println("------------------------------------------------------------------------");
            pw.println("| NOMBRE                | EJERCICIOS  | NIVEL       | MEDIA INTENSIDAD |");
            pw.println("------------------------------------------------------------------------");
            ArrayList<Usuario> usuarios= registroUsuarios.getUsuarios();
            if( !usuarios.isEmpty()){
                for (Usuario usu : usuarios){
                    pw.printf("| %-22s| %-12d| %-12s| %-17d|\n", usu.getNombre(), usu.getEjerciciosSize(), usu.getNivel(), usu.calcularPromedioIntensidad());
                    pw.println("------------------------------------------------------------------");
                }
                pw.println("------------------------------------------------------------------------");
            }else{
                pw.println("No hay usuarios existentes");
            }
            pw.println("------------------------------------------------------------------");
            pw.close();
            System.out.println("Informe generado correctamente en el archivo 'Informe_general_2024_04_28.txt'");
        } catch (FileNotFoundException e) {
            System.out.println("No se ha podido abrir el archivo.");
        }

    }

    private static RegistroUsuarios leerDatos(String nombreArchivo) {
        try {
            ObjectInputStream archivo = new ObjectInputStream(new FileInputStream(nombreArchivo));
            RegistroUsuarios registro = (RegistroUsuarios)archivo.readObject();
            archivo.close();
            System.out.println("Datos descargados correctamente");
            return registro;
        } catch (IOException var3) {
            System.out.println("No se ha podido abrir el archivo para leer los datos.");
        } catch (ClassNotFoundException var4) {
            System.out.println("El archivo no contiene información de los usuarios.");
        }
        return new RegistroUsuarios();
    }

    private static void guardarDatos(RegistroUsuarios registroUsuarios,String nombreArchivo) {
        try {
            FileOutputStream file = new FileOutputStream(nombreArchivo);
            ObjectOutputStream archivo = new ObjectOutputStream(file);
            archivo.writeObject(registroUsuarios);
            archivo.close();
            System.out.println("Datos guardados correctamente");
        } catch (IOException var4) {
            System.out.println("No se ha podido abrir el archivo para guardar los datos.");
            var4.printStackTrace();
        }

    }

    /**
     * Permite a un usuario iniciar sesión en FitnessApp.
     *
     * @param registroUsuarios el registro de usuarios de FitnessApp
     * @return el registro de usuarios actualizado después de iniciar sesión
     */
    private static RegistroUsuarios inicioSesion(RegistroUsuarios registroUsuarios,String nombreArchivo) {
        String nombre;
        Usuario usuario;
        int menu;
        System.out.println("Introduce el nombre: ");
        nombre = PedirDatos.pedirPalabra("el nombre");
        usuario = registroUsuarios.buscarUsuario(nombre);
        if (usuario == null) {
            System.out.println("Necesitas iniciar sesión si no tienes usuario si tienes usuario introduce bien tu nombre");
            System.out.println("Si no estas registrado y deseas registrarte presiona 1 ");
            System.out.println("Si tienes usuario y has introducido mal tu nombre presiona 2 ");
            System.out.println("Para volver al menu anterior presione 3");
            menu = PedirDatos.pedirNumeroIntMaxMin(1, 3);
            if(menu == 3){
                menuGeneral();
            }
            registroUsuarios = switchMenu(menu, registroUsuarios, nombreArchivo);
        } else {
            MenuUsuario.menuUsuario(usuario);
            registroUsuarios.modificar(usuario);// Cuando el usuario cierra sesión se actualiza
        }
        return registroUsuarios;
    }
    /**
     * Permite a un nuevo usuario registrarse en FitnessApp.
     *
     * @param registroUsuarios el registro de usuarios de FitnessApp
     */
    private static void registrarse(RegistroUsuarios registroUsuarios) {
        System.out.println("Introduce el nombre: ");
        String nombre = PedirDatos.pedirPalabra("el nombre");
        System.out.println("Introduce el peso en kilogramos: ");
        double peso = PedirDatos.pedirNumeroDoubleMin(0);
        System.out.println("Introduce la edad: ");
        int edad = PedirDatos.pedirNumeroIntMin(0);
        Nivel nivel = PedirDatos.seleccionarNivel();
        boolean intentarNuevamente = true;
        while (intentarNuevamente) {
            try {
                Usuario nuevoUsuario = new Usuario(nombre, edad, peso, nivel);
                registroUsuarios.agregarUsuario(nuevoUsuario);
                intentarNuevamente = false; // No se lanzó ninguna excepción, podemos salir del bucle
            } catch (Exception e) {
                if (e.getMessage().equals("Nivel")) {
                    System.out.println("Has introducido un nivel erroneo corrígelo");
                    nivel = PedirDatos.seleccionarNivel();
                } else if (e.getMessage().equals("Nombre")) {
                    System.out.println("Has introducido un nombre vacío tienes que volver a introducirlo");
                    nombre = PedirDatos.pedirPalabra("el nombre");
                } else if (e.getMessage().equals("Edad")) {
                    System.out.println("Has introducido una edad negativa la cual es imposible");
                    edad = PedirDatos.pedirNumeroIntMin(0);
                } else if (e.getMessage().equals("Peso")) {
                    System.out.println("Has introducido un peso imposible ya que es negativo");
                    peso = PedirDatos.pedirNumeroIntMin(0);
                } else {
                    System.out.println("Error no esperado");
                }
            }
        }
        System.out.println("Usuario registrado correctamente");
    }
}

