package Ejercicios;

import Excepciones.ConjuntoVacioException;
import Excepciones.IntensidadIncorrectaException;
import Excepciones.NumeroNegativoException;

import java.io.Serializable;
import java.time.LocalDate;
/**
 * La clase Cardio representa un tipo de ejercicio cardiovascular, que hereda de la clase Ejercicio.
 * Contiene atributos específicos como la distancia recorrida y la duración del ejercicio.
 */
public class Cardio extends Ejercicio implements Serializable {

    /** La distancia recorrida durante el ejercicio de cardio, en kilómetros. */
    private double distancia;

    /** La duración del ejercicio de cardio, en minutos. */
    private double duracion;

    /**
     * Constructor para la clase Cardio.
     *
     * @param nombre el nombre del ejercicio
     * @param intensidad la intensidad del ejercicio en una escala del 1 al 8
     * @param fecha la fecha en la que se realizó el ejercicio
     * @param distancia la distancia recorrida durante el ejercicio, en kilómetros
     * @param duracion la duración del ejercicio, en minutos
     */
    public Cardio(String nombre, int intensidad, LocalDate fecha, double distancia, double duracion) throws NumeroNegativoException, ConjuntoVacioException, IntensidadIncorrectaException {
        super(nombre, intensidad, fecha);
        if(distancia<0 || duracion<0){
            throw new NumeroNegativoException("El número introducido debe ser positivo.");
        }
        this.distancia = distancia;
        this.duracion = duracion;
    }

    /**
     * Obtiene una representación en forma de cadena del objeto Cardio.
     *
     * @return una cadena que describe el ejercicio de cardio
     */
    @Override
    public String toString() {
        String distanciaString = distancia == 1 ? "kilómetro" : "kilómetros";
        String duracionString = duracion == 1 ? "minuto" : "minutos";

        return "El ejercicio de Cardio '" + nombre + "' consistió en correr una distancia de " + distancia + " " + distanciaString +
                " durante " + duracion + " " + duracionString + ", con una intensidad de " + intensidad + " sobre 8, realizado en la fecha " + fecha + ".";    }

    /**
     * Calcula las calorías quemadas durante el ejercicio de cardio.
     *
     * @param peso el peso del individuo que realiza el ejercicio, en kilogramos
     * @return el número de calorías quemadas durante el ejercicio de cardio
     */
    @Override
    public double calcularCalorias(double peso) {
        double calorias;
        double met = distancia * intensidad * duracion;
        calorias = 0.0175 * peso * met;
        return calorias;
    }

    @Override
    public String getDatosInforme() {
        return distancia+" Km "+duracion+" min.";
    }
}

