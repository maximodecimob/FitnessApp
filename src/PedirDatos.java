import java.time.LocalDate;
import java.util.Objects;
import java.util.Scanner;

public class PedirDatos {
    public static int pedirNumeroIntMin(int min) {
        Scanner teclado = new Scanner(System.in);
        int num=0;
        do{
            try {
                num = teclado.nextInt();
                if(num < min){
                    System.out.print("Introduzca un número mayor que " + min);
                }
            }catch (Exception ex){
                System.out.print("Introduzca un número mayor que " + min);
                String texto = teclado.nextLine();
                num = min - 1;
            }
        }while(num < min);
        return num;
    }
    public static int pedirNumeroIntMaxMin(int min, int max) {
        Scanner teclado = new Scanner(System.in);
        int num=0;
        do{
            try {
                num = teclado.nextInt();
                if(num < min || num > max){
                    System.out.print("Introduzca un número en el rango " + min + "-" + max+":");
                }
            }catch (Exception ex){
                System.out.print("Introduzca un número en el rango " + min + "-" + max+":");
                String texto = teclado.nextLine();
                num = min - 1;
            }
        }while(num < min || num > max);
        return num;
    }
    public static double pedirNumeroDoubleMin(int min) {
        Scanner teclado = new Scanner(System.in);
        double num=0;
        do{
            try {
                num = teclado.nextDouble();
                if(num < min){
                    System.out.print("Introduzca un número mayor que " + min);
                }
            }catch (Exception ex){
                System.out.print("Introduzca un número mayor que " + min);
                String texto = teclado.nextLine();
                num = min - 1;
            }
        }while(num < min);
        return num;
    }
    public static double pedirNumeroDoubleMaxMin(int min, int max) {
        Scanner teclado = new Scanner(System.in);
        double num=0;
        do{
            try {
                num = teclado.nextDouble();
                if(num < min || num > max){
                    System.out.print("Introduzca un número en el rango " + min + "-" + max+":");
                }
            }catch (Exception ex){
                System.out.print("Introduzca un número en el rango " + min + "-" + max+":");
                String texto = teclado.nextLine();
                num = min - 1;
            }
        }while(num < min || num > max);
        return num;
    }
    public static String pedirPalabra(String peticion) {
        Scanner teclado = new Scanner(System.in);
        String palabra;
        do{
            try {
                palabra = teclado.nextLine();
                if (esNumero(palabra)) {
                    System.out.println(peticion+" no puede ser un número. Inténtelo de nuevo.");
                    palabra = ""; // Restablecer la palabra para volver a solicitarla
                }
            }catch (Exception ex){
                System.out.print("Introduzca "+peticion);
                String texto = teclado.nextLine();
                palabra = "";
            }
        }while(Objects.equals(palabra, ""));
        return palabra;
    }
    private static boolean esNumero(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public static Nivel seleccionarNivel() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Por favor, ingresa tu nivel (PRINCIPIANTE, INTERMEDIO o AVANZADO):");
        Nivel nivel = null;

        do {
            try {
                String nivelStr = scanner.nextLine().toUpperCase();
                nivel = Nivel.valueOf(nivelStr);
            } catch (Exception e) {
                System.out.println("Nivel inválido. Por favor, ingresa uno de los siguientes niveles: PRINCIPIANTE, INTERMEDIO o AVANZADO.");
            }
        } while (nivel == null);

        return nivel;
    }

    public static LocalDate pedirFecha() {
        Scanner scanner = new Scanner(System.in);
        LocalDate fecha = null;

        do {
            try {
                System.out.print("Ingrese una fecha (formato AAAA-MM-DD): ");
                String input = scanner.nextLine();
                fecha = LocalDate.parse(input);

                // Verificar si la fecha está entre el año 2000 y el día de hoy inclusive
                LocalDate fechaMinima = LocalDate.of(2000, 1, 1);
                LocalDate fechaActual = LocalDate.now();
                if (fecha.isAfter(fechaMinima) || fecha.isEqual(fechaActual)) {
                    break; // Salir del bucle si la fecha es válida
                } else {
                    System.out.println("Ingrese una fecha entre el año 2000 y hoy inclusive.");
                }
            } catch (Exception ex) {
                System.out.println("Ingrese una fecha válida en el formato especificado.");
            }
        } while (true);

        scanner.close();
        return fecha;
    }
}
