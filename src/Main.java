import Ejercicios.Cardio;

import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ProgramaPrincipal.menuGeneral();
    }

    private static int pedirNumeroIntMin(int min) {
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
    private static int pedirNumeroIntMaxMin(int min, int max) {
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
    private static double pedirNumeroDouble(int min, int max) {
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
    private static String pedirPalabra(String peticion) {
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
}
