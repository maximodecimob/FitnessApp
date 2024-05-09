package Excepciones;

/**
 * La clase NumeroNegativoException es una excepción que se lanza cuando se proporciona un número negativo en un contexto donde se espera un número positivo.
 * Esta excepción puede ser utilizada para manejar situaciones en las que la presencia de un número negativo es problemática.
 */
public class NumeroNegativoException extends Exception {

    /**
     * Constructor para la clase NumeroNegativoException.
     *
     * @param message el mensaje que describe la excepción
     */
    public NumeroNegativoException(String message) {
        super(message);
    }
}
