package graph;

/**
 * Representa una arista (conexión) del grafo.
 *
 * Una arista se modela "desde el punto de vista de un vértice": cada
 * Edge guarda solamente el vértice DESTINO al que apunta, porque ya
 * vive dentro de la lista de adyacencia del vértice ORIGEN (ver AdjList).
 * Por eso no necesita guardar también el origen.
 *
 * Soporta peso (weight) para representar grafos ponderados.
 */
public class Edge<E> {

    // Vértice al que llega esta arista.
    private Vertex<E> destination;

    // Peso/costo de la arista (distancia, tiempo, etc.).
    private int weight;

    /**
     * Constructor sin peso explícito: usado en grafos NO ponderados.
     * Se delega al otro constructor fijando weight = 1 por defecto.
     */
    public Edge(Vertex<E> destination) {
        this(destination, 1);
    }

    /** Constructor con peso explícito: usado en grafos ponderados. */
    public Edge(Vertex<E> destination, int weight) {
        this.destination = destination;
        this.weight = weight;
    }

    // Devuelve el vértice destino de la arista.
    public Vertex<E> getDestination() {
        return destination;
    }

    // Permite cambiar el destino de la arista.
    public void setDestination(Vertex<E> destination) {
        this.destination = destination;
    }

    // Devuelve el peso de la arista.
    public int getWeight() {
        return weight;
    }

    // Permite modificar el peso de la arista.
    public void setWeight(int weight) {
        this.weight = weight;
    }

    // Representación en texto: destino y peso entre paréntesis.
    @Override
    public String toString() {
        return destination.toString() + "(w=" + weight + ")";
    }
}