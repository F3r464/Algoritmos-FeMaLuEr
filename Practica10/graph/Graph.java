package graph;

import java.util.ArrayList;

/**
 * TAD Graph: Interfaz genérica que define las operaciones básicas de un grafo.
 */
public interface Graph<E> {
    void insertVertex(E data);
    void insertEdge(E origin, E destination);
    void insertEdgeWeight(E origin, E destination, int weight);
    boolean removeVertex(E data);
    boolean removeEdge(E origin, E destination);
    boolean searchVertex(E data);
    boolean searchEdge(E origin, E destination);
    ArrayList<E> adjacentVertices(E data);
}