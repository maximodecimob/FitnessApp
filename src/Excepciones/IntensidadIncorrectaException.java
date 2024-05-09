package Excepciones;

/**
 * La clase IntensidadIncorrectaException es una excepción que se lanza cuando se proporciona una intensidad inválida para un ejercicio.
 * Esta excepción puede ser utilizada para manejar situaciones en las que la intensidad proporcionada no está dentro del rango válido.
 */
public class IntensidadIncorrectaException extends Exception {

    /**
     * Constructor para la clase IntensidadIncorrectaException.
     *
     * @param message el mensaje que describe la excepción
     */
    public IntensidadIncorrectaException(String message) {
        super(message);
    }
}
