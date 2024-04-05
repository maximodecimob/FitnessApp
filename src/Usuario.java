import Ejercicios.Ejercicio;

import java.util.ArrayList;

public class Usuario {
    private String nombre;
    private int edad;
    private double peso;
    private Nivel nivel;
    private ArrayList<Ejercicio> ejerciciosRelacionados;

    public Usuario(String nombre, int edad, double peso, Nivel nivel) {
        //Hacer la correspondiente exception
        this.nombre = nombre;
        this.edad = edad;
        this.peso = peso;
        this.nivel = nivel;
    }
    public void agragarEjercicioRealizado(Ejercicio ejercicio){
        // Si no está añadido lo añade en la posición última más una
    }
    public int calcularPromedioIntensidad(){
        //Busca la intensidad de los ejercicios y hace la media
        return 0;
    }
    public int calcularConsumaCaloricoTotal(){
        // Suma todas las calorías quemadas
        return 0;
    }
    @Override
    public String toString(){
        //Hacer el toString
        return "";
    }
}
