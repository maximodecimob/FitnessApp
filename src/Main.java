import Ejercicios.Cardio;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Inicializar las variables
        int menu,minMenu=1,maxMenu=3;
        boolean fitnessApp=true;
        do {
            System.out.println("Bienvenido a FitnessApp, ¿Qué desea hacer?");
            System.out.println("1. Registrar usuario");
            System.out.println("2. Iniciar sesión");
            System.out.println("3. Salir");
            menu = pedirNumero(minMenu, maxMenu);
            switch (menu) {
                case (1):
                    // Si ya hay usuarios solo se añade uno si no está ya añadido
                    // Si no hay se crea y se añade uno
                    break;
                case (2):
                    //1. Registrar la realización de un nuevo ejercicio.
                    //2. Eliminar un ejercicio realizado dado su nombre y su fecha de realización.
                    //3. Mostrar el número de ejercicios total realizados y el gasto calórico total de ellos.
                    //4. Mostrar en número de ejercicios realizados desde una fecha hasta la actualidad, mostrando el
                    //gasto calórico total de esos ejercicios.
                    //5. Mostrar los ejercicios realizados de un determinado tipo, mostrando las calorías gastadas en la
                    //realización de cada uno.
                    //6. Mostrar los ejercicios realizados de un determinado tipo desde una fecha dada hasta la actualidad,
                    //    con el gasto calórico total de ellos.
                    //7. Obtener todos los ejercicios, con toda su información, realizados en un determinado mes y año.
                    //8. Obtener todos los ejercicios, con toda su información, realizados entre dos fechas.
                    //9. Calcular la intensidad media de los ejercicios totales realizados.
                    //10. Calcular la intensidad media de los ejercicios realizados desde una fecha.
                    //11. Volver al menú anterior
                    break;
                case (3):
                    fitnessApp = false;
                    break;
            }
        }while(fitnessApp);


    }
    private static int pedirNumero(int min, int max) {
        Scanner teclado = new Scanner(System.in);
        int num=0;
        do{
            try {
                num = teclado.nextInt();
            }catch (InputMismatchException ex){
                System.out.print("Introduzca un número en el rango " + min + "-" + max+":");
                String texto = teclado.nextLine();
                num = min - 1;
            }
        }while(num < min || num > max);
        return num;
    }
}