package exceptions;

// Excepción personalizada para errores relacionados con operaciones en el Árbol B.
// Se lanza cuando se intenta realizar una operación inválida sobre la estructura.

public class TreeException extends Exception {
 
    public TreeException(String message) {
        super(message);
    }
}