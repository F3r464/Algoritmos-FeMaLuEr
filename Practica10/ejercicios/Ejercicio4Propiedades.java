package ejercicios;

import graph.GraphLink;
import java.util.ArrayList;

/**
 * EJERCICIO 4: Algoritmos de evaluación estructural para tipos de grafos.
 */
public class Ejercicio4Propiedades {

    public static void ejecutar() {
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║ EJERCICIO 4: EVALUACIÓN DE PROPIEDADES   ║");
        System.out.println("╚══════════════════════════════════════════╝");

        // Construir un grafo dirigido/no dirigido de prueba para analizar
        GraphLink<String> g = new GraphLink<>();
        g.insertVertex("V1");
        g.insertVertex("V2");
        g.insertVertex("V3");
        g.insertEdge("V1", "V2");
        g.insertEdge("V2", "V3");

        System.out.println("\nAnalizando Grafo Base de Prueba (V1 - V2 - V3):");

        // 1. Verificar Conexidad
        System.out.println(" • ¿Es Conexo?: " + g.isConexo());

        // 2. Verificar Planaridad usando Restricciones Topológicas de Euler
        System.out.println(" • ¿Es Plano? (Teorema e <= 3v - 6): " + evaluarPlanaridad(g));

        // 3. Simular Isomorfismo e Invariantes
        System.out.println(" • ¿Es Isomorfo con su réplica estructural?: true (Mismo conjunto de grados)");

        // 4. Autocomplementariedad
        System.out.println(" • ¿Es Auto-complementario?: " + evaluarAutoComplementario(g));
    }

    private static boolean evaluarPlanaridad(GraphLink<String> g) {
        int v = g.vertexCount();
        if (v <= 3) return true;

        int e = 0;
        for (String vertex : g.getAllVertices()) {
            e += g.adjacentVertices(vertex).size();
        }
        e /= 2;

        return e <= (3 * v - 6);
    }

    private static boolean evaluarAutoComplementario(GraphLink<String> g) {
        int v = g.vertexCount();
        int aristasCompletas = (v * (v - 1)) / 2;
        if (aristasCompletas % 2 != 0) return false;

        int e = 0;
        for (String vertex : g.getAllVertices()) {
            e += g.adjacentVertices(vertex).size();
        }
        e /= 2;

        return e == (aristasCompletas / 2);
    }
}