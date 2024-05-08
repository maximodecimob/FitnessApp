import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Objects;
import java.util.Scanner;

/**
 * La clase PedirDatos proporciona métodos para solicitar diversos tipos de datos al usuario desde la consola.
 */
public class PedirDatos {

    /**
     * Solicita al usuario un número entero mayor o igual que el valor mínimo especificado.
     *
     * @param min el valor mínimo permitido
     * @return el número entero ingresado por el usuario
     */
    public static int pedirNumeroIntMin(int min) {
        Scanner teclado = new Scanner(System.in);
        int num = 0;
        do {
            try {
                num = teclado.nextInt();
                if (num < min) {
                    System.out.print("Introduzca un número mayor que " + min);
                }
            } catch (Exception ex) {
                System.out.print("Introduzca un número mayor que " + min);
                String texto = teclado.nextLine();
                num = min - 1;
            }
        } while (num < min);
        return num;
    }

    /**
     * Solicita al usuario un número entero dentro de un rango específico.
     *
     * @param min el valor mínimo permitido
     * @param max el valor máximo permitido
     * @return el número entero ingresado por el usuario
     */
    public static int pedirNumeroIntMaxMin(int min, int max,boolean menu) {
        Scanner teclado = new Scanner(System.in);
        int num = 0;
        do {
            try {
                num = teclado.nextInt();
                if (num < min || num > max) {
                    System.out.print("Introduzca un número en el rango " + min + "-" + max + ":");
                }
            } catch (Exception ex) {
                System.out.print("Introduzca un número en el rango " + min + "-" + max + ":");
                String texto = teclado.nextLine();
                num = min - 1;
            }
        } while ((num < min || num > max )&&menu);
        return num;
    }

    /**
     * Solicita al usuario un número decimal mayor o igual que el valor mínimo especificado.
     *
     * @param min el valor mínimo permitido
     * @return el número decimal ingresado por el usuario
     */
    public static double pedirNumeroDoubleMin(int min) {
        Scanner teclado = new Scanner(System.in);
        double num = 0;
        do {
            try {
                num = teclado.nextDouble();
                if (num < min) {
                    System.out.print("Introduzca un número mayor que " + min + " ");
                }
            } catch (Exception ex) {
                System.out.print("Introduzca un número mayor que " + min + " ");
                String texto = teclado.nextLine();
                num = min - 1;
            }
        } while (num < min);
        return num;
    }

    /**
     * Solicita al usuario una palabra (cadena de caracteres que no contenga números ni caracteres especiales).
     *
     * @param peticion el mensaje de solicitud al usuario
     * @return la palabra ingresada por el usuario
     */
    public static String pedirPalabra(String peticion) {
        Scanner teclado = new Scanner(System.in);
        String palabra;
        do {
            try {
                palabra = teclado.nextLine();
                if (esNumero(palabra) || contieneCaracteresEspeciales(palabra)) {
                    System.out.println(peticion + " no puede contener números ni caracteres especiales. Inténtelo de nuevo.");
                    palabra = ""; // Restablecer la palabra para volver a solicitarla
                }
            } catch (Exception ex) {
                System.out.print("Introduzca " + peticion);
                String texto = teclado.nextLine();
                palabra = "";
            }
        } while (Objects.equals(palabra, ""));
        return palabra;
    }

    /**
     * Verifica si una cadena de caracteres contiene números.
     *
     * @param str la cadena de caracteres a verificar
     * @return true si la cadena contiene números, false en caso contrario
     */
    private static boolean esNumero(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Verifica si una cadena de caracteres contiene caracteres especiales.
     *
     * @param str la cadena de caracteres a verificar
     * @return true si la cadena contiene caracteres especiales, false en caso contrario
     */
    public static boolean contieneCaracteresEspeciales(String str) {
        try {
            return !str.matches("[a-zA-Z ]+");
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Solicita al usuario seleccionar un nivel de actividad física.
     *
     * @return el nivel de actividad física seleccionado por el usuario
     */
    public static Nivel seleccionarNivel() {
        Scanner teclado = new Scanner(System.in);

        System.out.println("Por favor, ingresa tu nivel (PRINCIPIANTE, INTERMEDIO o AVANZADO):");
        Nivel nivel = null;

        do {
            try {
                String nivelStr = teclado.nextLine().toUpperCase();
                nivel = Nivel.valueOf(nivelStr);
            } catch (Exception e) {
                System.out.println("Nivel inválido. Por favor, ingresa uno de los siguientes niveles: PRINCIPIANTE, INTERMEDIO o AVANZADO.");
            }
        } while (nivel == null);
        return nivel;
    }

    /**
     * Solicita al usuario ingresar una fecha en formato AAAA-MM-DD.
     *
     * @return la fecha ingresada por el usuario
     */
    public static LocalDate pedirFecha() {
        Scanner teclado = new Scanner(System.in);
        LocalDate fecha = null;
        LocalDate fechaMinima = LocalDate.of(2000, 1, 1);
        LocalDate fechaActual = LocalDate.now();
        boolean fechaValida = false;
        do {
            try {
                System.out.print("Ingrese una fecha (formato AAAA-MM-DD): ");
                String input = teclado.nextLine();
                fecha = LocalDate.parse(input);
                if (fecha.isAfter(fechaMinima) && (fecha.isBefore(fechaActual) || fecha.isEqual(fechaActual))) {
                    fechaValida = true;
                } else {
                    System.out.println("Ingrese una fecha entre el año 2000 y hoy inclusive.");
                }
            } catch (Exception ex) {
                System.out.println("Ingrese una fecha válida en el formato especificado.");
            }
        } while (!fechaValida);
        return fecha;
    }

    /**
     * Solicita al usuario ingresar un mes y año en formato AAAA-MM.
     *
     * @return el año y mes ingresado por el usuario
     */
    public static YearMonth pedirAnoMes() {
        Scanner teclado = new Scanner(System.in);
        LocalDate fecha = null;
        YearMonth yearMonth = null;
        LocalDate fechaMinima = LocalDate.of(2000, 1, 1);
        LocalDate fechaActual = LocalDate.now();
        boolean fechaValida = false;
        do {
            try {
                System.out.print("Ingrese el mes y el año (formato AAAA-MM): ");
                String input = teclado.nextLine();
                yearMonth = YearMonth.parse(input);
                fecha = yearMonth.atDay(1);
                if (fecha.isAfter(fechaMinima) && (fecha.isBefore(fechaActual) || fecha.isEqual(fechaActual))) {
                    fechaValida = true;
                } else {
                    System.out.println("Ingrese un mes y año entre el año 2000 y el mes y año actual inclusive.");
                }
            } catch (Exception ex) {
                System.out.println("Ingrese un mes y año válidos en el formato especificado.");
            }
        } while (!fechaValida);
        return yearMonth;
    }
}
// Cambiar los pedir_datos para que no pidan infinitamente solo pidan una vez y luego salir al menu
// Crear las extepciones y realizar las impresiones correspondientes