package graph;

import listlinked.ListLinked;

/**
 * Agrupa un vértice con su lista de aristas adyacentes.
 * Es la unidad fundamental de la representación por listas de adyacencia.
 */
public class AdjList<E> {
    private Vertex<E> vertex;
    private ListLinked<Edge<E>> edges;

    public AdjList(Vertex<E> vertex) {
        this.vertex = vertex;
        this.edges = new ListLinked<>();
    }

    public Vertex<E> getVertex() {
        return vertex;
    }

    public ListLinked<Edge<E>> getEdges() {
        return edges;
    }

    @Override
    public String toString() {
        return vertex.toString() + " -> " + edges.toString();
    }
}