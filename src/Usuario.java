import Ejercicios.Ejercicio;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Objects;

/**
 * La clase Usuario representa un usuario que realiza ejercicios físicos.
 * Contiene información sobre el nombre, edad, peso, nivel de actividad y los ejercicios relacionados del usuario.
 */
public class Usuario implements Serializable {

    /** El nombre del usuario. */
    private String nombre;

    /** La edad del usuario. */
    private int edad;

    /** El peso del usuario, en kilogramos. */
    private double peso;

    /** El nivel de actividad del usuario. */
    private Nivel nivel;

    /** La lista de ejercicios relacionados del usuario. */
    private ArrayList<Ejercicio> ejerciciosRelacionados;

    /**
     * Constructor para la clase Usuario.
     *
     * @param nombre el nombre del usuario
     * @param edad la edad del usuario
     * @param peso el peso del usuario, en kilogramos
     * @param nivel el nivel de actividad del usuario
     */
    public Usuario(String nombre, int edad, double peso, Nivel nivel) throws Exception {
        if(nivel == null){
            throw new Exception("Nivel");
        } else if (Objects.equals(nombre, "")) {
            throw new Exception("Nombre");
        } else if (edad<0) {
            throw new Exception("Edad");
        } else if (peso<0) {
            throw new Exception("Peso");
        }
        this.nombre = nombre;
        this.edad = edad;
        this.peso = peso;
        this.nivel = nivel;
        this.ejerciciosRelacionados = new ArrayList<>();
    }

    public int getEdad() {
        return edad;
    }

    public ArrayList<Ejercicio> getEjerciciosRelacionados() {
        return ejerciciosRelacionados;
    }

    /**
     * Obtiene el peso del usuario.
     *
     * @return el peso del usuario, en kilogramos
     */
    public double getPeso() {
        return peso;
    }

    public Nivel getNivel() {
        return nivel;
    }

    /**
     * Obtiene el nombre del usuario.
     *
     * @return el nombre del usuario
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Agrega un ejercicio realizado por el usuario a la lista de ejercicios relacionados.
     *
     * @param ejercicio el ejercicio realizado por el usuario
     */
    public void agregarEjercicioRealizado(Ejercicio ejercicio) {
        if (ejercicio != null) {
            this.ejerciciosRelacionados.add(ejercicio);
        }
    }

    /**
     * Calcula el promedio de intensidad de los ejercicios realizados por el usuario.
     *
     * @return el promedio de intensidad de los ejercicios realizados por el usuario
     */
    public int calcularPromedioIntensidad() {
        if (ejerciciosRelacionados.isEmpty()) {
            return 0;
        }

        int media = 0;
        for (Ejercicio ejercicio : ejerciciosRelacionados) {
            media += ejercicio.getIntensidad();
        }
        return media / ejerciciosRelacionados.size();
    }

    /**
     * Calcula el consumo calórico total de los ejercicios realizados por el usuario.
     *
     * @return el consumo calórico total de los ejercicios realizados por el usuario
     */
    public int calcularConsumoCaloricoTotal() {
        double suma = 0;
        for (Ejercicio value : ejerciciosRelacionados) {
            suma += value.calcularCalorias(this.peso);
        }
        return (int) suma;
    }

    /**
     * Obtiene una representación en forma de cadena del objeto Usuario.
     *
     * @return una cadena que describe al usuario y sus ejercicios relacionados
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Usuario: " + nombre + "\n");
        result.append("Edad: ").append(edad).append("\n");
        result.append("Peso: ").append(peso).append(" kg\n");
        result.append("Nivel: ").append(nivel).append("\n");
        result.append("Ejercicios Relacionados: \n");
        for (Ejercicio ejercicio : ejerciciosRelacionados) {
            result.append(ejercicio.toString()).append("\n");
        }
        return result.toString();
    }

    /**
     * Elimina un ejercicio de la lista de ejercicios relacionados del usuario.
     *
     * @param ejercicio el ejercicio a eliminar
     */
    public void eliminarEjercicio(Ejercicio ejercicio) {
        ejerciciosRelacionados.remove(ejercicio);
    }

    /**
     * Obtiene el tamaño de la lista de ejercicios relacionados del usuario.
     *
     * @return el tamaño de la lista de ejercicios relacionados
     */
    public int getEjerciciosSize() {
        return ejerciciosRelacionados.size();
    }

    /**
     * Busca un ejercicio en la lista de ejercicios relacionados del usuario por nombre y fecha.
     *
     * @param nombre el nombre del ejercicio
     * @param fecha la fecha en la que se realizó el ejercicio
     * @return el ejercicio encontrado o null si no se encuentra
     */
    public Ejercicio buscarEjercicio(String nombre, LocalDate fecha) {
        for (Ejercicio value : ejerciciosRelacionados) {
            if (Objects.equals(value.getNombre(), nombre) && Objects.equals(value.getFecha(), fecha)) {
                return value;
            }
        }
        return null;
    }

    /**
     * Obtiene una lista de ejercicios realizados después de una fecha específica.
     *
     * @param fecha la fecha de referencia
     * @return una lista de ejercicios realizados después de la fecha especificada
     */
    public ArrayList<Ejercicio> ejerciciosDespuesDeFecha(LocalDate fecha) {
        ArrayList<Ejercicio> ejercicios = new ArrayList<>();
        for (Ejercicio value : ejerciciosRelacionados) {
            if (value.getFecha().isAfter(fecha)) {
                ejercicios.add(value);
            }
        }
        return ejercicios;
    }

    /**
     * Obtiene una lista de ejercicios de un tipo específico.
     *
     * @param tipo el tipo de ejercicio a buscar
     * @return una lista de ejercicios del tipo especificado
     */
    public ArrayList<Ejercicio> obtenerEjerciciosPorTipo(Class<? extends Ejercicio> tipo) {
        ArrayList<Ejercicio> ejerciciosPorTipo = new ArrayList<>();
        for (Ejercicio ejercicio : ejerciciosRelacionados) {
            if (tipo.isInstance(ejercicio)) {
                ejerciciosPorTipo.add(ejercicio);
            }
        }
        return ejerciciosPorTipo;
    }

    /**
     * Obtiene una lista de ejercicios de un tipo específico realizados entre dos fechas.
     *
     * @param tipo el tipo de ejercicio a buscar
     * @param fecha la fecha de referencia
     * @return una lista de ejercicios del tipo especificado realizados entre las fechas especificadas
     */
    public ArrayList<Ejercicio> obtenerEjerciciosPorTipoFecha(Class<? extends Ejercicio> tipo, LocalDate fecha) {
        ArrayList<Ejercicio> ejercicios = new ArrayList<>();
        for (Ejercicio value : ejerciciosRelacionados) {
            if (value.getFecha().isAfter(fecha) && value.getFecha().isBefore(LocalDate.now()) && tipo.isInstance(value)) {
                ejercicios.add(value);
            }
        }
        return ejercicios;
    }

    /**
     * Obtiene una lista de ejercicios realizados en un año y mes específico.
     *
     * @param yearMonth el año y mes de referencia
     * @return una lista de ejercicios realizados en el año y mes especificados
     */
    public ArrayList<Ejercicio> ejerciciosEnAnoMes(YearMonth yearMonth) {
        ArrayList<Ejercicio> ejerciciosEnAnoMes = new ArrayList<>();
        for (Ejercicio ejercicio : ejerciciosRelacionados) {
            if (YearMonth.from(ejercicio.getFecha()).equals(yearMonth)) {
                ejerciciosEnAnoMes.add(ejercicio);
            }
        }
        return ejerciciosEnAnoMes;
    }

    /**
     * Obtiene una lista de ejercicios realizados entre dos fechas específicas.
     *
     * @param fecha1 la primera fecha de referencia
     * @param fecha2 la segunda fecha de referencia
     * @return una lista de ejercicios realizados entre las fechas especificadas
     */
    public ArrayList<Ejercicio> ejerciciosEntreFechas(LocalDate fecha1, LocalDate fecha2) {
        ArrayList<Ejercicio> ejercicios = new ArrayList<>();
        for (Ejercicio value : ejerciciosRelacionados) {
            if (value.getFecha().isAfter(fecha1) && value.getFecha().isBefore(fecha2)) {
                ejercicios.add(value);
            }
        }
        return ejercicios;
    }

    /**
     * Calcula la intensidad media de los ejercicios realizados después de una fecha específica.
     *
     * @param fecha la fecha de referencia
     * @return la intensidad media de los ejercicios realizados después de la fecha especificada
     */
    public double IntensidadMediaFecha(LocalDate fecha) {
        double intensidad = 0;
        int total = 0;
        for (Ejercicio ejercicio : ejerciciosRelacionados) {
            if (ejercicio.getFecha().isAfter(fecha)) {
                intensidad += ejercicio.getIntensidad();
                total += 1;
            }
        }
        return intensidad / total;
    }
}
