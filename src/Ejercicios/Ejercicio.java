package Ejercicios;

import java.time.LocalDate;

public abstract class Ejercicio{
    protected String nombre;
    protected int intensidad;
    protected LocalDate fecha;

    public LocalDate getFecha() {
        return fecha;
    }

    public int getIntensidad() {
        return intensidad;
    }
    public String getNombre() {
        return nombre;
    }

    public Ejercicio(String nombre, int intensidad, LocalDate fecha) {
        this.nombre = nombre;
        this.intensidad = intensidad;
        this.fecha = fecha;
    }
    public abstract double calcularCalorias(double peso);

    @Override
    public String toString() {
        return "Este ejercicio de nombre '" + nombre + "' " +
                "se realizó con una intensidad de " + intensidad + "sobre 8, en la fecha " + fecha + ".";
    }
}
