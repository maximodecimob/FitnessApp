package Ejercicios;

import java.time.LocalDate;

/**
 * La clase Flexibilidad representa un tipo de ejercicio de estiramiento para mejorar la flexibilidad del cuerpo.
 * Hereda de la clase Ejercicio e incluye atributos específicos como el número de repeticiones.
 */
public class Flexibilidad extends Ejercicio {

    /** El número de repeticiones del ejercicio de flexibilidad. */
    private int repeticiones;

    /**
     * Constructor para la clase Flexibilidad.
     *
     * @param nombre el nombre del ejercicio de flexibilidad
     * @param intensidad la intensidad del ejercicio en una escala del 1 al 8
     * @param fecha la fecha en la que se realizó el ejercicio
     * @param repeticiones el número de repeticiones del ejercicio de flexibilidad
     */
    public Flexibilidad(String nombre, int intensidad, LocalDate fecha, int repeticiones) {
        super(nombre, intensidad, fecha);
        this.repeticiones = repeticiones;
    }

    /**
     * Obtiene una representación en forma de cadena del objeto Flexibilidad.
     *
     * @return una cadena que describe el ejercicio de flexibilidad
     */
    @Override
    public String toString() {
        return "Para el ejercicio de Flexibilidad llamado '" + nombre + "'" +
                ", se realizaron " + repeticiones + " repeticiones con una intensidad de " + intensidad + " sobre 8" +
                ", en la fecha " + fecha + ".";
    }

    /**
     * Calcula las calorías quemadas durante el ejercicio de flexibilidad.
     *
     * @param peso el peso del individuo que realiza el ejercicio, en kilogramos
     * @return el número de calorías quemadas durante el ejercicio de flexibilidad
     */
    @Override
    public double calcularCalorias(double peso) {
        double calorias;
        double met = repeticiones * 0.1;
        calorias = 0.0175 * peso * met;
        return calorias;
    }
}
