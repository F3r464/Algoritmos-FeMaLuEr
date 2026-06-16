package graph;

import java.util.ArrayList;


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