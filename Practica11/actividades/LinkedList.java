package actividades;

public class LinkedList<T> {

    private class Node {
        T data;
        Node next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node head;
    private int size;

    public LinkedList() {
        head = null;
        size = 0;
    }

    public void add(T data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) { // Recorrido secuencial hasta hallar el último elemento de la lista
                current = current.next;
            }
            current.next = newNode; // Enlaza el nuevo nodo modificando el puntero del antiguo elemento final
        }
        size++;
    }

    public boolean remove(T data) {
        if (head == null) return false;

        if (head.data.equals(data)) {
            head = head.next; // Desplaza el puntero inicial al segundo nodo, aislando la cabeza anterior
            size--;
            return true;
        }

        Node current = head;
        while (current.next != null) {
            if (current.next.data.equals(data)) { // Evalúa de forma anticipada el valor del nodo posterior
                current.next = current.next.next; // Salto lógico: reconecta el nodo actual con el nieto
                size--;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public T get(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException("Índice: " + index);
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

    public int size() { return size; }
    public boolean isEmpty() { return size == 0; }

    public T find(java.util.function.Predicate<T> predicate) {
        Node current = head;
        while (current != null) {
            if (predicate.test(current.data)) return current.data; // Retorna la referencia si cumple la expresión Lambda
            current = current.next;
        }
        return null;
    }

    public boolean removeIf(java.util.function.Predicate<T> predicate) {
        if (head == null) return false;

        if (predicate.test(head.data)) {
            head = head.next;
            size--;
            return true;
        }

        Node current = head;
        while (current.next != null) {
            if (predicate.test(current.next.data)) { // Verifica el cumplimiento del criterio dinámico de búsqueda
                current.next = current.next.next; // Quita físicamente el nodo intermedio enlazando padre con nieto
                size--;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public String toString() {
        if (isEmpty()) return "[]";
        StringBuilder sb = new StringBuilder("[");
        Node current = head;
        while (current != null) {
            sb.append(current.data);
            if (current.next != null) sb.append(" -> ");
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }
}