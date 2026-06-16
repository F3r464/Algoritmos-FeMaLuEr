package actividades;

import graph.GraphLink;
import java.util.ArrayList;

/**
 * ACTIVIDAD 1
 * Representación de grafos (No Dirigidos, Dirigidos y Ponderados).
 */
public class Actividad1 {

    public static void grafoNoDirigido() {
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║  A) GRAFO NO DIRIGIDO                    ║");
        System.out.println("╚══════════════════════════════════════════╝");

        GraphLink<Integer> g = new GraphLink<>();
        for (int i = 1; i <= 6; i++) g.insertVertex(i);

        g.insertEdge(1, 3);
        g.insertEdge(1, 2);
        g.insertEdge(3, 2);
        g.insertEdge(3, 5);
        g.insertEdge(2, 4);
        g.insertEdge(2, 5);
        g.insertEdge(5, 4);
        g.insertEdge(5, 6);
        g.insertEdge(4, 6);

        System.out.println("\n-- Lista de Adyacencia --");
        System.out.println(g);
        System.out.println("-- Matriz de Adyacencia (6x6) --");
        printMatrizAdyacencia(g, new Integer[]{1,2,3,4,5,6});
    }

    public static void grafoDirigido() {
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║  B) GRAFO DIRIGIDO (Dígrafo)             ║");
        System.out.println("╚══════════════════════════════════════════╝");

        String[] labels = {"A","B","C","D","E"};
        int[][] matrix = {
            { 0, 1, 1, 0, 0 },
            { 1, 0, 1, 0, 0 },
            { 1, 0, 0, 0, 0 },
            { 0, 1, 1, 0, 0 },
            { 0, 0, 1, 1, 0 }
        };

        System.out.println("\n-- Matriz de Adyacencia --");
        System.out.print("   ");
        for (String l : labels) System.out.printf("%3s", l);
        System.out.println();
        for (int i = 0; i < labels.length; i++) {
            System.out.printf("%3s", labels[i]);
            for (int j = 0; j < labels.length; j++) {
                System.out.printf("%3d", matrix[i][j]);
            }
            System.out.println();
        }

        System.out.println("\n-- Lista de Adyacencia (Inserciones al INICIO) --");
        String[][] adjLists = {
            {"A", "C", "B"},
            {"B", "C", "A"},
            {"C", "A"},
            {"D", "C", "B"},
            {"E", "D", "C"}
        };
        for (String[] row : adjLists) {
            System.out.print("  " + row[0] + " -> ");
            for (int i = 1; i < row.length; i++) {
                System.out.print(row[i]);
                if (i < row.length - 1) System.out.print(" -> ");
            }
            System.out.println(" -> NULL");
        }
    }

    public static void grafoPonderado() {
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║  C) GRAFO PONDERADO                      ║");
        System.out.println("╚══════════════════════════════════════════╝");

        GraphLink<Integer> g = new GraphLink<>();
        for (int i = 1; i <= 5; i++) g.insertVertex(i);

        g.insertEdgeWeight(1, 2, 5);
        g.insertEdgeWeight(1, 5, 4);
        g.insertEdgeWeight(5, 3, 1);
        g.insertEdgeWeight(4, 2, 6);
        g.insertEdgeWeight(5, 4, 2);
        g.insertEdgeWeight(2, 5, 3);

        System.out.println("\n-- Lista de Adyacencia (Con Pesos) --");
        System.out.println(g);
        System.out.println("-- Matriz de Adyacencia Ponderada --");
        printMatrizAdyacenciaPonderada(g, new Integer[]{1,2,3,4,5});
    }

    private static void printMatrizAdyacencia(GraphLink<Integer> g, Integer[] vertices) {
        System.out.print("    ");
        for (int v : vertices) System.out.printf("%3d", v);
        System.out.println();
        for (int vi : vertices) {
            System.out.printf("%3d ", vi);
            ArrayList<Integer> adj = g.adjacentVertices(vi);
            for (int vj : vertices) {
                System.out.printf("%3d", adj.contains(vj) ? 1 : 0);
            }
            System.out.println();
        }
    }

    private static void printMatrizAdyacenciaPonderada(GraphLink<Integer> g, Integer[] vertices) {
        System.out.print("    ");
        for (int v : vertices) System.out.printf("%4d", v);
        System.out.println();
        for (int vi : vertices) {
            System.out.printf("%3d ", vi);
            for (int vj : vertices) {
                if (vi == vj) {
                    System.out.printf("%4s", "0");
                } else {
                    ArrayList<Integer> adj = g.adjacentVertices(vi);
                    System.out.printf("%4s", adj.contains(vj) ? "X" : "0");
                }
            }
            System.out.println();
        }
        System.out.println("(X = arista existe; 0 = sin conexión)");
    }
}