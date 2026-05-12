package EJER.EJER02;
import ACT.ACT04.*;

public interface BinarySearchTree<E extends Comparable<E>>{

    void insertar(E dato) throws ItemDuplicated;

    E buscar(E dato) throws ItemNoFound;

    void eliminar(E dato) throws ExceptionIsEmpty;

    boolean estaVacio();

    // EJERCICIO 02

    void destruirNodos();

    int contarTodosNodos();

    int contarNodos();

    int altura(E x);

    int amplitud(int nivel);
}