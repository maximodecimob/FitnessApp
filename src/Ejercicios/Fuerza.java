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
    public double calcualrCalorias(double peso) {
        return 0;
    }
}
