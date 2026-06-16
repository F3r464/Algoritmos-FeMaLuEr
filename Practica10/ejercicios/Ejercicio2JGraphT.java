package ejercicios;

import org.jgrapht.Graph;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

/**
 * EJERCICIO 2: Modelado de Red de Ciudades usando exclusivamente JGraphT.
 */
public class Ejercicio2JGraphT {

    public static void ejecutar() {
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║ EJERCICIO 2: RED DE CIUDADES (JGraphT)   ║");
        System.out.println("╚══════════════════════════════════════════╝");

        // Crear Grafo Ponderado No Dirigido
        Graph<String, DefaultWeightedEdge> red = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);

        // Agregar Ciudades (Vértices)
        String[] ciudades = {"Arequipa", "Cusco", "Puno", "Tacna", "Moquegua"};
        for (String ciudad : ciudades) {
            red.addVertex(ciudad);
        }

        // Agregar Carreteras con Distancia (Aristas con Peso)
        agregarRuta(red, "Arequipa", "Cusco", 510);
        agregarRuta(red, "Arequipa", "Moquegua", 230);
        agregarRuta(red, "Moquegua", "Tacna", 160);
        agregarRuta(red, "Cusco", "Puno", 390);
        agregarRuta(red, "Puno", "Tacna", 420);

        // Imprimir Reporte Solicitado
        System.out.println("\nLista de Ciudades en la Red:");
        for (String ciudad : red.vertexSet()) {
            System.out.println("  • " + ciudad);
        }

        System.out.println("\nCarreteras Registradas:");
        for (DefaultWeightedEdge edge : red.edgeSet()) {
            System.out.println("  " + red.getEdgeSource(edge) + " <---> " 
                    + red.getEdgeTarget(edge) + " [" + (int)red.getEdgeWeight(edge) + " km]");
        }

        // Calcular camino óptimo mediante Dijkstra
        String origen = "Arequipa";
        String destino = "Tacna";

        DijkstraShortestPath<String, DefaultWeightedEdge> dijkstra = new DijkstraShortestPath<>(red);
        var path = dijkstra.getPath(origen, destino);

        System.out.println("\n--- Cálculo de Ruta Óptima ---");
        if (path != null) {
            System.out.println("Ruta más corta desde " + origen + " hasta " + destino + ":");
            System.out.println("Camino a seguir: " + path.getVertexList());
            System.out.println("Costo total de viaje: " + (int)path.getWeight() + " km");
        } else {
            System.out.println("No hay conexión terrestre disponible.");
        }
    }

    private static void agregarRuta(Graph<String, DefaultWeightedEdge> g, String o, String d, double peso) {
        DefaultWeightedEdge edge = g.addEdge(o, d);
        if (edge != null) {
            g.setEdgeWeight(edge, peso);
        }
    }
}