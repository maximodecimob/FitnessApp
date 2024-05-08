package Ejercicios;

import Excepciones.ConjuntoVacioException;
import Excepciones.IntensidadIncorrectaException;
import Excepciones.NumeroNegativoException;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * La clase Flexibilidad representa un tipo de ejercicio de estiramiento para mejorar la flexibilidad del cuerpo.
 * Hereda de la clase Ejercicio e incluye atributos específicos como el número de repeticiones.
 */
public class Flexibilidad extends Ejercicio implements Serializable {

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
    public Flexibilidad(String nombre, int intensidad, LocalDate fecha, int repeticiones) throws NumeroNegativoException, ConjuntoVacioException, IntensidadIncorrectaException {
        super(nombre, intensidad, fecha);
        if(repeticiones<0){
            throw new NumeroNegativoException("Las repeticiones deben ser un número positivo");
        }
        this.repeticiones = repeticiones;
    }

    /**
     * Obtiene una representación en forma de cadena del objeto Flexibilidad.
     *
     * @return una cadena que describe el ejercicio de flexibilidad
     */
    @Override
    public String toString() {
        if (repeticiones == 1) {
            return "Para el ejercicio de Flexibilidad llamado '" + nombre + "'" +
                    ", se realizó " + repeticiones + " repetición con una intensidad de " + intensidad + " sobre 8" +
                    ", en la fecha " + fecha + ".";
        } else {
            return "Para el ejercicio de Flexibilidad llamado '" + nombre + "'" +
                    ", se realizaron " + repeticiones + " repeticiones con una intensidad de " + intensidad + " sobre 8" +
                    ", en la fecha " + fecha + ".";
        }
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

    @Override
    public String getDatosInforme() {
        if (repeticiones == 1) {
            return repeticiones + " repetición.";
        } else {
            return repeticiones + " repeticiones.";
        }
    }
}
