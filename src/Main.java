import Ejercicios.Cardio;

import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Inicializar las variables
        int menu=3,opcion,edad;
        double peso;
        boolean fitnessApp=true,primera=true,inicio=true;
        String nombre;
        Nivel nivel;
        RegistroUsuarios registroUsuarios = new RegistroUsuarios();
        Usuario usuario = null;
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
                menu = pedirNumeroInt(1, 3);
            }else{
                inicio = true;
            }
            switch (menu) {
                case (1):
                    System.out.println("Introduce el nombre: ");
                    nombre = pedirPalabra("el nombre");
                    System.out.println("Introduce el peso: ");
                    peso = pedirNumeroDouble(1,600);//Poner el mensaje que quiero que salga
                    System.out.println("Introduce la edad: ");
                    edad = pedirNumeroInt(0,150);//Poner el mensaje que quiero que salga
                    nivel = seleccionarNivel();
                    //Registrar al usuario
                    //Usuario registrado correctamente
                    break;
                case (2):
                    System.out.println("Introduce el nombre: ");
                    nombre = pedirPalabra("el nombre");
                    usuario = registroUsuarios.buscarUsuario(nombre);
                    if(usuario==null){
                        System.out.println("Necesitas iniciar sesión si no tienes usuario si tienes usuario introduce bien tu nombre");
                        System.out.println("Si no estas registrado y deseas registrarte presiona 1 ");
                        System.out.println("Si tienes usuario y has introducido mal tu nombre presiona 2 ");
                        menu = pedirNumeroInt(1,2);
                        inicio = false;
                    }else{

                    // Ver que el usuario existe y devolver el nombre
                    System.out.println("1. Registrar la realización de un nuevo ejercicio.");
                    System.out.println("2. Eliminar un ejercicio realizado dado su nombre y su fecha de realización.");
                    System.out.println("3. Mostrar el número de ejercicios total realizados y el gasto calórico total de ellos.");
                    System.out.println("4. Mostrar en número de ejercicios realizados desde una fecha hasta la actualidad, mostrando el gasto calórico total de esos ejercicios.");
                    System.out.println("5. Mostrar los ejercicios realizados de un determinado tipo, mostrando las calorías gastadas en la realización de cada uno.");
                    System.out.println("6. Mostrar los ejercicios realizados de un determinado tipo desde una fecha dada hasta la actualidad, con el gasto calórico total de ellos.");
                    System.out.println("7. Obtener todos los ejercicios, con toda su información, realizados en un determinado mes y año.");
                    System.out.println("8. Obtener todos los ejercicios, con toda su información, realizados entre dos fechas.");
                    System.out.println("9. Calcular la intensidad media de los ejercicios totales realizados.");
                    System.out.println("10. Calcular la intensidad media de los ejercicios realizados desde una fecha.");
                    System.out.println("11. Volver al menú anterior.");
                    System.out.println("Ingrese el número de la opción deseada:");

                    opcion = pedirNumeroInt(1,11);

                    switch (opcion) {
                        case 1:
                            // Lógica para registrar un nuevo ejercicio
                            break;
                        case 2:
                            // Lógica para eliminar un ejercicio realizado
                            break;
                        case 3:
                            // Lógica para mostrar el número de ejercicios realizados y el gasto calórico total
                            break;
                        case 4:
                            // Lógica para mostrar ejercicios realizados desde una fecha hasta la actualidad
                            break;
                        case 5:
                            // Lógica para mostrar ejercicios de un determinado tipo con el gasto calórico
                            break;
                        case 6:
                            // Lógica para mostrar ejercicios de un tipo desde una fecha hasta la actualidad
                            break;
                        case 7:
                            // Lógica para obtener ejercicios realizados en un mes y año específicos
                            break;
                        case 8:
                            // Lógica para obtener ejercicios realizados entre dos fechas
                            break;
                        case 9:
                            // Lógica para calcular la intensidad media de todos los ejercicios
                            break;
                        case 10:
                            // Lógica para calcular la intensidad media de ejercicios desde una fecha
                            break;
                        case 11:
                            System.out.println("Volviendo al menú anterior...");
                            break;
                        }
                    }
                    break;
                case (3):
                    fitnessApp = false;
                    break;
            }
        }while(fitnessApp);


    }
    private static int pedirNumeroInt(int min, int max) {
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
        } catch (NumberFormatException e) {
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
            } catch (IllegalArgumentException e) {
                System.out.println("Nivel inválido. Por favor, ingresa uno de los siguientes niveles: PRINCIPIANTE, INTERMEDIO o AVANZADO.");
            }
        } while (nivel == null);

        return nivel;
    }
}