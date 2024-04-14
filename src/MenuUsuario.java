import Ejercicios.Cardio;
import Ejercicios.Ejercicio;
import Ejercicios.Flexibilidad;
import Ejercicios.Fuerza;

import java.time.LocalDate;
import java.util.ArrayList;

public class MenuUsuario {
    public static Usuario menuUsuario(Usuario usuario){
        boolean menuUsu = true;
        usuario.agregarEjercicioRealizado(new Cardio("Correr", 2, LocalDate.of(2023, 1, 1), 20, 15));
        usuario.agregarEjercicioRealizado(new Cardio("Nadar", 3, LocalDate.of(2023, 2, 1), 30, 12));
        usuario.agregarEjercicioRealizado(new Cardio("Saltar la cuerda", 4, LocalDate.of(2023, 3, 1), 25, 10));
        usuario.agregarEjercicioRealizado(new Flexibilidad("Estiramiento de piernas", 5, LocalDate.of(2023, 4, 1), 10));
        usuario.agregarEjercicioRealizado(new Flexibilidad("Estiramiento de brazos", 3, LocalDate.of(2023, 5, 1), 8));
        usuario.agregarEjercicioRealizado(new Flexibilidad("Estiramiento de espalda", 4, LocalDate.of(2023, 6, 1), 12));
        usuario.agregarEjercicioRealizado(new Fuerza("Levantamiento de pesas", 5, LocalDate.of(2023, 7, 1), 50.0, 10));
        usuario.agregarEjercicioRealizado(new Fuerza("Press de banca", 4, LocalDate.of(2023, 8, 1), 60.0, 8));
        usuario.agregarEjercicioRealizado(new Fuerza("Sentadillas", 3, LocalDate.of(2023, 9, 1), 70.0, 12));
        int menu;
        do{
            System.out.println();
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------");
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
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println();
            System.out.println("Ingrese el número de la opción deseada:");

            menu = PedirDatos.pedirNumeroIntMaxMin(1,11);

            switch (menu) {
                case 1:
                    anadirEjercicio(usuario);
                    break;
                case 2:
                    eliminarEjercicio(usuario);
                    break;
                case 3:
                    ejerciciosRealizadosCaloriasTotal(usuario);
                    break;
                case 4:
                    ejerciciosRealizadosDedeFecha(usuario);
                    break;
                case 5:
                    ejerciciosDeUnTipoConGastoCalorico(usuario);
                    break;
                case 6:
                    ejerciciosDeUnTipoEntreFecha(usuario);
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

    private static void ejerciciosRealizadosDedeFecha(Usuario usuario) {
        System.out.println("Para mostrarte todos los ejercicios realizados desde un dia introduce el dia: ");
        LocalDate fecha = PedirDatos.pedirFecha();
        ArrayList<Ejercicio> ejercicios = usuario.ejerciciosDespuesDeFecha(fecha);
        for(Ejercicio value: ejercicios){
            System.out.println(value.toString());
        }
    }

    private static void ejerciciosRealizadosCaloriasTotal(Usuario usuario) {
        System.out.println("Has realizado "+usuario.getEjerciciosSize()+" ejercicios");
        System.out.println("Con unas calorias totales de "+usuario.calcularConsumoCaloricoTotal());
    }

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

    private static void anadirFuerza(Usuario usuario, String nombre, int intensidad, LocalDate fecha) {
        System.out.println("Introduce el peso levantado en kilogramos ");
        double peso = PedirDatos.pedirNumeroDoubleMin(0);
        System.out.println("Introduce el número de repeticiones ");
        int repes = PedirDatos.pedirNumeroIntMin(0);
        Fuerza fuerza = new Fuerza(nombre,intensidad,fecha,peso,repes);
        usuario.agregarEjercicioRealizado(fuerza);
    }
    private static void anadirFlexibilidad(Usuario usuario, String nombre, int intensidad, LocalDate fecha) {
        System.out.println("Introduce las repeticiones ");
        int repeticiones = PedirDatos.pedirNumeroIntMin(0);
        Flexibilidad flexibilidad = new Flexibilidad(nombre,intensidad,fecha,repeticiones);
        usuario.agregarEjercicioRealizado(flexibilidad);
    }
    private static void anadirCardio(Usuario usuario,String nombre,int intensidad,LocalDate fecha) {
        System.out.println("Ingrese la distancia en kilómetros ");
        double distancia = PedirDatos.pedirNumeroDoubleMin(0);
        System.out.println("Ingrese la duración en minutos");
        double duracion = PedirDatos.pedirNumeroDoubleMin(0);
        Cardio cardio = new Cardio(nombre,intensidad,fecha,distancia,duracion);
        usuario.agregarEjercicioRealizado(cardio);
    }
}
