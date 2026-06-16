package ejercicios;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import java.util.Set;


public class CiudadGrafoJGraphT {

    // El grafo de JGraphT: los vértices son Strings (nombres de ciudades),
    // y las aristas son DefaultWeightedEdge (carreteras con distancia/peso).
    private Graph<String, DefaultWeightedEdge> grafoCiudades;

    /**
     * Constructor: se inicializa un grafo PONDERADO y NO DIRIGIDO,
     * usando la clase SimpleWeightedGraph de JGraphT.
     *
     * El parámetro que se le pasa al constructor es la "fábrica" de
     * aristas: le indica a JGraphT que, cada vez que se cree una
     * nueva arista internamente, debe ser de tipo DefaultWeightedEdge.
     */
    public CiudadGrafoJGraphT() {
        grafoCiudades = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
    }

    // ══════════════════════════════════════════════════════════
    //  AGREGAR CIUDADES (vértices)
    // ══════════════════════════════════════════════════════════

    /**
     * Agrega una ciudad (vértice) al grafo.
     * addVertex() es un método propio de la interfaz Graph de JGraphT;
     * si el vértice ya existe, simplemente no hace nada (no lanza error).
     */
    public void agregarCiudad(String nombreCiudad) {
        grafoCiudades.addVertex(nombreCiudad);
        System.out.println("[OK] Ciudad agregada: " + nombreCiudad);
    }

    // ══════════════════════════════════════════════════════════
    //  AGREGAR CARRETERAS (aristas con peso)
    // ══════════════════════════════════════════════════════════

    /**
     * Agrega una carretera (arista) entre dos ciudades, con una
     * distancia (peso) en kilómetros.
     *
     * Proceso:
     *   1. addEdge(origen, destino) crea la arista SIN peso (peso 0 por defecto)
     *      y devuelve el objeto DefaultWeightedEdge recién creado.
     *   2. setEdgeWeight(arista, distanciaKm) le asigna el peso real a esa arista.
     *
     * Como el grafo es SimpleWeightedGraph (no dirigido), JGraphT ya
     * se encarga de que la conexión sea bidireccional internamente;
     * no hace falta llamar addEdge dos veces como en una implementación manual.
     */
    public void agregarCarretera(String ciudadOrigen, String ciudadDestino, double distanciaKm) {
        // Se crea la arista entre las dos ciudades.
        DefaultWeightedEdge arista = grafoCiudades.addEdge(ciudadOrigen, ciudadDestino);

        if (arista == null) {
            // Esto ocurre si alguna de las ciudades no fue agregada antes,
            // o si la arista ya existía (JGraphT no permite duplicados
            // en un SimpleWeightedGraph).
            System.out.println("[ERROR] No se pudo crear la carretera "
                    + ciudadOrigen + " - " + ciudadDestino
                    + " (verifique que ambas ciudades existan).");
            return;
        }

        // Se asigna el peso (distancia en km) a la arista recién creada.
        grafoCiudades.setEdgeWeight(arista, distanciaKm);
        System.out.println("[OK] Carretera agregada: " + ciudadOrigen + " - "
                + ciudadDestino + " (" + distanciaKm + " km)");
    }

    // ══════════════════════════════════════════════════════════
    //  MOSTRAR CIUDADES (vértices)
    // ══════════════════════════════════════════════════════════

    /** Imprime en consola la lista de todas las ciudades (vértices) del grafo. */
    public void mostrarCiudades() {
        System.out.println("\n--- LISTA DE CIUDADES ---");
        // vertexSet() devuelve un Set<String> con todos los vértices del grafo.
        Set<String> ciudades = grafoCiudades.vertexSet();
        for (String ciudad : ciudades) {
            System.out.println("  • " + ciudad);
        }
    }

    // ══════════════════════════════════════════════════════════
    //  MOSTRAR CARRETERAS (aristas con su peso)
    // ══════════════════════════════════════════════════════════

    /** Imprime en consola todas las carreteras registradas junto con su distancia. */
    public void mostrarCarreteras() {
        System.out.println("\n--- CARRETERAS REGISTRADAS ---");
        // edgeSet() devuelve el conjunto de todas las aristas del grafo.
        Set<DefaultWeightedEdge> carreteras = grafoCiudades.edgeSet();
        for (DefaultWeightedEdge carretera : carreteras) {
            // getEdgeSource / getEdgeTarget devuelven los vértices extremos
            // de la arista (en un grafo no dirigido, el orden es el que
            // se usó al crear la arista, pero la conexión es bidireccional).
            String origen = grafoCiudades.getEdgeSource(carretera);
            String destino = grafoCiudades.getEdgeTarget(carretera);
            // getEdgeWeight devuelve el peso (distancia) asignado a la arista.
            double distancia = grafoCiudades.getEdgeWeight(carretera);
            System.out.println("  " + origen + " <-> " + destino + " : " + distancia + " km");
        }
    }

    // ══════════════════════════════════════════════════════════
    //  CAMINO MÁS CORTO — Algoritmo de Dijkstra (de JGraphT)
    // ══════════════════════════════════════════════════════════

    /**
     * Calcula y muestra el camino más corto entre dos ciudades usando
     * el algoritmo de Dijkstra YA IMPLEMENTADO por JGraphT
     * (clase DijkstraShortestPath), sin necesidad de programarlo manualmente.
     */
    public void calcularCaminoMasCorto(String origen, String destino) {
        System.out.println("\n--- CAMINO MÁS CORTO: " + origen + " -> " + destino + " ---");

        // Se crea el objeto del algoritmo, pasándole el grafo sobre el que va a operar.
        DijkstraShortestPath<String, DefaultWeightedEdge> dijkstra =
                new DijkstraShortestPath<>(grafoCiudades);

        // getPath calcula la ruta óptima entre origen y destino.
        // Devuelve un GraphPath (objeto que contiene la secuencia de
        // vértices, las aristas usadas, y el peso total del camino).
        GraphPath<String, DefaultWeightedEdge> camino = dijkstra.getPath(origen, destino);

        if (camino == null) {
            // Si getPath devuelve null, significa que NO existe ninguna
            // ruta posible entre origen y destino (grafo no conexo entre esos puntos).
            System.out.println("[INFO] No existe una ruta entre " + origen + " y " + destino);
            return;
        }

        // getVertexList() devuelve la lista ordenada de ciudades del camino óptimo.
        System.out.println("Ruta óptima: " + camino.getVertexList());
        // getWeight() devuelve el costo total acumulado (suma de distancias) del camino.
        System.out.println("Costo total: " + camino.getWeight() + " km");
    }

    // ══════════════════════════════════════════════════════════
    //  MAIN — punto de entrada independiente para probar el Ejercicio 2
    // ══════════════════════════════════════════════════════════

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════╗");
        System.out.println("║ EJERCICIO 2: RED DE CIUDADES CON JGraphT          ║");
        System.out.println("╚══════════════════════════════════════════════════╝");

        // Se crea la red de ciudades.
        CiudadGrafoJGraphT red = new CiudadGrafoJGraphT();

        // Se agregan las 5 ciudades de ejemplo del enunciado.
        red.agregarCiudad("Arequipa");
        red.agregarCiudad("Cusco");
        red.agregarCiudad("Puno");
        red.agregarCiudad("Tacna");
        red.agregarCiudad("Moquegua");

        // Se agregan las carreteras de ejemplo del enunciado, con su distancia en km.
        red.agregarCarretera("Arequipa", "Cusco", 510);
        red.agregarCarretera("Arequipa", "Moquegua", 230);
        red.agregarCarretera("Moquegua", "Tacna", 160);
        red.agregarCarretera("Cusco", "Puno", 390);
        red.agregarCarretera("Puno", "Tacna", 420);

        // Se muestran las ciudades y carreteras registradas.
        red.mostrarCiudades();
        red.mostrarCarreteras();

        // Se calcula el camino más corto entre dos ciudades de ejemplo.
        red.calcularCaminoMasCorto("Arequipa", "Tacna");
        red.calcularCaminoMasCorto("Cusco", "Moquegua");
    }
}