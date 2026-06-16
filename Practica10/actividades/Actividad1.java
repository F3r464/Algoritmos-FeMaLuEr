package actividades;

import graph.GraphLink;
import java.util.ArrayList;

/**
 * ACTIVIDAD 1
 * Representación de grafos mediante matrices de adyacencia y listas
 * de adyacencia, para los tres casos pedidos en la guía:
 *   a) Grafo No Dirigido  (inserciones al FINAL de las listas)
 *   b) Grafo Dirigido     (inserciones al INICIO de las listas)
 *   c) Grafo Ponderado    (inserciones al FINAL, con pesos)
 */
public class Actividad1 {

    // ──────────────────────────────────────────
    //  A) Grafo No Dirigido
    // ──────────────────────────────────────────
    public static void grafoNoDirigido() {
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║  A) GRAFO NO DIRIGIDO                    ║");
        System.out.println("╚══════════════════════════════════════════╝");

        // Se crea un grafo cuyo dato de vértice es Integer.
        GraphLink<Integer> g = new GraphLink<>();

        // Se insertan los 6 vértices en orden numérico (1 al 6).
        for (int i = 1; i <= 6; i++) g.insertVertex(i);

        // Se insertan las aristas del grafo del PDF (sin peso -> peso 1 por defecto).
        // Al ser NO dirigido, cada llamada agrega la conexión en ambos sentidos.
        g.insertEdge(1, 3); // e3
        g.insertEdge(1, 2); // e8
        g.insertEdge(3, 2); // e6
        g.insertEdge(3, 5); // e7
        g.insertEdge(2, 4); // e1
        g.insertEdge(2, 5); // e4
        g.insertEdge(5, 4); // e9
        g.insertEdge(5, 6); // e2
        g.insertEdge(4, 6); // e5

        // Se imprime la lista de adyacencia resultante (toString de GraphLink).
        System.out.println("\n-- Lista de Adyacencia --");
        System.out.println(g);

        // Se imprime también la matriz de adyacencia equivalente.
        System.out.println("-- Matriz de Adyacencia (6x6) --");
        printMatrizAdyacencia(g, new Integer[]{1,2,3,4,5,6});
    }

    // ──────────────────────────────────────────
    //  B) Grafo Dirigido
    //     (GraphLink modela grafo NO dirigido; aquí se muestra la
    //      matriz y lista de adyacencia del dígrafo de forma manual,
    //      respetando exactamente las aristas dirigidas del PDF)
    // ──────────────────────────────────────────
    public static void grafoDirigido() {
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║  B) GRAFO DIRIGIDO (Dígrafo)             ║");
        System.out.println("╚══════════════════════════════════════════╝");

        // Etiquetas de los 5 vértices, en orden alfabético.
        String[] labels = {"A","B","C","D","E"};

        // Matriz de adyacencia del dígrafo: fila = origen, columna = destino.
        // matrix[i][j] = 1 significa "existe arista dirigida de labels[i] hacia labels[j]".
        int[][] matrix = {
            // A  B  C  D  E
            {  0, 1, 1, 0, 0 },  // A → B, A → C
            {  1, 0, 1, 0, 0 },  // B → A, B → C
            {  1, 0, 0, 0, 0 },  // C → A
            {  0, 1, 1, 0, 0 },  // D → B, D → C
            {  0, 0, 1, 1, 0 }   // E → C, E → D
        };

        System.out.println("\n-- Matriz de Adyacencia --");
        // Encabezado de columnas.
        System.out.print("   ");
        for (String l : labels) System.out.printf("%3s", l);
        System.out.println();
        // Cada fila: etiqueta + valores de la matriz.
        for (int i = 0; i < labels.length; i++) {
            System.out.printf("%3s", labels[i]);
            for (int j = 0; j < labels.length; j++) {
                System.out.printf("%3d", matrix[i][j]);
            }
            System.out.println();
        }

        System.out.println("\n-- Lista de Adyacencia (Inserciones al INICIO) --");
        // Se muestra cómo quedaría la lista de adyacencia si cada arista
        // nueva se inserta al INICIO (addFirst) en vez del final.
        // Por eso el último vecino insertado aparece primero en la lista.
        String[][] adjLists = {
            {"A", "C", "B"},   // a A se le insertó primero B, luego C -> C queda primero
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

    // ──────────────────────────────────────────
    //  C) Grafo Ponderado
    // ──────────────────────────────────────────
    public static void grafoPonderado() {
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║  C) GRAFO PONDERADO                      ║");
        System.out.println("╚══════════════════════════════════════════╝");

        GraphLink<Integer> g = new GraphLink<>();

        // Se insertan los 5 vértices.
        for (int i = 1; i <= 5; i++) g.insertVertex(i);

        // Se insertan las aristas CON peso explícito (insertEdgeWeight),
        // tal como aparecen en el grafo ponderado del PDF.
        g.insertEdgeWeight(1, 2, 5); // e2, peso 5
        g.insertEdgeWeight(1, 5, 4); // e3, peso 4
        g.insertEdgeWeight(5, 3, 1); // e5, peso 1
        g.insertEdgeWeight(4, 2, 6); // e4, peso 6
        g.insertEdgeWeight(5, 4, 2); // e1, peso 2
        g.insertEdgeWeight(2, 5, 3); // e6, peso 3

        System.out.println("\n-- Lista de Adyacencia (Con Pesos) --");
        System.out.println(g);

        System.out.println("-- Matriz de Adyacencia Ponderada --");
        printMatrizAdyacenciaPonderada(g, new Integer[]{1,2,3,4,5});
    }

    // ══════════════════════════════════════════════════════════
    //  MÉTODOS AUXILIARES DE IMPRESIÓN
    // ══════════════════════════════════════════════════════════

    /**
     * Imprime la matriz de adyacencia binaria (0/1) de un grafo no ponderado.
     * Se construye consultando adjacentVertices() de GraphLink para
     * cada par de vértices (vi, vj).
     */
    private static void printMatrizAdyacencia(GraphLink<Integer> g, Integer[] vertices) {
        System.out.print("    ");
        for (int v : vertices) System.out.printf("%3d", v); // encabezado de columnas
        System.out.println();

        for (int vi : vertices) {
            System.out.printf("%3d ", vi); // etiqueta de fila
            ArrayList<Integer> adj = g.adjacentVertices(vi); // vecinos de vi
            for (int vj : vertices) {
                // 1 si vj es vecino de vi, 0 en caso contrario
                System.out.printf("%3d", adj.contains(vj) ? 1 : 0);
            }
            System.out.println();
        }
    }

    /**
     * Imprime la matriz de adyacencia ponderada, mostrando el PESO REAL
     * de cada arista (obtenido con getEdgeWeight) en lugar de solo 0/1.
     */
    private static void printMatrizAdyacenciaPonderada(GraphLink<Integer> g, Integer[] vertices) {
        System.out.print("    ");
        for (int v : vertices) System.out.printf("%4d", v);
        System.out.println();

        for (int vi : vertices) {
            System.out.printf("%3d ", vi);
            for (int vj : vertices) {
                if (vi == vj) {
                    // La diagonal principal siempre es 0 (no hay auto-lazos).
                    System.out.printf("%4s", "0");
                } else {
                    // Se consulta el peso real de la arista vi -> vj.
                    int w = g.getEdgeWeight(vi, vj);
                    System.out.printf("%4s", w == -1 ? "0" : String.valueOf(w));
                }
            }
            System.out.println();
        }
    }
}