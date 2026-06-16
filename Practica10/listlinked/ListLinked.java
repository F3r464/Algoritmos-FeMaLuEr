package listlinked;

/**
 * Lista enlazada simple genérica.
 *
 * Es la estructura de datos base sobre la que se construye el grafo:
 * GraphLink usa una ListLinked para guardar la lista de vértices, y
 * cada vértice usa otra ListLinked para guardar su lista de aristas
 * (lista de adyacencia).
 *
 * Soporta: inserción al inicio, inserción al final, búsqueda,
 * eliminación por valor y acceso por índice.
 */
public class ListLinked<E> {

    // Referencia al primer nodo de la lista. Si la lista está vacía, head = null.
    private Node<E> head;

    // Cantidad de elementos actualmente almacenados en la lista.
    // Se mantiene actualizado en cada inserción/eliminación para
    // evitar tener que recorrer toda la lista cada vez que se
    // necesita saber su tamaño (size() es O(1) gracias a esto).
    private int size;

    // Constructor: lista vacía, sin nodos.
    public ListLinked() {
        this.head = null;
        this.size = 0;
    }

    // ──────────────────────────────────────────
    //  Inserción
    // ──────────────────────────────────────────

    /**
     * Inserta un nuevo dato al FINAL de la lista.
     * Complejidad: O(n) porque hay que recorrer hasta el último nodo
     * (esta lista no mantiene un puntero "tail").
     */
    public void addLast(E data) {
        // Se crea el nuevo nodo que contendrá el dato.
        Node<E> nuevo = new Node<>(data);

        if (head == null) {
            // Caso 1: la lista está vacía -> el nuevo nodo pasa a ser la cabeza.
            head = nuevo;
        } else {
            // Caso 2: hay que avanzar nodo por nodo hasta llegar al último
            // (aquel cuyo "next" es null), y enlazar el nuevo nodo ahí.
            Node<E> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = nuevo;
        }
        // Se actualiza el contador de elementos.
        size++;
    }

    /**
     * Inserta un nuevo dato al INICIO de la lista.
     * Complejidad: O(1) porque no requiere recorrer la lista.
     */
    public void addFirst(E data) {
        Node<E> nuevo = new Node<>(data);
        // El nuevo nodo apunta a lo que antes era la cabeza...
        nuevo.next = head;
        // ...y ahora el nuevo nodo se convierte en la cabeza.
        head = nuevo;
        size++;
    }

    // ──────────────────────────────────────────
    //  Acceso
    // ──────────────────────────────────────────

    /**
     * Devuelve el elemento ubicado en la posición "index" (la cabeza es índice 0).
     * Complejidad: O(n).
     */
    public E get(int index) {
        // Validación de rango: evita NullPointerException al recorrer de más.
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Índice fuera de rango: " + index);
        }
        Node<E> current = head;
        // Avanza "index" veces desde la cabeza.
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.getData();
    }

    /**
     * Devuelve el nodo cabeza de la lista.
     * Se expone para que clases externas (como GraphLink) puedan
     * recorrer la lista manualmente con punteros (current.next),
     * en lugar de depender únicamente de get(index), lo cual es
     * más eficiente cuando se necesita recorrido secuencial completo.
     */
    public Node<E> getHead() {
        return head;
    }

    // Devuelve cuántos elementos hay en la lista. O(1).
    public int size() {
        return size;
    }

    // Indica si la lista no tiene elementos.
    public boolean isEmpty() {
        return size == 0;
    }

    // ──────────────────────────────────────────
    //  Búsqueda
    // ──────────────────────────────────────────

    /**
     * Devuelve true si algún elemento de la lista es igual (equals) a "data".
     * Complejidad: O(n).
     */
    public boolean contains(E data) {
        Node<E> current = head;
        while (current != null) {
            if (current.getData().equals(data)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    // ──────────────────────────────────────────
    //  Eliminación
    // ──────────────────────────────────────────

    /**
     * Elimina la PRIMERA ocurrencia de "data" en la lista.
     * Devuelve true si se encontró y eliminó, false si no existía.
     * Complejidad: O(n).
     */
    public boolean remove(E data) {
        // Lista vacía: no hay nada que eliminar.
        if (head == null) return false;

        // Caso especial: el nodo a eliminar ES la cabeza.
        // Se "salta" la cabeza apuntando head directamente al segundo nodo.
        if (head.getData().equals(data)) {
            head = head.next;
            size--;
            return true;
        }

        // Caso general: se busca el nodo ANTERIOR al que se quiere eliminar,
        // porque en una lista simplemente enlazada es el anterior quien
        // necesita "saltarse" al nodo eliminado (current.next = current.next.next).
        Node<E> current = head;
        while (current.next != null) {
            if (current.next.getData().equals(data)) {
                current.next = current.next.next; // se desconecta el nodo
                size--;
                return true;
            }
            current = current.next;
        }
        // Se recorrió toda la lista y no se encontró el dato.
        return false;
    }

    // ──────────────────────────────────────────
    //  Representación
    // ──────────────────────────────────────────

    // Representación en texto de la lista, útil para depuración/impresión.
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> current = head;
        while (current != null) {
            sb.append(current.getData());
            if (current.next != null) sb.append(" -> ");
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }
}