import Ejercicios.Cardio;
import Ejercicios.Flexibilidad;
import Ejercicios.Fuerza;

import java.time.LocalDate;

public class MenuUsuario {
    public static Usuario menuUsuario(Usuario usuario){
        boolean menuUsu = true;
        int menu;
        do{
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

            menu = PedirDatos.pedirNumeroIntMaxMin(1,11);

            switch (menu) {
                case 1:
                    usuario = anadirEjercicio(usuario);
                    break;
                case 2:
                    usuario = eliminarEjercicio(usuario);
                    break;
                case 3:
                    ejerciciosRealizadosCaloriasTotal(usuario);
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
                    menuUsu=false;
                    break;
            }
        }while(menuUsu);
        return usuario;
    }

    private static void ejerciciosRealizadosCaloriasTotal(Usuario usuario) {
        System.out.println("Has realizado "+usuario.getEjerciciosSize()+" ejercicios");
        System.out.println("Con unas calorias totales de "+usuario.calcularConsumoCaloricoTotal());
    }

    private static Usuario eliminarEjercicio(Usuario usuario) {
        System.out.println("Introduce el nombre del ejercicio que quieras eliminar ");
        String ejercicio = PedirDatos.pedirPalabra("el ejercicio");
        usuario.eliminarEjercicio(ejercicio);
        return usuario;
    }
    private static Usuario anadirEjercicio(Usuario usuario){
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
                System.out.println("Ingrese la distancia en kilómetros ");
                double distancia = PedirDatos.pedirNumeroDoubleMin(0);
                System.out.println("Ingrese la duración en minutos");
                double duracion = PedirDatos.pedirNumeroDoubleMin(0);
                Cardio cardio = new Cardio(nombre,intensidad,fecha,distancia,duracion);
                usuario.agregarEjercicioRealizado(cardio);
                break;
            case 2:
                System.out.println("Introduce las repeticiones ");
                int repeticiones = PedirDatos.pedirNumeroIntMin(0);
                Flexibilidad flexibilidad = new Flexibilidad(nombre,intensidad,fecha,repeticiones);
                usuario.agregarEjercicioRealizado(flexibilidad);
                break;
            case 3:
                System.out.println("Introduce el peso levantado en kilogramos ");
                double peso = PedirDatos.pedirNumeroDoubleMin(0);
                System.out.println("Introduce el número de repeticiones ");
                int repes = PedirDatos.pedirNumeroIntMin(0);
                Fuerza fuerza = new Fuerza(nombre,intensidad,fecha,peso,repes);
                usuario.agregarEjercicioRealizado(fuerza);
                break;
        }
        return usuario;
    }
}
