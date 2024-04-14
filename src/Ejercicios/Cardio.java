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
    public String toString() {
        return "El ejercicio de Cardio '"+nombre+"' consistió en correr una distancia de "+distancia+" kilómetros " +
                "durante "+duracion+" minutos, con una intensidad de "+intensidad+" sobre 8, realizado en la fecha "+fecha+".";
    }

    @Override
    public double calcularCalorias(double peso) {
        double calorias;
        double met = distancia * intensidad * duracion;
        calorias = 0.0175 * peso * met;
        return calorias;
    }
}
