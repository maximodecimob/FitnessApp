package Ejercicios;

import java.io.Serializable;
import java.time.LocalDate;
/**
 * La clase Fuerza representa un tipo de ejercicio diseñado para aumentar la fuerza muscular.
 * Hereda de la clase Ejercicio e incluye atributos específicos como el peso levantado y el número de repeticiones.
 */
public class Fuerza extends Ejercicio implements Serializable {

    /** El peso levantado durante el ejercicio de fuerza, en kilogramos. */
    private double peso;

    /** El número de repeticiones del ejercicio de fuerza. */
    private int repeticiones;

    /**
     * Constructor para la clase Fuerza.
     *
     * @param nombre el nombre del ejercicio de fuerza
     * @param intensidad la intensidad del ejercicio en una escala del 1 al 8
     * @param fecha la fecha en la que se realizó el ejercicio
     * @param peso el peso levantado durante el ejercicio, en kilogramos
     * @param repeticiones el número de repeticiones del ejercicio de fuerza
     */
    public Fuerza(String nombre, int intensidad, LocalDate fecha, double peso, int repeticiones) throws Exception {
        super(nombre, intensidad, fecha);
        if(peso<0){
            throw new Exception("Peso");
        } else if (repeticiones<0) {
            throw new Exception("Repeticiones");
        }
        this.peso = peso;
        this.repeticiones = repeticiones;
    }

    /**
     * Obtiene una representación en forma de cadena del objeto Fuerza.
     *
     * @return una cadena que describe el ejercicio de fuerza
     */
    @Override
    public String toString() {
        if (repeticiones == 1) {
            return "El ejercicio de Fuerza '" + nombre + "' " +
                    "consistió en levantar un peso de " + peso + " kilogramos durante " +
                    repeticiones + " repetición, con una intensidad de " + intensidad + " sobre 8, realizado en la fecha " + fecha + ".";
        } else {
            return "El ejercicio de Fuerza '" + nombre + "' " +
                    "consistió en levantar un peso de " + peso + " kilogramos durante " +
                    repeticiones + " repeticiones, con una intensidad de " + intensidad + " sobre 8, realizado en la fecha " + fecha + ".";
        }    }

    /**
     * Calcula las calorías quemadas durante el ejercicio de fuerza.
     *
     * @param peso el peso del individuo que realiza el ejercicio, en kilogramos
     * @return el número de calorías quemadas durante el ejercicio de fuerza
     */
    @Override
    public double calcularCalorias(double peso) {
        double calorias;
        double met = this.peso * 0.1 * intensidad * repeticiones;
        calorias = 0.0175 * peso * met;
        return calorias;
    }

    @Override
    public String getDatosInforme() {
        if (repeticiones == 1) {
            return peso + " Kg " + repeticiones + " repetición.";
        } else {
            return peso + " Kg " + repeticiones + " repeticiones.";
        }
    }
}

