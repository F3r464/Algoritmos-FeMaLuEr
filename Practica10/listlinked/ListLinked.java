package listlinked;

/**
 * Lista enlazada simple genérica.
 * Soporta inserción al inicio y al final, búsqueda, eliminación e iteración por índice.
 */
public class ListLinked<E> {
    private Node<E> head;
    private int size;

    public ListLinked() {
        this.head = null;
        this.size = 0;
    }

    // ──────────────────────────────────────────
    //  Inserción
    // ──────────────────────────────────────────

    /** Inserta al FINAL de la lista. */
    public void addLast(E data) {
        Node<E> nuevo = new Node<>(data);
        if (head == null) {
            head = nuevo;
        } else {
            Node<E> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = nuevo;
        }
        size++;
    }

    /** Inserta al INICIO de la lista. */
    public void addFirst(E data) {
        Node<E> nuevo = new Node<>(data);
        nuevo.next = head;
        head = nuevo;
        size++;
    }

    // ──────────────────────────────────────────
    //  Acceso
    // ──────────────────────────────────────────

    /** Devuelve el elemento en la posición indicada (base 0). */
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Índice fuera de rango: " + index);
        }
        Node<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.getData();
    }

    /** Devuelve el nodo cabeza (para iteración directa por punteros). */
    public Node<E> getHead() {
        return head;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    // ──────────────────────────────────────────
    //  Búsqueda
    // ──────────────────────────────────────────

    /** Devuelve true si la lista contiene el dato (usa equals). */
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
     * Elimina la PRIMERA ocurrencia del dato dado.
     * Devuelve true si se encontró y eliminó, false si no existía.
     */
    public boolean remove(E data) {
        if (head == null) return false;

        // Caso especial: el nodo a eliminar es la cabeza
        if (head.getData().equals(data)) {
            head = head.next;
            size--;
            return true;
        }

        Node<E> current = head;
        while (current.next != null) {
            if (current.next.getData().equals(data)) {
                current.next = current.next.next;
                size--;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    // ──────────────────────────────────────────
    //  Representación
    // ──────────────────────────────────────────

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