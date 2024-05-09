package Excepciones;

/**
 * La clase ConjuntoVacioException es una excepción que se lanza cuando se encuentra un conjunto vacío o nulo en un contexto donde se espera un conjunto con al menos un elemento.
 * Esta excepción puede ser utilizada para manejar situaciones en las que la ausencia de elementos en un conjunto es problemática.
 */
public class ConjuntoVacioException extends Exception {

    /**
     * Constructor para la clase ConjuntoVacioException.
     *
     * @param message el mensaje que describe la excepción
     */
    public ConjuntoVacioException(String message) {
        super(message);
    }
}
