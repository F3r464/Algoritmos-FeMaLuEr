package EJER.EJER02;

public class Node<E>{

    public E dato;
    public Node<E> left;
    public Node<E> right;

    public Node(E dato) {
        this(dato, null, null);
    }

    public Node(E dato, Node<E> left, Node<E> right) {

        this.dato = dato;
        this.left = left;
        this.right = right;
    }
}