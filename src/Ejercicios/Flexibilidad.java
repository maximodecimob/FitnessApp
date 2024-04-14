package Ejercicios;

import java.time.LocalDate;

public class Flexibilidad extends Ejercicio{
    private int repeticiones;

    public Flexibilidad(String nombre, int intensidad, LocalDate fecha,int repeticiones) {
        super(nombre, intensidad, fecha);
        //Hacer la correspondiente exception
        this.repeticiones = repeticiones;
    }

    @Override
    public String toString() {
        return "Para el ejercicio de Flexibilidad llamado '" + nombre + "'" +
                ", se realizaron " + repeticiones + " repeticiones con una intensidad de " + intensidad + " sobre 8" +
                ", en la fecha " + fecha + ".";

    }

    @Override
    public double calcularCalorias(double peso) {
        double calorias;
        double met = repeticiones * 0.1;
        calorias = 0.0175 * peso * met;
        return calorias;
    }
}
