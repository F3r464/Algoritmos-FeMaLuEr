package graph;

import listlinked.ListLinked;

/**
 * Agrupa un vértice junto con su lista de adyacencia (sus aristas salientes).
 *
 * Esta clase es la pieza clave de la representación "lista de listas":
 * el grafo completo (GraphLink) es, en esencia, una ListLinked<AdjList<E>>,
 * es decir, una lista donde CADA elemento es "un vértice + su propia
 * lista de vecinos".
 */
public class AdjList<E> {

    // El vértice al que pertenece esta lista de adyacencia.
    private Vertex<E> vertex;

    // Lista de aristas que SALEN de este vértice (sus vecinos directos).
    private ListLinked<Edge<E>> edges;

    // Constructor: se crea la AdjList para un vértice dado, con su
    // lista de aristas inicialmente vacía.
    public AdjList(Vertex<E> vertex) {
        this.vertex = vertex;
        this.edges = new ListLinked<>();
    }

    // Devuelve el vértice asociado a esta lista de adyacencia.
    public Vertex<E> getVertex() {
        return vertex;
    }

    // Devuelve la lista de aristas (vecinos) de este vértice.
    public ListLinked<Edge<E>> getEdges() {
        return edges;
    }

    // Representación en texto: "vértice -> [vecino1, vecino2, ...]"
    @Override
    public String toString() {
        return vertex.toString() + " -> " + edges.toString();
    }
}