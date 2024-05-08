package Excepciones;

public class ConjuntoVacioException extends Exception{
    public ConjuntoVacioException() {
    }

    public ConjuntoVacioException(String message) {
        super(message);
    }
}
