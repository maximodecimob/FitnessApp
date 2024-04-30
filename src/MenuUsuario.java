import Ejercicios.Cardio;
import Ejercicios.Ejercicio;
import Ejercicios.Flexibilidad;
import Ejercicios.Fuerza;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * La clase MenuUsuario proporciona un menú interactivo para que un usuario realice diversas operaciones relacionadas con sus ejercicios.
 */
public class MenuUsuario {

    /**
     * Presenta un menú interactivo para que el usuario realice diversas operaciones relacionadas con sus ejercicios.
     *
     * @param usuario el usuario para el cual se realiza el menú
     */
    public static void menuUsuario(Usuario usuario) {
        boolean menuUsu = true;
        // Añadir ejercicios de muestra para propósitos de prueba
        int menu;
        System.out.println("Bienvenido " + usuario.getNombre() + ", ¿Qué desea hacer?");
        do {
            // Mostrar el menú
            mostrarMenu();
            // Solicitar al usuario que ingrese una opción del menú
            menu = PedirDatos.pedirNumeroIntMaxMin(1, 12);
            // Procesar la opción seleccionada por el usuario
            switch (menu) {
                case 1 -> anadirEjercicio(usuario);
                case 2 -> eliminarEjercicio(usuario);
                case 3 -> ejerciciosRealizadosCaloriasTotal(usuario);
                case 4 -> ejerciciosRealizadosDedeFecha(usuario);
                case 5 -> ejerciciosDeUnTipoConGastoCalorico(usuario);
                case 6 -> ejerciciosDeUnTipoEntreFecha(usuario);
                case 7 -> obtenerEjerciciosEnFecha(usuario);
                case 8 -> obtenerEjerciciosEntreFechas(usuario);
                case 9 -> calcularIntensidadMediaEjercicios(usuario);
                case 10 -> calcularIntensidadMediaFecha(usuario);
                case 11 -> informeEjercicios(usuario);
                case 12 -> {
                    System.out.println("Volviendo al menú anterior...");
                    menuUsu = false;
                }
            }
        } while (menuUsu);
    }

    private static void informeEjercicios(Usuario usuario) {
        try {
            LocalDate fecha = LocalDate.now();
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy_MM_dd");
            String fechaFormateada = fecha.format(formato);
            PrintWriter pw = new PrintWriter("informe_"+usuario.getNombre()+"_"+fechaFormateada+".txt");
            pw.println("-------------------------------------------------------------------------");
            pw.println("        | INFORME DE EJERCICIOS |");
            pw.println("-------------------------------------------------------------------------");
            pw.println("USUARIO: "+usuario.getNombre());
            pw.println("Edad: "+usuario.getEdad());
            pw.println("Peso: "+usuario.getPeso()+" Kg");
            pw.println("Nivel: "+usuario.getNivel());
            pw.println("Ejercicios Realizados:");
            pw.println("Fecha       Ejercicio    Intensidad   Características");
            DateTimeFormatter formatear = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            String fechaConFormato;
            for (Ejercicio ejercicio : usuario.getEjerciciosRelacionados()) {
                fechaConFormato=ejercicio.getFecha().format(formatear);
                pw.printf("%-11s %-12s %-12s %-30s\n", fechaConFormato, ejercicio.getNombre(), ejercicio.getIntensidad(), ejercicio.getDatosInforme());
            }
            pw.println("Total calorías consumidas: "+usuario.calcularConsumoCaloricoTotal()+" Kcal");
            pw.println("Media de Intensidad de los ejercicios: "+usuario.calcularPromedioIntensidad());
            pw.close();
        } catch (FileNotFoundException e) {
            System.out.println("No se ha podido abrir el archivo.");
        }

    }

    // Métodos privados auxiliares para manejar las opciones del menú

    // Método para mostrar el menú
    private static void mostrarMenu() {
        System.out.println();
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("1. Registrar la realización de un nuevo ejercicio.");
        System.out.println("2. Eliminar un ejercicio realizado dado su nombre y su fecha de realización.");
        System.out.println("3. Mostrar el número de ejercicios total realizados y el gasto calórico total de ellos.");
        System.out.println("4. Mostrar el número de ejercicios realizados desde una fecha hasta la actualidad, mostrando el gasto calórico total de esos ejercicios.");
        System.out.println("5. Mostrar los ejercicios realizados de un determinado tipo, mostrando las calorías gastadas en la realización de cada uno.");
        System.out.println("6. Mostrar los ejercicios realizados de un determinado tipo desde una fecha dada hasta la actualidad, con el gasto calórico total de ellos.");
        System.out.println("7. Obtener todos los ejercicios, con toda su información, realizados en un determinado mes y año.");
        System.out.println("8. Obtener todos los ejercicios, con toda su información, realizados entre dos fechas.");
        System.out.println("9. Calcular la intensidad media de los ejercicios totales realizados.");
        System.out.println("10. Calcular la intensidad media de los ejercicios realizados desde una fecha.");
        System.out.println("11. Generar un informe con los ejercicios realizados por ti");
        System.out.println("12. Volver al menú anterior.");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println();
        System.out.println("Ingrese el número de la opción deseada:");
    }
    /**
     * Calcula y muestra la intensidad media de los ejercicios realizados desde una fecha dada hasta la actualidad.
     *
     * @param usuario el usuario cuyos ejercicios se están evaluando
     */
    private static void calcularIntensidadMediaFecha(Usuario usuario){
        System.out.println("Introduce la fecha desde la cual quieres calcular la intensidad media");
        LocalDate fecha = PedirDatos.pedirFecha();
        System.out.println("La intensidad media desde "+fecha.toString()+" hasta la actualidad es de "+usuario.IntensidadMediaFecha(fecha));
    }
    /**
     * Calcula y muestra la intensidad media por ejercicio realizado.
     *
     * @param usuario el usuario cuyos ejercicios se están evaluando
     */
    private static void calcularIntensidadMediaEjercicios(Usuario usuario) {
        double intensidad = usuario.calcularPromedioIntensidad();
        System.out.println("La intensidad media por ejercicio realizado es de "+ intensidad);
    }
    /**
     * Obtiene y muestra todos los ejercicios realizados por el usuario entre dos fechas dadas.
     *
     * @param usuario el usuario cuyos ejercicios se están consultando
     */
    private static void obtenerEjerciciosEntreFechas(Usuario usuario) {
        System.out.println("Introduce la fecha desde la que quieres empezar a listar");
        LocalDate fecha1 = PedirDatos.pedirFecha();
        System.out.println("Introduce la fecha hasta la que quieres acabar a listar");
        LocalDate fecha2 = PedirDatos.pedirFecha();
        ArrayList<Ejercicio> ejercicios= usuario.ejerciciosEntreFechas(fecha1,fecha2);
        for (Ejercicio value: ejercicios){
            System.out.println(value.toString());
        }
    }
    /**
     * Obtiene y muestra todos los ejercicios realizados por el usuario en un mes y año específicos.
     *
     * @param usuario el usuario cuyos ejercicios se están consultando
     */
    private static void obtenerEjerciciosEnFecha(Usuario usuario) {
        System.out.println("Introduce el mes y el año de cuando quieras ver");
        YearMonth yearMonth = PedirDatos.pedirAnoMes();
        ArrayList<Ejercicio> ejercicios = usuario.ejerciciosEnAnoMes(yearMonth);
        for (Ejercicio value: ejercicios){
            System.out.println(value.toString());
        }
    }
    /**
     * Obtiene y muestra los ejercicios de un determinado tipo realizados desde una fecha dada hasta la actualidad.
     *
     * @param usuario el usuario cuyos ejercicios se están consultando
     */
    private static void ejerciciosDeUnTipoEntreFecha(Usuario usuario) {
        System.out.println("Introduce que tipo de ejercicio quieres listar desde una fecha hasta la actualidad");
        System.out.println("1.- Cardio ");
        System.out.println("2.- Flexibilidad ");
        System.out.println("3.-Fuerza ");
        int opcion = PedirDatos.pedirNumeroIntMaxMin(1, 3);
        System.out.println("Introduce la fecha desde la que quieres empezar a listar");
        LocalDate fecha = PedirDatos.pedirFecha();
        Class<? extends Ejercicio> claseEjercicio = switch (opcion) {
            case 1 -> Cardio.class;
            case 2 -> Flexibilidad.class;
            case 3 -> Fuerza.class;
            default -> null;
        };
        ArrayList<Ejercicio> ejercicio = usuario.obtenerEjerciciosPorTipoFecha(claseEjercicio,fecha);
        for (Ejercicio value: ejercicio){
            System.out.println(value.toString());
        }
    }
    /**
     * Obtiene y muestra los ejercicios de un determinado tipo realizados, junto con su gasto calórico, por el usuario.
     *
     * @param usuario el usuario cuyos ejercicios se están consultando
     */
    private static void ejerciciosDeUnTipoConGastoCalorico(Usuario usuario) {
        System.out.println("Introduce que tipo de ejercicio quieres listar con su gasto calórico");
        System.out.println("1.- Cardio ");
        System.out.println("2.- Flexibilidad ");
        System.out.println("3.-Fuerza ");
        int opcion = PedirDatos.pedirNumeroIntMaxMin(1, 3);
        Class<? extends Ejercicio> claseEjercicio = switch (opcion) {
            case 1 -> Cardio.class;
            case 2 -> Flexibilidad.class;
            case 3 -> Fuerza.class;
            default -> null;
        };
        ArrayList<Ejercicio> ejercicios = usuario.obtenerEjerciciosPorTipo(claseEjercicio);
        for(Ejercicio value: ejercicios){
            System.out.println(value.toString());
            System.out.println("Este ejercicio ha tenido un gasto de calorías de: "+value.calcularCalorias(usuario.getPeso()));
        }
    }
    /**
     * Obtiene y muestra los ejercicios realizados por el usuario después de una fecha específica.
     *
     * @param usuario el usuario cuyos ejercicios se están consultando
     */
    private static void ejerciciosRealizadosDedeFecha(Usuario usuario) {
        System.out.println("Para mostrarte todos los ejercicios realizados desde un dia introduce el dia: ");
        LocalDate fecha = PedirDatos.pedirFecha();
        ArrayList<Ejercicio> ejercicios = usuario.ejerciciosDespuesDeFecha(fecha);
        for(Ejercicio value: ejercicios){
            System.out.println(value.toString());
        }
    }
    /**
     * Muestra el número total de ejercicios realizados por el usuario y el gasto calórico total de ellos.
     *
     * @param usuario el usuario cuyos ejercicios se están consultando
     */
    private static void ejerciciosRealizadosCaloriasTotal(Usuario usuario) {
        System.out.println("Has realizado "+usuario.getEjerciciosSize()+" ejercicios");
        System.out.println("Con unas calorias totales de "+usuario.calcularConsumoCaloricoTotal());
    }
    /**
     * Elimina un ejercicio realizado por el usuario dado su nombre y fecha de realización.
     *
     * @param usuario el usuario cuyo ejercicio se va a eliminar
     */
    private static void eliminarEjercicio(Usuario usuario) {
        System.out.println("Introduce el nombre del ejercicio que quieras eliminar ");
        String nombre = PedirDatos.pedirPalabra("el ejercicio");
        System.out.println("Introduce la fecha de la realizacion del ejercicio que quieras eliminar");
        LocalDate fecha = PedirDatos.pedirFecha();
        Ejercicio ejercicio = usuario.buscarEjercicio(nombre,fecha);
        if(ejercicio != null) {
            usuario.eliminarEjercicio(ejercicio);
            System.out.println("Ejercicio eliminado correctamente");
        }else{
            System.out.println("El ejercicio no existía no se ha podido eliminar");
        }
    }
    /**
     * Agrega un nuevo ejercicio realizado por el usuario.
     *
     * @param usuario el usuario que realiza el ejercicio
     */
    private static void anadirEjercicio(Usuario usuario){
        int opcion;
        // Pedir el nombre
        System.out.println("Ingrese el nombre del ejercicio:");
        String nombre = PedirDatos.pedirPalabra("El ejercicio ");
        // Pedir la intensidad
        System.out.println("Ingrese la intensidad del ejercicio (un número entero) entre 1 y 8 siendo 1 lo mas bajo y 8 lo mas alto:");
        int intensidad = PedirDatos.pedirNumeroIntMaxMin(1,8);
        // Pedir la fecha
        System.out.println("Ingrese la fecha del ejercicio (en formato AAAA-MM-DD):");
        LocalDate fecha = PedirDatos.pedirFecha();
        System.out.println("Que tipo de ejercicio desea añadir ");
        System.out.println("1.- Cardio ");
        System.out.println("2.- Flexibilidad ");
        System.out.println("3.-Fuerza ");
        opcion = PedirDatos.pedirNumeroIntMaxMin(1,3);
        switch (opcion){
            case 1:
                anadirCardio(usuario,nombre,intensidad,fecha);
                break;
            case 2:
                anadirFlexibilidad(usuario,nombre,intensidad,fecha);
                break;
            case 3:
                anadirFuerza(usuario,nombre,intensidad,fecha);
                break;
        }
        System.out.println("Ejercicio añadido correctamente");
    }

    /**
     * Agrega un nuevo ejercicio de fuerza realizado por el usuario.
     *
     * @param usuario  el usuario que realiza el ejercicio
     * @param nombre   el nombre del ejercicio
     * @param intensidad la intensidad del ejercicio
     * @param fecha    la fecha en que se realizó el ejercicio
     */
    private static void anadirFuerza(Usuario usuario, String nombre, int intensidad, LocalDate fecha) {
        // Solicita al usuario que introduzca el peso levantado en kilogramos
        System.out.println("Introduce el peso levantado en kilogramos ");
        double peso = PedirDatos.pedirNumeroDoubleMin(0);
        // Solicita al usuario que introduzca el número de repeticiones
        System.out.println("Introduce el número de repeticiones ");
        int repes = PedirDatos.pedirNumeroIntMin(0);
        // Crea un nuevo ejercicio de fuerza y lo agrega al usuario
        Fuerza fuerza = new Fuerza(nombre, intensidad, fecha, peso, repes);
        usuario.agregarEjercicioRealizado(fuerza);
    }

    /**
     * Agrega un nuevo ejercicio de flexibilidad realizado por el usuario.
     *
     * @param usuario      el usuario que realiza el ejercicio
     * @param nombre       el nombre del ejercicio
     * @param intensidad   la intensidad del ejercicio
     * @param fecha        la fecha en que se realizó el ejercicio
     */
    private static void anadirFlexibilidad(Usuario usuario, String nombre, int intensidad, LocalDate fecha) {
        // Solicita al usuario que introduzca el número de repeticiones
        System.out.println("Introduce las repeticiones ");
        int repeticiones = PedirDatos.pedirNumeroIntMin(0);
        // Crea un nuevo ejercicio de flexibilidad y lo agrega al usuario
        Flexibilidad flexibilidad = new Flexibilidad(nombre, intensidad, fecha, repeticiones);
        usuario.agregarEjercicioRealizado(flexibilidad);
    }

    /**
     * Agrega un nuevo ejercicio de cardio realizado por el usuario.
     *
     * @param usuario      el usuario que realiza el ejercicio
     * @param nombre       el nombre del ejercicio
     * @param intensidad   la intensidad del ejercicio
     * @param fecha        la fecha en que se realizó el ejercicio
     */
    private static void anadirCardio(Usuario usuario, String nombre, int intensidad, LocalDate fecha) {
        // Solicita al usuario que introduzca la distancia en kilómetros
        System.out.println("Ingrese la distancia en kilómetros ");
        double distancia = PedirDatos.pedirNumeroDoubleMin(0);
        // Solicita al usuario que introduzca la duración en minutos
        System.out.println("Ingrese la duración en minutos");
        double duracion = PedirDatos.pedirNumeroDoubleMin(0);
        // Crea un nuevo ejercicio de cardio y lo agrega al usuario
        Cardio cardio = new Cardio(nombre, intensidad, fecha, distancia, duracion);
        usuario.agregarEjercicioRealizado(cardio);
    }
}
