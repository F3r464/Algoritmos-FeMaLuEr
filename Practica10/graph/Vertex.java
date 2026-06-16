package graph;

/**
 * Representa un vértice (nodo) del grafo.
 * Almacena el dato genérico que identifica al vértice.
 */
public class Vertex<E> {
    private E data;

    public Vertex(E data) {
        this.data = data;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return data.toString();
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Vertex<E> other = (Vertex<E>) obj;
        return data.equals(other.data);
    }

    @Override
    public int hashCode() {
        return data.hashCode();
    }
}