package listlinked;
 
/**
 * Nodo genérico utilizado por la lista enlazada (ListLinked).
 *
 * Cada nodo guarda un dato de tipo E y una referencia ("next") al
 * siguiente nodo de la cadena. Si "next" es null, significa que es
 * el último nodo de la lista.
 */
public class Node<E> {
 
    // Dato almacenado en el nodo (puede ser un Vertex, un Edge, un AdjList, etc.)
    private E data;
 
    // Apuntador/referencia al siguiente nodo de la lista.
    // Es package-private/public para que ListLinked y GraphLink puedan
    // recorrer la cadena de nodos directamente con "current.next".
    public Node<E> next;
 
    // Constructor: crea un nodo con un dato y next = null (todavía sin enlazar).
    public Node(E data) {
        this.data = data;
        this.next = null;
    }
 
    // Devuelve el dato almacenado en este nodo.
    public E getData() {
        return data;
    }
 
    // Permite cambiar el dato almacenado en este nodo.
    public void setData(E data) {
        this.data = data;
    }
}