import Ejercicios.Ejercicio;

import java.time.LocalDate;
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

    public double getPeso() {
        return peso;
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

    public void eliminarEjercicio(Ejercicio ejercicio) {
        ejerciciosRelacionados.remove(ejercicio);
    }

    public int getEjerciciosSize() {
        return ejerciciosRelacionados.size();
    }

    public Ejercicio buscarEjercicio(String nombre,LocalDate fecha) {
        for(Ejercicio value : ejerciciosRelacionados){
            if(Objects.equals(value.getNombre(), nombre) && Objects.equals(value.getFecha(), fecha)){
                return value;
            }
        }
        return null;
    }
    public ArrayList<Ejercicio> ejerciciosDespuesDeFecha(LocalDate fecha){
        ArrayList<Ejercicio> ejercicios = new ArrayList<>();
        for(Ejercicio value: ejerciciosRelacionados){
            if(value.getFecha().isAfter(fecha)){
                ejercicios.add(value);
            }
        }
        return ejercicios;
    }
    public ArrayList<Ejercicio> obtenerEjerciciosPorTipo(Class<? extends Ejercicio> tipo) {
        ArrayList<Ejercicio> ejerciciosPorTipo = new ArrayList<>();
        for (Ejercicio ejercicio : ejerciciosRelacionados) {
            if (tipo.isInstance(ejercicio)) {
                ejerciciosPorTipo.add(ejercicio);
            }
        }
        return ejerciciosPorTipo;
    }

    public ArrayList<Ejercicio> obtenerEjerciciosPorTipoFecha(Class<? extends Ejercicio> tipo, LocalDate fecha) {
        ArrayList<Ejercicio> ejercicios = new ArrayList<>();
        for(Ejercicio value: ejerciciosRelacionados){
            if(value.getFecha().isAfter(fecha)&&value.getFecha().isBefore(LocalDate.now())&&tipo.isInstance(value)){
                ejercicios.add(value);
            }
        }
        return ejercicios;
    }
}
