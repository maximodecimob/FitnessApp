import Excepciones.ConjuntoVacioException;
import Excepciones.NumeroNegativoException;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Clase que representa el programa principal de FitnessApp.
 * Permite registrar usuarios, iniciar sesión y mostrar un menú general de opciones.
 */
public class ProgramaPrincipal {

    /**
     * Muestra el menú general de FitnessApp.
     * Permite al usuario registrar un nuevo usuario, iniciar sesión o salir del programa.
     */
    public static void menuGeneral() {
        int menu;
        boolean fitnessApp = true, primera = true;
        RegistroUsuarios registroUsuarios = new RegistroUsuarios();
        do {
            if (primera) {
                primera = false;
                System.out.println("\nBienvenido a FitnessApp, ¿Qué desea hacer?");
            } else {
                System.out.println("¿Qué desea hacer?");
            }
            System.out.println("1. Registrar usuario");
            System.out.println("2. Iniciar sesión");
            System.out.println("3. Guardar los datos en un archivo");
            System.out.println("4. Descargar información de todos los usuarios");
            System.out.println("5. Hacer un informe general de todos los usuarios");
            System.out.println("6. Salir");
            menu = PedirDatos.pedirNumeroIntMaxMin(1, 6, true);
            registroUsuarios = switchMenu(menu, registroUsuarios);
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
    private static RegistroUsuarios switchMenu(int menu, RegistroUsuarios registroUsuarios) {
        switch (menu) {
            case (1):
                registrarse(registroUsuarios);
                break;
            case (2):
                inicioSesion(registroUsuarios);
                break;
            case (3):
                guardarDatos(registroUsuarios);
                break;
            case (4):
                registroUsuarios = leerDatos();
                break;
            case (5):
                generarInformeGeneral(registroUsuarios);
                break;
            case (6):
                registroUsuarios = null;
                break;
        }
        return registroUsuarios;
    }

    /**
     * Genera un informe general de todos los usuarios y lo guarda en un archivo.
     *
     * @param registroUsuarios el registro de usuarios de FitnessApp
     */
    private static void generarInformeGeneral(RegistroUsuarios registroUsuarios) {
        try {
            LocalDate fecha = LocalDate.now();
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy_MM_dd");
            String fechaFormateada = fecha.format(formato);
            PrintWriter pw = new PrintWriter("Informe_general_" + fechaFormateada + ".txt");
            pw.println("------------------------------------------------------------------------");
            pw.println("| NOMBRE                | EJERCICIOS  | NIVEL       | MEDIA INTENSIDAD |");
            pw.println("------------------------------------------------------------------------");
            ArrayList<Usuario> usuarios = registroUsuarios.getUsuarios();
            if (!usuarios.isEmpty()) {
                for (Usuario usu : usuarios) {
                    pw.printf("| %-22s| %-12d| %-12s| %-17d|\n", usu.getNombre(), usu.getEjerciciosSize(), usu.getNivel(), usu.calcularPromedioIntensidad());
                }
                pw.println("------------------------------------------------------------------------");
            } else {
                pw.println("No hay usuarios existentes");
            }
            pw.close();
            System.out.println("Informe creado");
        } catch (FileNotFoundException e) {
            System.out.println("No se ha podido abrir el archivo.");
        }
    }

    /**
     * Lee los datos de los usuarios desde un archivo.
     *
     * @return el registro de usuarios leído desde el archivo
     */
    private static RegistroUsuarios leerDatos() {
        try {
            ObjectInputStream archivo = new ObjectInputStream(new FileInputStream("registroUsuarios.dat"));
            RegistroUsuarios registro = (RegistroUsuarios) archivo.readObject();
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

    /**
     * Guarda los datos de los usuarios en un archivo.
     *
     * @param registroUsuarios el registro de usuarios de FitnessApp
     */
    private static void guardarDatos(RegistroUsuarios registroUsuarios) {
        try {
            FileOutputStream file = new FileOutputStream("registroUsuarios.dat");
            ObjectOutputStream archivo = new ObjectOutputStream(file);
            archivo.writeObject(registroUsuarios);
            archivo.close();
            System.out.println("Datos guardados correctamente");
        } catch (IOException var4) {
            System.out.println("No se ha podido abrir el archivo para guardar los datos.");
        }
    }

    /**
     * Permite a un usuario iniciar sesión en FitnessApp.
     *
     * @param registroUsuarios el registro de usuarios de FitnessApp
     */
    private static void inicioSesion(RegistroUsuarios registroUsuarios) {
        String nombre;
        Usuario usuario;
        System.out.println("Introduce el nombre: ");
        nombre = PedirDatos.pedirPalabra("el nombre");
        if (Objects.equals(nombre, "")) {
            System.out.println("Nombre incorrecto, volviendo al menú...");
            return;
        } else {
            usuario = registroUsuarios.buscarUsuario(nombre);
            if (usuario == null) {
                System.out.println("Necesitas iniciar sesión si no tienes usuario. Si tienes usuario introduce bien tu nombre.");
                return;
            } else {
                MenuUsuario.menuUsuario(usuario);
                registroUsuarios.modificar(usuario);// Cuando el usuario cierra sesión se actualiza
            }
        }
    }

    /**
     * Permite a un nuevo usuario registrarse en FitnessApp.
     *
     * @param registroUsuarios el registro de usuarios de FitnessApp
     */
    private static void registrarse(RegistroUsuarios registroUsuarios) {
        try {
            System.out.println("Introduce el nombre: ");
            String nombre = PedirDatos.pedirPalabra("el nombre");
            if (Objects.equals(nombre, "")) {
                return;
            }
            System.out.println("Introduce el peso en kilogramos: ");
            double peso = PedirDatos.pedirNumeroDoubleMin(0);
            if (peso <= 0) {
                return;
            }
            System.out.println("Introduce la edad: ");
            int edad = PedirDatos.pedirNumeroIntMin(0);
            if (edad <= 0) {
                return;
            }
            Nivel nivel = PedirDatos.seleccionarNivel();
            if (nivel == null) {
                return;
            }

            Usuario nuevoUsuario = new Usuario(nombre, edad, peso, nivel);
            registroUsuarios.agregarUsuario(nuevoUsuario);

            System.out.println("Usuario registrado con éxito.");

        } catch (ConjuntoVacioException e) {
            System.out.println("Has introducido un nombre vacío. \nVolviendo al menú...");
        } catch (NumeroNegativoException e) {
            System.out.println("Has introducido un valor negativo. \nVolviendo al menú...");
        }
    }
}
