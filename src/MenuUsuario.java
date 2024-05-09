import Ejercicios.Cardio;
import Ejercicios.Ejercicio;
import Ejercicios.Flexibilidad;
import Ejercicios.Fuerza;
import Excepciones.ConjuntoVacioException;
import Excepciones.IntensidadIncorrectaException;
import Excepciones.NumeroNegativoException;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;
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
        int menu;
        boolean menuUsu = true;
        System.out.println("\nBienvenido " + usuario.getNombre() + ", ¿Qué desea hacer?");
        do {
            // Mostrar el menú
            mostrarMenu();
            // Solicitar al usuario que ingrese una opción del menú
            menu = PedirDatos.pedirNumeroIntMaxMin(1, 12, true);
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
     * Genera un informe de los ejercicios realizados por un usuario y lo guarda en un archivo de texto.
     *
     * @param usuario el usuario del cual se generarán los informes
     */
    private static void informeEjercicios(Usuario usuario) {
        try {
            // Obtener la fecha actual
            LocalDate fecha = LocalDate.now();
            // Formatear la fecha actual como "yyyy_MM_dd"
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy_MM_dd");
            String fechaFormateada = fecha.format(formato);
            // Crear un PrintWriter para escribir en el archivo
            PrintWriter pw = new PrintWriter("informe_" + usuario.getNombre() + "_" + fechaFormateada + ".txt");
            // Escribir encabezado del informe
            pw.println("-------------------------------------------------------------------------");
            pw.println("        | INFORME DE EJERCICIOS |");
            pw.println("-------------------------------------------------------------------------");
            pw.println("USUARIO: " + usuario.getNombre());
            pw.println("Edad: " + usuario.getEdad());
            pw.println("Peso: " + usuario.getPeso() + " Kg");
            pw.println("Nivel: " + usuario.getNivel());
            // Verificar si el usuario tiene ejercicios relacionados
            if (usuario.getEjerciciosRelacionados() != null) {
                // Escribir los ejercicios realizados en el informe
                pw.println("Ejercicios Realizados:");
                pw.println("Fecha       Ejercicio    Intensidad   Características");
                DateTimeFormatter formatear = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                String fechaConFormato;
                for (Ejercicio ejercicio : usuario.getEjerciciosRelacionados()) {
                    fechaConFormato = ejercicio.getFecha().format(formatear);
                    pw.printf("%-11s %-12s %-12s %-30s\n", fechaConFormato, ejercicio.getNombre(), ejercicio.getIntensidad(), ejercicio.getDatosInforme());
                }
                // Escribir el total de calorías consumidas y la media de intensidad de los ejercicios
                pw.println("Total calorías consumidas: " + usuario.calcularConsumoCaloricoTotal() + " Kcal");
                pw.println("Media de Intensidad de los ejercicios: " + usuario.calcularPromedioIntensidad());
            } else {
                // Indicar si no hay ejercicios realizados
                pw.println("No hay ejercicios realizados");
            }
            // Cerrar el PrintWriter
            pw.close();
        } catch (FileNotFoundException e) {
            // Manejar la excepción si no se puede abrir el archivo
            System.out.println("No se ha podido abrir el archivo.");
        }
    }
    /**
     * Calcula y muestra la intensidad media de los ejercicios realizados desde una fecha dada hasta la actualidad.
     *
     * @param usuario el usuario cuyos ejercicios se están evaluando
     */
    private static void calcularIntensidadMediaFecha(Usuario usuario){
        System.out.println("Introduce la fecha desde la cual quieres calcular la intensidad media");
        LocalDate fecha = PedirDatos.pedirFecha();
        if(fecha != null) {
            System.out.println("La intensidad media desde " + fecha + " hasta la actualidad es de " + usuario.IntensidadMediaFecha(fecha));
        }else{
            System.out.println("Al meter mal el dato volviendo al menu del usuario ...");
        }
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
        if (fecha1 != null) {
            System.out.println("Introduce la fecha hasta la que quieres acabar a listar");
            LocalDate fecha2 = PedirDatos.pedirFecha();
            if (fecha2 != null) {
                ArrayList<Ejercicio> ejercicios = usuario.ejerciciosEntreFechas(fecha1, fecha2);
                if (ejercicios.isEmpty()) {
                    System.out.println("No hay ejercicios entre esas fechas");
                } else {
                    for (Ejercicio value : ejercicios) {
                        System.out.println(value.toString());
                    }
                }
            } else {
                System.out.println("Al meter mal el dato volviendo al menu del usuario ...");
            }
        } else {
            System.out.println("Al meter mal el dato volviendo al menu del usuario ...");
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
        if (yearMonth != null) {
            ArrayList<Ejercicio> ejercicios = usuario.ejerciciosEnAnoMes(yearMonth);
            if (ejercicios.isEmpty()) {
                System.out.println("No hay ejercicios de ese mes y de ese año");
            } else {
                for (Ejercicio value : ejercicios) {
                    System.out.println(value.toString());
                }
            }
        } else {
            System.out.println("Al meter mal el dato volviendo al menu del usuario ...");
        }
    }

    /**
     * Obtiene y muestra los ejercicios de un determinado tipo realizados desde una fecha dada hasta la actualidad.
     *
     * @param usuario el usuario cuyos ejercicios se están consultando
     */
    private static void ejerciciosDeUnTipoEntreFecha(Usuario usuario) {
        System.out.println("Introduce qué tipo de ejercicio quieres listar desde una fecha hasta la actualidad");
        System.out.println("1.- Cardio ");
        System.out.println("2.- Flexibilidad ");
        System.out.println("3.- Fuerza ");
        int opcion = PedirDatos.pedirNumeroIntMaxMin(1, 3, true);
        System.out.println("Introduce la fecha desde la que quieres empezar a listar");
        LocalDate fecha = PedirDatos.pedirFecha();
        if (fecha != null) {
            Class<? extends Ejercicio> claseEjercicio = switch (opcion) {
                case 1 -> Cardio.class;
                case 2 -> Flexibilidad.class;
                case 3 -> Fuerza.class;
                default -> null;
            };
            ArrayList<Ejercicio> ejercicio = usuario.obtenerEjerciciosPorTipoFecha(claseEjercicio, fecha);
            if (ejercicio.isEmpty()) {
                System.out.println("No hay ejercicios para ese tipo y fecha");
            } else {
                for (Ejercicio value : ejercicio) {
                    System.out.println(value.toString());
                }
            }
        } else {
            System.out.println("Al meter mal el dato volviendo al menu del usuario ...");
        }
    }

    /**
     * Obtiene y muestra los ejercicios de un determinado tipo realizados, junto con su gasto calórico, por el usuario.
     *
     * @param usuario el usuario cuyos ejercicios se están consultando
     */
    private static void ejerciciosDeUnTipoConGastoCalorico(Usuario usuario) {
        System.out.println("Introduce qué tipo de ejercicio quieres listar con su gasto calórico");
        System.out.println("1.- Cardio ");
        System.out.println("2.- Flexibilidad ");
        System.out.println("3.- Fuerza ");
        int opcion = PedirDatos.pedirNumeroIntMaxMin(1, 3, true);
        Class<? extends Ejercicio> claseEjercicio = switch (opcion) {
            case 1 -> Cardio.class;
            case 2 -> Flexibilidad.class;
            case 3 -> Fuerza.class;
            default -> null;
        };
        ArrayList<Ejercicio> ejercicios = usuario.obtenerEjerciciosPorTipo(claseEjercicio);
        if (!ejercicios.isEmpty()) {
            for (Ejercicio value : ejercicios) {
                System.out.println(value.toString());
                System.out.println("Este ejercicio ha tenido un gasto de calorías de: " + value.calcularCalorias(usuario.getPeso()));
            }
        } else {
            System.out.println("No hay ejercicios de el tipo seleccionado");
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
        if(fecha != null) {
            ArrayList<Ejercicio> ejercicios = usuario.ejerciciosDespuesDeFecha(fecha);
            if(ejercicios.isEmpty()){
                System.out.println("No hay ejercicios realizados desde esa fecha");
            }else {
                for (Ejercicio value : ejercicios) {
                    System.out.println(value.toString());
                }
            }
        }else{
            System.out.println("Al meter mal el dato volviendo al menu del usuario ...");
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
        if(!Objects.equals(nombre, "")) {
            System.out.println("Introduce la fecha de la realizacion del ejercicio que quieras eliminar");
            LocalDate fecha = PedirDatos.pedirFecha();
            if(fecha != null) {
                Ejercicio ejercicio = usuario.buscarEjercicio(nombre, fecha);
                if (ejercicio != null) {
                    usuario.eliminarEjercicio(ejercicio);
                    System.out.println("Ejercicio eliminado correctamente");
                } else {
                    System.out.println("El ejercicio no existía no se ha podido eliminar");
                }
            }else{
                System.out.println("Al meter mal el dato volviendo al menu del usuario ...");
            }
        }else{
            System.out.println("Al meter mal el dato volviendo al menu del usuario ...");
        }
    }
    /**
     * Agrega un nuevo ejercicio realizado por el usuario.
     *
     * @param usuario el usuario que realiza el ejercicio
     */
    private static void anadirEjercicio(Usuario usuario) {
        // Solicita al usuario que ingrese el nombre del ejercicio
        System.out.println("Ingrese el nombre del ejercicio:");
        String nombre = PedirDatos.pedirPalabra("El ejercicio ");
        if (Objects.equals(nombre, "")) {
            return;
        }

        // Solicita al usuario que ingrese la intensidad del ejercicio
        System.out.println("Ingrese la intensidad del ejercicio (un número entero) entre 1 y 8, siendo 1 lo más bajo y 8 lo más alto:");
        int intensidad = PedirDatos.pedirNumeroIntMaxMin(1, 8, false);
        if (intensidad == -1) {
            return;
        }

        // Solicita al usuario que ingrese la fecha del ejercicio
        System.out.println("Ingrese la fecha del ejercicio (en formato AAAA-MM-DD):");
        LocalDate fecha = PedirDatos.pedirFecha();
        if (fecha == null) {
            return;
        }

        // Solicita al usuario que seleccione el tipo de ejercicio a añadir
        int opcion;
        System.out.println("Qué tipo de ejercicio desea añadir?");
        System.out.println("1.- Cardio");
        System.out.println("2.- Flexibilidad");
        System.out.println("3.- Fuerza");
        opcion = PedirDatos.pedirNumeroIntMaxMin(1, 3, true);

        // Ejecuta el método correspondiente según la opción seleccionada
        switch (opcion) {
            case 1:
                anadirCardio(usuario, nombre, intensidad, fecha);
                break;
            case 2:
                anadirFlexibilidad(usuario, nombre, intensidad, fecha);
                break;
            case 3:
                anadirFuerza(usuario, nombre, intensidad, fecha);
                break;
        }
    }

    /**
     * Agrega un nuevo ejercicio de fuerza realizado por el usuario.
     *
     * @param usuario    el usuario que realiza el ejercicio
     * @param nombre     el nombre del ejercicio
     * @param intensidad la intensidad del ejercicio
     * @param fecha      la fecha en que se realizó el ejercicio
     */
    private static void anadirFuerza(Usuario usuario, String nombre, int intensidad, LocalDate fecha) {
        // Solicita al usuario que introduzca el peso
        System.out.println("Introduzca el peso:");
        double peso = PedirDatos.pedirNumeroDoubleMin(0);
        if (peso == -1) {
            System.out.println("Al ingresar incorrectamente el dato, volviendo al menú del usuario...");
            return;
        }

        // Solicita al usuario que introduzca el número de repeticiones
        System.out.println("Introduce el número de repeticiones:");
        int repeticiones = PedirDatos.pedirNumeroIntMin(0);
        if (repeticiones == -1) {
            System.out.println("Al ingresar incorrectamente el dato, volviendo al menú del usuario...");
            return;
        }

        // Crea un nuevo ejercicio de fuerza y lo agrega al usuario
        try {
            Fuerza fuerza = new Fuerza(nombre, intensidad, fecha, peso, repeticiones);
            usuario.agregarEjercicioRealizado(fuerza);
        } catch (IntensidadIncorrectaException e) {
            System.out.println("Has ingresado incorrectamente la intensidad");
        } catch (ConjuntoVacioException e) {
            System.out.println("Has ingresado incorrectamente la fecha o el nombre del ejercicio");
        } catch (NumeroNegativoException e) {
            System.out.println("Has ingresado un valor negativo para el peso o las repeticiones");
        }
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
        System.out.println("Introduce las repeticiones:");
        int repeticiones = PedirDatos.pedirNumeroIntMin(0);
        if (repeticiones != -1) {
            // Crea un nuevo ejercicio de flexibilidad y lo agrega al usuario
            try {
                Flexibilidad flexibilidad = new Flexibilidad(nombre, intensidad, fecha, repeticiones);
                usuario.agregarEjercicioRealizado(flexibilidad);
            } catch (IntensidadIncorrectaException e) {
                System.out.println("Has ingresado incorrectamente la intensidad");
            } catch (ConjuntoVacioException e) {
                System.out.println("Has ingresado incorrectamente la fecha o el nombre del ejercicio");
            } catch (NumeroNegativoException e) {
                System.out.println("Has ingresado un valor negativo para las repeticiones");
            }
        } else {
            System.out.println("Al ingresar incorrectamente el dato, volviendo al menú del usuario...");
        }
    }

    /**
     * Agrega un nuevo ejercicio de cardio realizado por el usuario.
     *
     * @param usuario    el usuario que realiza el ejercicio
     * @param nombre     el nombre del ejercicio
     * @param intensidad la intensidad del ejercicio
     * @param fecha      la fecha en que se realizó el ejercicio
     */
    private static void anadirCardio(Usuario usuario, String nombre, int intensidad, LocalDate fecha) {
        // Solicita al usuario que introduzca la distancia
        System.out.println("Introduce la distancia:");
        double distancia = PedirDatos.pedirNumeroDoubleMin(0);
        if (distancia == -1) {
            System.out.println("Al ingresar incorrectamente el dato, volviendo al menú del usuario...");
            return;
        }

        // Solicita al usuario que introduzca la duración
        System.out.println("Introduce la duración:");
        double duracion = PedirDatos.pedirNumeroDoubleMin(0);
        if (duracion == -1) {
            System.out.println("Al ingresar incorrectamente el dato, volviendo al menú del usuario...");
            return;
        }

        // Crea un nuevo ejercicio de cardio y lo agrega al usuario
        try {
            Cardio cardio = new Cardio(nombre, intensidad, fecha, distancia, duracion);
            usuario.agregarEjercicioRealizado(cardio);
        } catch (IntensidadIncorrectaException e) {
            System.out.println("Has ingresado incorrectamente la intensidad");
        } catch (ConjuntoVacioException e) {
            System.out.println("Has ingresado incorrectamente la fecha o el nombre del ejercicio");
        } catch (NumeroNegativoException e) {
            System.out.println("Has ingresado un valor negativo para la distancia o la duración");
        }
    }
}