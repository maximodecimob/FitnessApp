package Ejercicios;

import java.time.LocalDate;

public abstract class Ejercicio{
    protected String nombre;
    protected int intensidad;
    protected LocalDate fecha;

    public Ejercicio(String nombre, int intensidad, LocalDate fecha) {
        //Hacer la correspondiente exception
        this.nombre = nombre;
        this.intensidad = intensidad;
        this.fecha = fecha;
    }
    public abstract double calcularCalorias(double peso);
    @Override
    public String toString(){
        //Hacer un toString normal
        return "";
    }
}
