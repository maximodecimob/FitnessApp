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
    public double calcularCalorias(double peso) {
        double calorias;
        double met = intensidad * 0.1;
        calorias = 0.0175 * peso * met;
        return calorias;
    }
}
