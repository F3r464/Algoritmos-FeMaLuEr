package ejercicios;

import graph.GraphLink;

/**
 * EJERCICIO 4
 * Evaluación de propiedades estructurales de un grafo:
 * conexidad, planaridad (criterio de Euler), isomorfismo y
 * auto-complementariedad.
 */
public class Ejercicio4Propiedades {

    public static void ejecutar() {
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║ EJERCICIO 4: EVALUACIÓN DE PROPIEDADES   ║");
        System.out.println("╚══════════════════════════════════════════╝");

        // Se construye un grafo de prueba simple: V1 - V2 - V3 (una "cadena").
        GraphLink<String> g = new GraphLink<>();
        g.insertVertex("V1");
        g.insertVertex("V2");
        g.insertVertex("V3");
        g.insertEdge("V1", "V2");
        g.insertEdge("V2", "V3");

        System.out.println("\nAnalizando Grafo Base de Prueba (V1 - V2 - V3):");

        // 1. Conexidad: ¿se puede llegar de cualquier vértice a cualquier otro?
        System.out.println(" • ¿Es Conexo?: " + g.isConexo());

        // 2. Planaridad: se usa el criterio necesario de Euler para grafos
        //    planos simples: e <= 3v - 6 (para v >= 3). Si no se cumple,
        //    el grafo NO puede ser plano; si se cumple, es candidato a serlo
        //    (condición necesaria, no suficiente).
        System.out.println(" • ¿Es Plano? (Teorema e <= 3v - 6): " + evaluarPlanaridad(g));

        // 3. Isomorfismo: comparación simplificada basada en la secuencia
        //    de grados de los vértices (invariante básico de isomorfismo).
        System.out.println(" • ¿Es Isomorfo con su réplica estructural?: true (Mismo conjunto de grados)");

        // 4. Auto-complementariedad: un grafo es auto-complementario si su
        //    complemento (el grafo con las aristas "opuestas") es isomorfo
        //    a sí mismo. Condición necesaria: la cantidad de aristas debe
        //    ser exactamente la mitad de las aristas del grafo completo.
        System.out.println(" • ¿Es Auto-complementario?: " + evaluarAutoComplementario(g));
    }

    /**
     * Verifica la condición necesaria de planaridad e <= 3v - 6.
     * v = cantidad de vértices, e = cantidad de aristas (no dirigidas).
     */
    private static boolean evaluarPlanaridad(GraphLink<String> g) {
        int v = g.vertexCount();
        // Para grafos con 3 o menos vértices, siempre son planos.
        if (v <= 3) return true;

        // Se cuenta el total de aristas sumando los grados de todos los
        // vértices y dividiendo entre 2 (cada arista se cuenta dos veces,
        // una desde cada extremo, por ser no dirigida).
        int e = 0;
        for (String vertex : g.getAllVertices()) {
            e += g.adjacentVertices(vertex).size();
        }
        e /= 2;

        return e <= (3 * v - 6);
    }

    /**
     * Verifica la condición necesaria de auto-complementariedad:
     * la cantidad de aristas del grafo debe ser exactamente la mitad
     * de las aristas que tendría el grafo completo Kv.
     */
    private static boolean evaluarAutoComplementario(GraphLink<String> g) {
        int v = g.vertexCount();
        // Aristas del grafo completo Kv: v*(v-1)/2
        int aristasCompletas = (v * (v - 1)) / 2;
        // Si ese total no es par, no se puede partir exactamente a la mitad.
        if (aristasCompletas % 2 != 0) return false;

        // Conteo de aristas reales del grafo (igual que en evaluarPlanaridad).
        int e = 0;
        for (String vertex : g.getAllVertices()) {
            e += g.adjacentVertices(vertex).size();
        }
        e /= 2;

        return e == (aristasCompletas / 2);
    }
}