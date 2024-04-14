package Ejercicios;

import java.time.LocalDate;

public class Fuerza extends Ejercicio{
    private double peso;
    private int repeticiones;
    public Fuerza(String nombre, int intensidad, LocalDate fecha,double peso,int repeticiones) {
        super(nombre, intensidad, fecha);
        //Hacer la correspondiente exception
        this.peso = peso;
        this.repeticiones = repeticiones;
    }

    @Override
    public String toString() {
        return "El ejercicio de Fuerza '" + nombre + "' " +
                "consistió en levantar un peso de " + peso + " kilogramos durante " +
                 repeticiones + " repeticiones, con una intensidad de " + intensidad + " sobre 8, realizado en la fecha " + fecha + ".";
    }

    @Override
    public double calcularCalorias(double peso) {
        double calorias;
        double met = this.peso * 0.1 * intensidad * repeticiones;
        calorias = 0.0175 * peso * met;
        return calorias;
    }
}
