package Ejercicios;

import Excepciones.ConjuntoVacioException;
import Excepciones.IntensidadIncorrectaException;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
/**
 * La clase abstracta Ejercicio representa un tipo genérico de ejercicio físico.
 * Contiene atributos comunes a todos los tipos de ejercicios, como nombre, intensidad y fecha.
 */
public abstract class Ejercicio implements Serializable {

    /** El nombre del ejercicio. */
    protected String nombre;

    /** La intensidad del ejercicio en una escala del 1 al 8. */
    protected int intensidad;

    /** La fecha en la que se realizó el ejercicio. */
    protected LocalDate fecha;

    /**
     * Constructor para la clase Ejercicio.
     *
     * @param nombre el nombre del ejercicio
     * @param intensidad la intensidad del ejercicio en una escala del 1 al 8
     * @param fecha la fecha en la que se realizó el ejercicio
     * @throws IntensidadIncorrectaException si la intensidad no está en el rango correcto
     * @throws ConjuntoVacioException si el nombre o la fecha son nulos
     */
    public Ejercicio(String nombre, int intensidad, LocalDate fecha) throws IntensidadIncorrectaException, ConjuntoVacioException {
        if(intensidad > 8 || intensidad < 1){
            throw new IntensidadIncorrectaException("Intensidad incorrecta. Debe ser un número entero entre 1 y 8 inclusive.");
        } else if (Objects.equals(nombre, "") || fecha == null) {
            throw new ConjuntoVacioException("No ha introducido nada en fecha o en nombre.");
        }
        this.nombre = nombre;
        this.intensidad = intensidad;
        this.fecha = fecha;
    }

    /**
     * Obtiene la fecha en la que se realizó el ejercicio.
     *
     * @return la fecha en la que se realizó el ejercicio
     */
    public LocalDate getFecha() {
        return fecha;
    }

    /**
     * Obtiene la intensidad del ejercicio.
     *
     * @return la intensidad del ejercicio
     */
    public int getIntensidad() {
        return intensidad;
    }

    /**
     * Obtiene el nombre del ejercicio.
     *
     * @return el nombre del ejercicio
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Calcula las calorías quemadas durante el ejercicio.
     *
     * @param peso el peso del individuo que realiza el ejercicio, en kilogramos
     * @return el número de calorías quemadas durante el ejercicio
     */
    public abstract double calcularCalorias(double peso);

    /**
     * Obtiene los datos del ejercicio para un informe.
     *
     * @return una cadena con los datos relevantes del ejercicio para un informe
     */
    public abstract String getDatosInforme();

    /**
     * Obtiene una representación en forma de cadena del objeto Ejercicio.
     *
     * @return una cadena que describe el ejercicio
     */
    @Override
    public String toString() {
        return "Este ejercicio de nombre '" + nombre + "' " +
                "se realizó con una intensidad de " + intensidad + " sobre 8, en la fecha " + fecha + ".";
    }
}
