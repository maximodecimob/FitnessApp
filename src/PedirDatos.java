import java.time.LocalDate;
import java.time.YearMonth;
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
        int num;
        try {
            num = teclado.nextInt();
            if (num < min) {
                System.out.print("Introduzca un número mayor que " + min);
            }
        } catch (Exception ex) {
            System.out.print("El número introducido no es correcto o no es un número");
            teclado.nextLine();
            num = -1;
        }
        return num;
    }

    /**
     * Solicita al usuario un número entero dentro de un rango específico.
     *
     * @param min   el valor mínimo permitido
     * @param max   el valor máximo permitido
     * @param menu  true si se muestra un menú con el rango permitido, false si solo se muestra un mensaje de error
     * @return el número entero ingresado por el usuario
     */
    public static int pedirNumeroIntMaxMin(int min, int max, boolean menu) {
        Scanner teclado = new Scanner(System.in);
        int num = -10; // Inicializamos a -10 porque nunca le vamos a dar -10 como mínimo o máximo
        try {
            num = teclado.nextInt();
            if (menu) {
                while (num < min || num > max) {
                    System.out.print("Introduzca un número en el rango " + min + "-" + max + ": ");
                    num = teclado.nextInt();
                }
            } else {
                if (num < min || num > max) {
                    System.out.println("El número debe encontrarse en el rango " + min + "-" + max);
                    num = -1;
                }
            }
        } catch (Exception e) {
            System.out.println("Número inválido.");
        }
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
        double num;
        try {
            num = teclado.nextDouble();
            if (num < min) {
                System.out.print("Introduzca un número mayor que " + min + " ");
            }
        } catch (Exception ex) {
            System.out.print("Error: Debe introducir un número mayor que " + min + " ");
            teclado.nextLine();
            num = -1;
        }
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
        try {
            palabra = teclado.nextLine();
            if (esNumero(palabra) || contieneCaracteresEspeciales(palabra)) {
                System.out.println(peticion + " no puede contener números ni caracteres especiales. Tampoco puede estar vacío");
                palabra = ""; // Restablecer la palabra para volver a solicitarla
            }
        } catch (Exception ex) {
            System.out.print("Introduzca " + peticion);
            teclado.nextLine();
            palabra = "";
        }
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

        System.out.println("Por favor, ingresa tu nivel (PRINCIPIANTE, INTERMEDIO o AVANZADO): ");
        Nivel nivel;
        try {
            String nivelStr = teclado.nextLine().toUpperCase();
            nivel = Nivel.valueOf(nivelStr);
        } catch (Exception e) {
            System.out.println("Nivel inválido. No es ninguno de los siguientes niveles: PRINCIPIANTE, INTERMEDIO o AVANZADO. ");
            nivel = null;
        }
        return nivel;
    }

    /**
     * Solicita al usuario ingresar una fecha en formato AAAA-MM-DD.
     *
     * @return la fecha ingresada por el usuario
     */
    public static LocalDate pedirFecha() {
        Scanner teclado = new Scanner(System.in);
        LocalDate fecha;
        LocalDate fechaMinima = LocalDate.of(2000, 1, 1);
        LocalDate fechaActual = LocalDate.now();
        try {
            System.out.print("Ingrese una fecha (formato AAAA-MM-DD): ");
            String input = teclado.nextLine();
            fecha = LocalDate.parse(input);
            if (!fecha.isAfter(fechaMinima) || (!fecha.isBefore(fechaActual) && !fecha.isEqual(fechaActual))) {
                System.out.println("La fecha no está entre el año 2000 y hoy inclusive. ");
                fecha = null;
            }
        } catch (Exception ex) {
            System.out.println("Fecha inválida en el formato especificado. ");
            fecha = null;
        }
        return fecha;
    }

    /**
     * Solicita al usuario ingresar un mes y año en formato AAAA-MM.
     *
     * @return el año y mes ingresado por el usuario
     */
    public static YearMonth pedirAnoMes() {
        Scanner teclado = new Scanner(System.in);
        LocalDate fecha;
        YearMonth yearMonth;
        LocalDate fechaMinima = LocalDate.of(2000, 1, 1);
        LocalDate fechaActual = LocalDate.now();
        try {
            System.out.print("Ingrese el mes y el año (formato AAAA-MM): ");
            String input = teclado.nextLine();
            yearMonth = YearMonth.parse(input);
            fecha = yearMonth.atDay(1);
            if (!fecha.isAfter(fechaMinima) || (!fecha.isBefore(fechaActual) && !fecha.isEqual(fechaActual))) {
                System.out.println("El mes y año no están entre el año 2000 y el mes y año actual inclusive. ");
                yearMonth = null;
            }
        } catch (Exception ex) {
            System.out.println("Ingrese un mes y año válidos en el formato especificado. ");
            yearMonth = null;
        }
        return yearMonth;
    }
}