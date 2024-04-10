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

    public double getPeso() {
        return peso;
    }

    public void agregarEjercicioRealizado(Ejercicio ejercicio){
        boolean existe = false;
        for (Ejercicio value : ejerciciosRelacionados) {
            if (Objects.equals(value.getNombre(), ejercicio.getNombre())) {
                existe = true;
                break;
            }
        }
        if(!existe) {
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
            suma+= value.calcularCalorias(this.getPeso());
        }
        return (int)suma;
    }
    @Override
    public String toString(){
        //Hacer el toString
        return "";
    }

    public void eliminarEjercicio(String ejercicio) {
        ejerciciosRelacionados.removeIf(value -> Objects.equals(value.getNombre(), ejercicio));
    }

    public int getEjerciciosSize() {
        return ejerciciosRelacionados.size();
    }
}
