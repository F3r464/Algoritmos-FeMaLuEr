package ejercicios;

import graph.GraphLink;
import java.util.ArrayList;
import java.util.Stack;

/**
 * EJERCICIO 1
 * Demuestra el uso de los métodos agregados a GraphLink para grafos
 * no dirigidos ponderados: insertEdgeWeight, shortPath, isConexo y dijkstra.
 * (Las implementaciones de estos métodos están en graph.GraphLink).
 */
public class Ejercicio1 {

    public static void ejecutar() {
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║ EJERCICIO 1: GRAFO PONDERADO - MÉTODOS   ║");
        System.out.println("╚══════════════════════════════════════════╝");

        // Se crea un grafo ponderado de prueba con 5 vértices.
        GraphLink<String> g = new GraphLink<>();
        g.insertVertex("A");
        g.insertVertex("B");
        g.insertVertex("C");
        g.insertVertex("D");
        g.insertVertex("E");

        // Se insertan aristas con peso usando insertEdgeWeight(v, z, w).
        g.insertEdgeWeight("A", "B", 4);
        g.insertEdgeWeight("A", "C", 2);
        g.insertEdgeWeight("C", "B", 1);
        g.insertEdgeWeight("B", "D", 5);
        g.insertEdgeWeight("C", "D", 8);
        g.insertEdgeWeight("D", "E", 3);

        System.out.println("\n-- Grafo ponderado construido --");
        System.out.println(g);

        // isConexo(): comprueba si todos los vértices están conectados.
        System.out.println("¿El grafo es conexo? " + g.isConexo());

        // shortPath(v, z): ruta más corta como ArrayList, de origen a destino.
        ArrayList<String> ruta = g.shortPath("A", "E");
        System.out.println("\nRuta más corta de A a E (shortPath): " + ruta);

        // Dijkstra(v, w): retorna un Stack con la ruta más corta.
        Stack<String> rutaStack = g.dijkstra("A", "E");
        System.out.println("Ruta más corta de A a E (dijkstra - Stack, tope=destino): " + rutaStack);
    }
}