package graph;

/**
 * Representa una arista (conexión) entre dos vértices del grafo.
 * Soporta grafos ponderados mediante el atributo weight.
 */
public class Edge<E> {
    private Vertex<E> destination;
    private int weight;

    /** Constructor sin peso (grafo no ponderado). */
    public Edge(Vertex<E> destination) {
        this(destination, 1);
    }

    /** Constructor con peso (grafo ponderado). */
    public Edge(Vertex<E> destination, int weight) {
        this.destination = destination;
        this.weight = weight;
    }

    public Vertex<E> getDestination() {
        return destination;
    }

    public void setDestination(Vertex<E> destination) {
        this.destination = destination;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return destination.toString() + "(w=" + weight + ")";
    }
}