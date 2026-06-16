package graph;

/**
 * Representa un vértice (nodo) del grafo.
 *
 * Es genérico (tipo E) para poder representar cualquier tipo de dato:
 * enteros, letras, nombres de ciudades, etc.
 */
public class Vertex<E> {

    // Dato que identifica al vértice (ej: "Arequipa", 1, "A", etc.)
    private E data;

    // Constructor: crea un vértice con el dato indicado.
    public Vertex(E data) {
        this.data = data;
    }

    // Devuelve el dato del vértice.
    public E getData() {
        return data;
    }

    // Permite modificar el dato del vértice.
    public void setData(E data) {
        this.data = data;
    }

    // Representación en texto: simplemente el dato (ej: al imprimir un
    // Vertex<String> con dato "Arequipa", se imprime "Arequipa").
    @Override
    public String toString() {
        return data.toString();
    }

    /**
     * Compara dos vértices por su dato (no por referencia de memoria).
     * Esto es fundamental para que findAdjList(), contains(), remove(), etc.
     * puedan reconocer "el mismo vértice" aunque sean objetos distintos
     * en memoria, siempre que su "data" sea igual.
     */
    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object obj) {
        if (this == obj) return true; // misma referencia -> son iguales
        if (obj == null || getClass() != obj.getClass()) return false; // tipo distinto
        Vertex<E> other = (Vertex<E>) obj; //casteo de object a <E>
        return data.equals(other.data); // se compara el contenido
    }

    // hashCode coherente con equals: dos vértices "iguales" deben
    // producir el mismo hash (requisito de Java al usar equals()).
    @Override
    public int hashCode() {
        return data.hashCode();
    }
}