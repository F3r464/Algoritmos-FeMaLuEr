package ejercicios;

import graph.GraphLink;
import java.util.ArrayList;


public class Ejercicio3 {

    // Grafo de ciudades, usando el TAD propio (no JGraphT).
    // El tipo de dato de cada vértice es String (nombre de la ciudad).
    private GraphLink<String> redCiudades;

    public Ejercicio3() {
        // Se instancia la implementación concreta del TAD Graph.
        redCiudades = new GraphLink<>();
    }

    // ══════════════════════════════════════════════════════════
    //  insertVertex() — agregar ciudad
    // ══════════════════════════════════════════════════════════
    public void agregarCiudad(String ciudad) {
        // Delega directamente en el método del TAD.
        redCiudades.insertVertex(ciudad);
    }

    // ══════════════════════════════════════════════════════════
    //  insertEdge() — agregar carretera SIN distancia (peso 1)
    // ══════════════════════════════════════════════════════════
    public void agregarCarreteraSimple(String origen, String destino) {
        redCiudades.insertEdge(origen, destino);
    }

    // ══════════════════════════════════════════════════════════
    //  insertEdgeWeight() — agregar carretera CON distancia (peso real)
    // ══════════════════════════════════════════════════════════
    public void agregarCarretera(String origen, String destino, int distanciaKm) {
        redCiudades.insertEdgeWeight(origen, destino, distanciaKm);
    }

    // ══════════════════════════════════════════════════════════
    //  removeVertex() — eliminar ciudad
    // ══════════════════════════════════════════════════════════
    public void eliminarCiudad(String ciudad) {
        redCiudades.removeVertex(ciudad);
    }

    // ══════════════════════════════════════════════════════════
    //  removeEdge() — eliminar carretera
    // ══════════════════════════════════════════════════════════
    public void eliminarCarretera(String origen, String destino) {
        redCiudades.removeEdge(origen, destino);
    }

    // ══════════════════════════════════════════════════════════
    //  searchVertex() — buscar ciudad
    // ══════════════════════════════════════════════════════════
    public boolean existeCiudad(String ciudad) {
        return redCiudades.searchVertex(ciudad);
    }

    // ══════════════════════════════════════════════════════════
    //  searchEdge() — buscar carretera
    // ══════════════════════════════════════════════════════════
    public boolean existeCarretera(String origen, String destino) {
        return redCiudades.searchEdge(origen, destino);
    }

    // ══════════════════════════════════════════════════════════
    //  adjacentVertices() — ciudades conectadas directamente
    // ══════════════════════════════════════════════════════════
    public ArrayList<String> ciudadesConectadasCon(String ciudad) {
        return redCiudades.adjacentVertices(ciudad);
    }

    // ══════════════════════════════════════════════════════════
    //  Mostrar todas las conexiones del grafo (lista de adyacencia)
    // ══════════════════════════════════════════════════════════
    public void mostrarConexiones() {
        System.out.println("\n--- CONEXIONES DEL GRAFO (TAD propio) ---");
        // Se usa el toString() ya implementado en GraphLink.
        System.out.println(redCiudades);
    }

    // ══════════════════════════════════════════════════════════
    //  Camino más corto (reutiliza shortPath de GraphLink, que internamente
    //  usa el algoritmo de Dijkstra implementado a mano en el TAD propio)
    // ══════════════════════════════════════════════════════════
    public void caminoMasCorto(String origen, String destino) {
        System.out.println("\n--- CAMINO MÁS CORTO (TAD propio): "
                + origen + " -> " + destino + " ---");
        ArrayList<String> ruta = redCiudades.shortPath(origen, destino);

        if (ruta.isEmpty()) {
            System.out.println("[INFO] No existe ruta entre " + origen + " y " + destino);
            return;
        }
        System.out.println("Ruta óptima: " + ruta);
    }

    // ══════════════════════════════════════════════════════════
    //  Obtiene la referencia al GraphLink interno (por si se necesita
    //  acceder a métodos adicionales como isConexo(), dfs(), bfs(), etc.)
    // ══════════════════════════════════════════════════════════
    public GraphLink<String> getGrafo() {
        return redCiudades;
    }

    // ══════════════════════════════════════════════════════════
    //  MAIN — punto de entrada independiente para probar el Ejercicio 3
    // ══════════════════════════════════════════════════════════
    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════╗");
        System.out.println("║ EJERCICIO 3: RED DE CIUDADES CON TAD PROPIO       ║");
        System.out.println("╚══════════════════════════════════════════════════╝");

        Ejercicio3 red = new Ejercicio3();

        // Se agregan las mismas 5 ciudades del Ejercicio 2, para poder
        // comparar resultados entre JGraphT y el TAD propio.
        red.agregarCiudad("Arequipa");
        red.agregarCiudad("Cusco");
        red.agregarCiudad("Puno");
        red.agregarCiudad("Tacna");
        red.agregarCiudad("Moquegua");

        // Mismas carreteras y distancias del enunciado.
        red.agregarCarretera("Arequipa", "Cusco", 510);
        red.agregarCarretera("Arequipa", "Moquegua", 230);
        red.agregarCarretera("Moquegua", "Tacna", 160);
        red.agregarCarretera("Cusco", "Puno", 390);
        red.agregarCarretera("Puno", "Tacna", 420);

        // Se muestran todas las conexiones (lista de adyacencia con pesos).
        red.mostrarConexiones();

        // Pruebas de los métodos exigidos por el Ejercicio 3.
        System.out.println("\n¿Existe la ciudad 'Cusco'? " + red.existeCiudad("Cusco"));
        System.out.println("¿Existe la ciudad 'Lima'? " + red.existeCiudad("Lima"));
        System.out.println("¿Existe carretera Arequipa-Cusco? " + red.existeCarretera("Arequipa", "Cusco"));
        System.out.println("Ciudades conectadas con Arequipa: " + red.ciudadesConectadasCon("Arequipa"));

        // Camino más corto usando el TAD propio.
        red.caminoMasCorto("Arequipa", "Tacna");
        red.caminoMasCorto("Cusco", "Moquegua");

        // Demostración de removeEdge y removeVertex del TAD.
        System.out.println("\n--- Eliminando la carretera Puno-Tacna ---");
        red.eliminarCarretera("Puno", "Tacna");
        red.mostrarConexiones();

        System.out.println("--- Eliminando la ciudad Moquegua ---");
        red.eliminarCiudad("Moquegua");
        red.mostrarConexiones();
    }
}