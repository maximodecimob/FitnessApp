package Ejercicios;

import java.time.LocalDate;

public class Cardio extends Ejercicio {
    private double distancia;
    private double duracion;

    public Cardio(String nombre, int intensidad, LocalDate fecha,double distancia,double duracion) {
        super(nombre, intensidad, fecha);
        //Hacer la correspondiente exception
        this.distancia = distancia;
        this.duracion = duracion;
    }

    @Override
    public double calcularCalorias(double peso) {
        return 0;
    }
}
