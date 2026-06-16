package graph;

import listlinked.ListLinked;
import listlinked.Node;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


public class GraphLink<E> implements Graph<E> {

    // Lista principal del grafo: cada nodo de esta lista contiene un
    // AdjList, es decir, un vértice junto con todas sus aristas salientes.
    private ListLinked<AdjList<E>> graph;

    // Constructor: el grafo arranca vacío (sin vértices ni aristas).
    public GraphLink() {
        graph = new ListLinked<>();
    }

    // ══════════════════════════════════════════════════════════
    //  BÚSQUEDA INTERNA — acceso directo a la AdjList de un vértice
    // ══════════════════════════════════════════════════════════

    /**
     * Recorre la lista principal del grafo buscando el AdjList cuyo
     * vértice tenga el dato indicado. Devuelve null si no existe.
     * Complejidad: O(V), donde V es la cantidad de vértices.
     *
     * Es privado porque es un detalle de implementación interno:
     * las demás clases del proyecto NO deberían manipular AdjList
     * directamente, solo a través de los métodos públicos del TAD.
     */
    private AdjList<E> findAdjList(E data) {
        // Se toma el primer nodo de la lista principal.
        Node<AdjList<E>> current = graph.getHead();
        // Se recorre nodo por nodo hasta llegar al final (null).
        while (current != null) {
            // Se compara el dato del vértice de este AdjList con el buscado.
            if (current.getData().getVertex().getData().equals(data)) {
                return current.getData(); // encontrado: se devuelve el AdjList
            }
            current = current.next; // se avanza al siguiente nodo
        }
        return null; // no se encontró ningún vértice con ese dato
    }

    // ══════════════════════════════════════════════════════════
    //  INSERTAR VÉRTICE
    // ══════════════════════════════════════════════════════════

    @Override
    public void insertVertex(E data) {
        // Se verifica que no exista ya un vértice con el mismo dato,
        // para evitar vértices duplicados en el grafo.
        if (findAdjList(data) != null) {
            System.out.println("[AVISO] El vértice " + data + " ya existe.");
            return;
        }
        // Se crea el objeto Vertex que envuelve el dato...
        Vertex<E> vertex = new Vertex<>(data);
        // ...y se agrega al final de la lista principal del grafo,
        // envuelto en un AdjList nuevo (con lista de aristas vacía).
        graph.addLast(new AdjList<>(vertex));
    }

    // ══════════════════════════════════════════════════════════
    //  INSERTAR ARISTA (sin peso explícito)
    // ══════════════════════════════════════════════════════════

    @Override
    public void insertEdge(E origin, E destination) {
        // Una arista "sin peso" es, en la práctica, una arista con peso 1.
        insertEdgeWeight(origin, destination, 1);
    }

    // ══════════════════════════════════════════════════════════
    //  INSERTAR ARISTA CON PESO
    // ══════════════════════════════════════════════════════════

    @Override
    public void insertEdgeWeight(E origin, E destination, int weight) {
        // Se localizan las AdjList de ambos vértices.
        AdjList<E> v1 = findAdjList(origin);
        AdjList<E> v2 = findAdjList(destination);

        // Validación: ambos vértices deben existir antes de conectar una arista.
        if (v1 == null) {
            System.out.println("[ERROR] Vértice origen no encontrado: " + origin);
            return;
        }
        if (v2 == null) {
            System.out.println("[ERROR] Vértice destino no encontrado: " + destination);
            return;
        }

        // Como el grafo es NO DIRIGIDO, la conexión debe quedar registrada
        // en AMBOS sentidos: v1 ve a v2 como vecino, y v2 ve a v1 como vecino.
        v1.getEdges().addLast(new Edge<>(v2.getVertex(), weight));
        v2.getEdges().addLast(new Edge<>(v1.getVertex(), weight));
    }

    // ══════════════════════════════════════════════════════════
    //  ELIMINAR VÉRTICE (versión eficiente, sin recorrer todo el grafo)
    // ══════════════════════════════════════════════════════════

    /**
     * Elimina un vértice y todas las aristas que lo conectan con sus vecinos.
     *
     *   1. Se localiza el AdjList del vértice a eliminar.
     *   2. Su propia lista de aristas YA contiene a todos sus vecinos directos
     *      (no hace falta buscar "quién apunta a este vértice" recorriendo
     *      todo el grafo, porque por ser no dirigido, si A-B existe,
     *      entonces B también aparece en la lista de A).
     *   3. Se visita cada uno de esos vecinos puntualmente (acceso directo
     *      por dato, no recorrido completo) y se elimina la arista de vuelta.
     *   4. Finalmente se elimina el propio AdjList de la lista principal.
     */
    @Override
    public boolean removeVertex(E data) {
        // Paso 0: localizar el vértice a eliminar.
        AdjList<E> target = findAdjList(data);
        if (target == null) {
            System.out.println("[AVISO] El vértice " + data + " no existe.");
            return false;
        }

        // Paso 1: se recorre SOLO la lista de aristas propia del vértice a eliminar (no todo el grafo). Cada arista de esta lista  apunta a un vecino directo.
        Node<Edge<E>> edgeNode = target.getEdges().getHead();
        while (edgeNode != null) {
            // Vecino al que apunta esta arista.
            Vertex<E> neighbor = edgeNode.getData().getDestination();
            // Paso 2: se accede DIRECTAMENTE al AdjList de ese vecino (no se recorre el grafo completo buscando coincidencias).
            AdjList<E> neighborAdj = findAdjList(neighbor.getData());
            if (neighborAdj != null) {
                // Se elimina, dentro de la lista de aristas del vecino,
                // la arista que apuntaba de vuelta hacia el vértice eliminado.
                removeEdgeFromList(neighborAdj, data);
            }
            edgeNode = edgeNode.next; // siguiente vecino del vértice eliminado
        }

        // Paso 3: ya que no quedan referencias cruzadas, se elimina el
        // AdjList del propio vértice de la lista principal del grafo.
        removeAdjList(data);
        System.out.println("[OK] Vértice " + data + " eliminado.");
        return true;
    }

    /**
     * Busca dentro de la lista de aristas de "adjList" la arista cuyo
     * destino sea "destinationData" y la elimina.
     * Es un método auxiliar usado por removeVertex().
     */
    private void removeEdgeFromList(AdjList<E> adjList, E destinationData) {
        ListLinked<Edge<E>> edges = adjList.getEdges();
        Node<Edge<E>> current = edges.getHead();
        while (current != null) {
            if (current.getData().getDestination().getData().equals(destinationData)) {
                // ListLinked.remove() usa equals() sobre el objeto Edge;
                // como aquí ya tenemos la referencia exacta del Edge
                // (current.getData()), remove() lo encuentra y lo elimina.
                edges.remove(current.getData());
                return; // ya se eliminó, no hace falta seguir buscando
            }
            current = current.next;
        }
    }

    /**
     * Elimina de la lista PRINCIPAL del grafo el AdjList correspondiente
     * al vértice con dato "data". Método auxiliar de removeVertex().
     */
    private void removeAdjList(E data) {
        // Se recorre manualmente con punteros "prev" y "current" porque
        // se necesita reconectar la cadena (prev.next = current.next)
        // si el nodo a eliminar no es la cabeza.
        Node<AdjList<E>> prev = null;
        Node<AdjList<E>> current = graph.getHead();
        while (current != null) {
            if (current.getData().getVertex().getData().equals(data)) {
                if (prev == null) {
                    // El nodo a eliminar es la cabeza: se delega a
                    // ListLinked.remove(), que ya maneja ese caso.
                    graph.remove(current.getData());
                } else {
                    // El nodo a eliminar está en medio o al final:
                    // se "salta" enlazando prev directamente con el
                    // siguiente del nodo eliminado.
                    prev.next = current.next;
                }
                return;
            }
            prev = current;
            current = current.next;
        }
    }

    // ══════════════════════════════════════════════════════════
    //  ELIMINAR ARISTA
    // ══════════════════════════════════════════════════════════

    @Override
    public boolean removeEdge(E origin, E destination) {
        // Se localizan ambos vértices.
        AdjList<E> v1 = findAdjList(origin);
        AdjList<E> v2 = findAdjList(destination);

        if (v1 == null || v2 == null) {
            System.out.println("[AVISO] Uno o ambos vértices no existen.");
            return false;
        }

        // Al ser no dirigido, hay que eliminar la arista en AMBOS sentidos:
        // la que va de v1 hacia v2, y la que va de v2 hacia v1.
        boolean removed1 = removeEdgeFromListByDest(v1, destination);
        boolean removed2 = removeEdgeFromListByDest(v2, origin);

        // Solo se considera éxito total si ambas direcciones existían y se borraron.
        return removed1 && removed2;
    }

    /**
     * Busca y elimina, dentro de la lista de aristas de "adjList",
     * la arista cuyo destino sea "destinationData".
     * Devuelve true si la encontró y eliminó.
     */
    private boolean removeEdgeFromListByDest(AdjList<E> adjList, E destinationData) {
        Node<Edge<E>> current = adjList.getEdges().getHead();
        while (current != null) {
            if (current.getData().getDestination().getData().equals(destinationData)) {
                adjList.getEdges().remove(current.getData());
                return true;
            }
            current = current.next;
        }
        return false;
    }

    // ══════════════════════════════════════════════════════════
    //  BÚSQUEDAS PÚBLICAS
    // ══════════════════════════════════════════════════════════

    @Override
    public boolean searchVertex(E data) {
        // El vértice existe si findAdjList logra ubicarlo.
        return findAdjList(data) != null;
    }

    @Override
    public boolean searchEdge(E origin, E destination) {
        // Se localiza la lista de adyacencia del vértice origen.
        AdjList<E> adj = findAdjList(origin);
        if (adj == null) return false; // si el origen no existe, no puede haber arista

        // Se recorre la lista de aristas del origen buscando "destination".
        Node<Edge<E>> current = adj.getEdges().getHead();
        while (current != null) {
            if (current.getData().getDestination().getData().equals(destination)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    // ══════════════════════════════════════════════════════════
    //  VÉRTICES ADYACENTES
    // ══════════════════════════════════════════════════════════

    @Override
    public ArrayList<E> adjacentVertices(E data) {
        // Se usa ArrayList (de java.util) solo como contenedor de
        // RESULTADO para devolver al usuario; la estructura interna
        // del grafo sigue siendo 100% ListLinked propia.
        ArrayList<E> result = new ArrayList<>();
        AdjList<E> adj = findAdjList(data);
        if (adj == null) return result; // vértice no existe -> lista vacía

        // Se recorre la lista de aristas del vértice y se extraen los
        // datos de cada vecino.
        Node<Edge<E>> current = adj.getEdges().getHead();
        while (current != null) {
            result.add(current.getData().getDestination().getData());
            current = current.next;
        }
        return result;
    }

    // ══════════════════════════════════════════════════════════
    //  RECORRIDO DFS — Depth First Search (profundidad)
    // ══════════════════════════════════════════════════════════

    /**
     * Recorrido en profundidad a partir del vértice "start".
     * Usa recursividad, que internamente actúa como una pila implícita
     * (cada llamada recursiva se apila hasta que no hay más vecinos
     * sin visitar, y entonces empieza a "desapilarse" con los retornos).
     */
    public ArrayList<E> dfs(E start) {
        ArrayList<E> visited = new ArrayList<>(); // orden de visita resultante
        if (!searchVertex(start)) return visited; // vértice inicial inválido
        dfsRecursive(start, visited);
        return visited;
    }

    /** Función auxiliar recursiva del DFS. */
    private void dfsRecursive(E data, ArrayList<E> visited) {
        // Se marca el vértice actual como visitado (se agrega al resultado).
        visited.add(data);
        AdjList<E> adj = findAdjList(data);
        if (adj == null) return;

        // Se recorren todos los vecinos del vértice actual.
        Node<Edge<E>> current = adj.getEdges().getHead();
        while (current != null) {
            E neighborData = current.getData().getDestination().getData();
            // Solo se desciende (llamada recursiva) si el vecino NO fue visitado.
            if (!visited.contains(neighborData)) {
                dfsRecursive(neighborData, visited);
            }
            current = current.next;
        }
    }

    // ══════════════════════════════════════════════════════════
    //  RECORRIDO BFS — Breadth First Search (anchura)
    // ══════════════════════════════════════════════════════════

    /**
     * Recorrido en anchura a partir del vértice "start".
     * Usa una Cola (Queue): se visitan primero todos los vecinos
     * directos antes de avanzar al siguiente nivel.
     */
    public ArrayList<E> bfs(E start) {
        ArrayList<E> visited = new ArrayList<>();
        if (!searchVertex(start)) return visited;

        // Cola auxiliar para procesar los vértices en orden de descubrimiento.
        Queue<E> queue = new LinkedList<>();
        queue.add(start);     // se encola el vértice inicial
        visited.add(start);   // y se marca como visitado de inmediato

        while (!queue.isEmpty()) {
            E current = queue.poll(); // se extrae el frente de la cola
            AdjList<E> adj = findAdjList(current);
            if (adj == null) continue;

            // Se revisan todos los vecinos del vértice actual.
            Node<Edge<E>> edgeNode = adj.getEdges().getHead();
            while (edgeNode != null) {
                E neighborData = edgeNode.getData().getDestination().getData();
                if (!visited.contains(neighborData)) {
                    visited.add(neighborData); // se marca como visitado
                    queue.add(neighborData);   // y se encola para procesar después
                }
                edgeNode = edgeNode.next;
            }
        }
        return visited;
    }

    // ══════════════════════════════════════════════════════════
    //  ALGORITMO DE DIJKSTRA — ruta más corta entre dos vértices
    // ══════════════════════════════════════════════════════════

    /**
     * Calcula la ruta más corta (según peso acumulado) entre "start" y "end"
     * usando el algoritmo de Dijkstra clásico (sin cola de prioridad,
     * con búsqueda lineal del mínimo en cada iteración).
     *
     * @return Stack con la ruta: el TOPE del stack es "end" y el FONDO es "start".
     */
    public Stack<E> dijkstra(E start, E end) {
        // Se obtiene la lista de TODOS los vértices del grafo, en el
        // mismo orden en que fueron insertados.
        ArrayList<E> allVertices = getAllVertices();
        int n = allVertices.size();

        // dist[i]: distancia mínima conocida desde "start" hasta el vértice i.
        int[] dist = new int[n];
        // prev[i]: índice del vértice anterior en el camino más corto hacia i
        // (permite reconstruir la ruta completa al final).
        int[] prev = new int[n];
        // visited[i]: indica si ya se procesó (finalizó) el vértice i.
        boolean[] visited = new boolean[n];

        // Inicialización: todas las distancias son "infinito" excepto el origen.
        for (int i = 0; i < n; i++) {
            dist[i] = Integer.MAX_VALUE;
            prev[i] = -1;
            visited[i] = false;
        }

        int startIdx = allVertices.indexOf(start);
        if (startIdx == -1) return new Stack<>(); // el vértice de inicio no existe
        dist[startIdx] = 0; // la distancia de "start" a sí mismo es 0

        // Bucle principal: en cada iteración se "cierra" (finaliza) un vértice.
        for (int iter = 0; iter < n; iter++) {
            // Se busca, entre los vértices NO visitados, el de menor distancia
            // acumulada conocida hasta el momento (selección greedy de Dijkstra).
            int u = -1;
            for (int i = 0; i < n; i++) {
                if (!visited[i] && (u == -1 || dist[i] < dist[u])) {
                    u = i;
                }
            }

            // Si no queda ningún vértice alcanzable, se detiene el algoritmo.
            if (u == -1 || dist[u] == Integer.MAX_VALUE) break;
            visited[u] = true; // se marca como finalizado

            // Relajación de aristas: se revisan los vecinos de "u" y se
            // intenta mejorar su distancia pasando por "u".
            AdjList<E> adj = findAdjList(allVertices.get(u));
            if (adj == null) continue;

            Node<Edge<E>> edgeNode = adj.getEdges().getHead();
            while (edgeNode != null) {
                E neighborData = edgeNode.getData().getDestination().getData();
                int w = edgeNode.getData().getWeight();
                int v = allVertices.indexOf(neighborData);

                // Si pasar por u mejora la distancia conocida hacia v, se actualiza.
                if (v != -1 && !visited[v] && dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                    prev[v] = u; // se recuerda que para llegar a v, se pasó por u
                }
                edgeNode = edgeNode.next;
            }
        }

        // Reconstrucción de la ruta: se parte de "end" y se retrocede
        // usando "prev" hasta llegar a "start" (o hasta -1 si no hay ruta).
        Stack<E> route = new Stack<>();
        int endIdx = allVertices.indexOf(end);

        // Si el destino no existe o quedó "inalcanzable" (infinito), no hay ruta.
        if (endIdx == -1 || dist[endIdx] == Integer.MAX_VALUE) return route;

        System.out.println("[DIJKSTRA] Distancia mínima total calculada: " + dist[endIdx]);

        int current = endIdx;
        while (current != -1) {
            route.push(allVertices.get(current)); // se apila cada vértice del camino
            current = prev[current];               // se retrocede al vértice anterior
        }
        // El primer elemento apilado fue "end" (queda en el FONDO del stack);
        // el último elemento apilado fue "start" (queda en el TOPE del stack).
        // Por lo tanto: route.peek() == start, y al hacer pop() sucesivos
        // se obtiene el camino en orden start -> ... -> end.
        return route;
    }

    // ══════════════════════════════════════════════════════════
    //  shortPath — ruta más corta como ArrayList ordenado origen→destino
    // ══════════════════════════════════════════════════════════

    /**
     * Calcula la ruta más corta entre v y z (usando Dijkstra) y la
     * devuelve como ArrayList ordenado desde el origen hasta el destino.
     */
    public ArrayList<E> shortPath(E v, E z) {
        // dijkstra() devuelve un Stack cuyo TOPE es "v" (start) y cuyo
        // FONDO es "z" (end) — ver el comentario al final de dijkstra().
        Stack<E> stack = dijkstra(v, z);
        ArrayList<E> path = new ArrayList<>();

        // Al hacer pop() sucesivos se extrae primero "v" (tope) y al
        // final "z" (fondo), exactamente en el orden origen -> destino
        // que se requiere para shortPath. No es necesario invertir nada.
        while (!stack.isEmpty()) {
            path.add(stack.pop());
        }
        return path;
    }

    // ══════════════════════════════════════════════════════════
    //  isConexo — verifica si el grafo es conexo
    // ══════════════════════════════════════════════════════════

    /**
     * Un grafo no dirigido es conexo si, partiendo de cualquier vértice,
     * se puede llegar a todos los demás siguiendo las aristas.
     * Estrategia: se hace un BFS desde el primer vértice y se comprueba
     * si la cantidad de vértices visitados es igual al total de vértices.
     */
    public boolean isConexo() {
        if (graph.isEmpty()) return true; // un grafo vacío se considera conexo (caso trivial)

        // Se toma el primer vértice insertado como punto de partida.
        E firstVertex = graph.get(0).getVertex().getData();

        // Se hace un recorrido BFS completo desde ese vértice.
        ArrayList<E> visited = bfs(firstVertex);

        // Si BFS visitó la misma cantidad de vértices que tiene el grafo,
        // significa que todos están conectados entre sí.
        return visited.size() == graph.size();
    }

    // ══════════════════════════════════════════════════════════
    //  AUXILIARES Y REPRESENTACIÓN
    // ══════════════════════════════════════════════════════════

    /** Devuelve una lista con los datos de TODOS los vértices del grafo, en orden de inserción. */
    public ArrayList<E> getAllVertices() {
        ArrayList<E> result = new ArrayList<>();
        Node<AdjList<E>> current = graph.getHead();
        while (current != null) {
            result.add(current.getData().getVertex().getData());
            current = current.next;
        }
        return result;
    }

    /** Devuelve la cantidad de vértices del grafo. */
    public int vertexCount() {
        return graph.size();
    }

    /**
     * Devuelve el peso de la arista entre origin y destination,
     * o -1 si la arista no existe. Útil para la GUI y para imprimir
     * matrices de adyacencia ponderadas con el valor real del peso.
     */
    public int getEdgeWeight(E origin, E destination) {
        AdjList<E> adj = findAdjList(origin);
        if (adj == null) return -1;

        Node<Edge<E>> current = adj.getEdges().getHead();
        while (current != null) {
            if (current.getData().getDestination().getData().equals(destination)) {
                return current.getData().getWeight();
            }
            current = current.next;
        }
        return -1;
    }

    // Representación en texto del grafo completo: cada vértice con su
    // lista de vecinos y el peso de cada conexión.
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== GRAFO (Listas de Adyacencia) ===\n");
        Node<AdjList<E>> current = graph.getHead();
        while (current != null) {
            AdjList<E> adj = current.getData();
            sb.append("  ").append(adj.getVertex()).append(" -> ");
            Node<Edge<E>> edgeNode = adj.getEdges().getHead();
            while (edgeNode != null) {
                sb.append(edgeNode.getData().getDestination());
                sb.append("(").append(edgeNode.getData().getWeight()).append(")");
                if (edgeNode.next != null) sb.append(", ");
                edgeNode = edgeNode.next;
            }
            sb.append("\n");
            current = current.next;
        }
        return sb.toString();
    }
}