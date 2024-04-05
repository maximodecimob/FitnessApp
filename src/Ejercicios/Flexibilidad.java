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
    public double calcualrCalorias(double peso) {
        return 0;
    }
}
