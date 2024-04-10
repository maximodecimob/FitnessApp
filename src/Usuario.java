import Ejercicios.Ejercicio;

import java.util.ArrayList;
import java.util.Objects;

public class Usuario {
    private String nombre;
    private int edad;
    private double peso;
    private Nivel nivel;
    private ArrayList<Ejercicio> ejerciciosRelacionados;

    public Usuario(String nombre, int edad, double peso, Nivel nivel){
        this.nombre = nombre;
        this.edad = edad;
        this.peso = peso;
        this.nivel = nivel;
        this.ejerciciosRelacionados = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void agregarEjercicioRealizado(Ejercicio ejercicio){
        if(ejercicio != null) {
            this.ejerciciosRelacionados.add(ejercicio);
        }
    }
    public int calcularPromedioIntensidad(){
        int media=0;

        for(Ejercicio value: ejerciciosRelacionados){
            media+=value.getIntensidad();
        }
        media=media/ejerciciosRelacionados.size();
        return media;
    }
    public int calcularConsumoCaloricoTotal(){
        double suma=0;
        for(Ejercicio value: ejerciciosRelacionados){
            suma+= value.calcularCalorias(this.peso);
            //aqui no entiendo por qué has puesto getPeso() en vez de this.peso
        }
        return (int)suma;
    }
    @Override
    public String toString(){
        //Hacer el toString
        return "";
    }

    public void eliminarEjercicio(String ejercicio) {
        Ejercicio e = buscarEjercicio(ejercicio);
        if(e != null){
            ejerciciosRelacionados.remove(e);
        }
    }

    public int getEjerciciosSize() {
        return ejerciciosRelacionados.size();
    }

    public Ejercicio buscarEjercicio(String nombre) {
        for(Ejercicio value : ejerciciosRelacionados){
            if(Objects.equals(value.getNombre(), nombre)){
                return value;
            }
        }
        return null;
    }
}
