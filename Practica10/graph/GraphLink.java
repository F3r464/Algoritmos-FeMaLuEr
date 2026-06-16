package graph;

import listlinked.ListLinked;
import listlinked.Node;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Implementación del TAD Grafo NO DIRIGIDO PONDERADO
 * usando listas de adyacencia (ListLinked).
 * Implementa la interfaz Graph según el Ejercicio 3.
 */
public class GraphLink<E> implements Graph<E> {

    private ListLinked<AdjList<E>> graph;

    public GraphLink() {
        graph = new ListLinked<>();
    }

    // ══════════════════════════════════════════════════════════
    //  BÚSQUEDA INTERNA
    // ══════════════════════════════════════════════════════════

    private AdjList<E> findAdjList(E data) {
        Node<AdjList<E>> current = graph.getHead();
        while (current != null) {
            if (current.getData().getVertex().getData().equals(data)) {
                return current.getData();
            }
            current = current.next;
        }
        return null;
    }

    // ══════════════════════════════════════════════════════════
    //  INSERTAR VÉRTICE
    // ══════════════════════════════════════════════════════════

    @Override
    public void insertVertex(E data) {
        if (findAdjList(data) != null) {
            System.out.println("[AVISO] El vértice " + data + " ya existe.");
            return;
        }
        Vertex<E> vertex = new Vertex<>(data);
        graph.addLast(new AdjList<>(vertex));
    }

    // ══════════════════════════════════════════════════════════
    //  INSERTAR ARISTA
    // ══════════════════════════════════════════════════════════

    @Override
    public void insertEdge(E origin, E destination) {
        insertEdgeWeight(origin, destination, 1);
    }

    @Override
    public void insertEdgeWeight(E origin, E destination, int weight) {
        AdjList<E> v1 = findAdjList(origin);
        AdjList<E> v2 = findAdjList(destination);

        if (v1 == null) {
            System.out.println("[ERROR] Vértice origen no encontrado: " + origin);
            return;
        }
        if (v2 == null) {
            System.out.println("[ERROR] Vértice destino no encontrado: " + destination);
            return;
        }

        v1.getEdges().addLast(new Edge<>(v2.getVertex(), weight));
        v2.getEdges().addLast(new Edge<>(v1.getVertex(), weight));
    }

    // ══════════════════════════════════════════════════════════
    //  ELIMINAR VÉRTICE
    // ══════════════════════════════════════════════════════════

    @Override
    public boolean removeVertex(E data) {
        AdjList<E> target = findAdjList(data);
        if (target == null) {
            System.out.println("[AVISO] El vértice " + data + " no existe.");
            return false;
        }

        Node<Edge<E>> edgeNode = target.getEdges().getHead();
        while (edgeNode != null) {
            Vertex<E> neighbor = edgeNode.getData().getDestination();
            AdjList<E> neighborAdj = findAdjList(neighbor.getData());
            if (neighborAdj != null) {
                removeEdgeFromList(neighborAdj, data);
            }
            edgeNode = edgeNode.next;
        }

        removeAdjList(data);
        System.out.println("[OK] Vértice " + data + " eliminado.");
        return true;
    }

    private void removeEdgeFromList(AdjList<E> adjList, E destinationData) {
        ListLinked<Edge<E>> edges = adjList.getEdges();
        Node<Edge<E>> current = edges.getHead();
        while (current != null) {
            if (current.getData().getDestination().getData().equals(destinationData)) {
                edges.remove(current.getData());
                return;
            }
            current = current.next;
        }
    }

    private void removeAdjList(E data) {
        Node<AdjList<E>> prev = null;
        Node<AdjList<E>> current = graph.getHead();
        while (current != null) {
            if (current.getData().getVertex().getData().equals(data)) {
                if (prev == null) {
                    graph.remove(current.getData());
                } else {
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
        AdjList<E> v1 = findAdjList(origin);
        AdjList<E> v2 = findAdjList(destination);

        if (v1 == null || v2 == null) {
            System.out.println("[AVISO] Uno o ambos vértices no existen.");
            return false;
        }

        boolean removed1 = removeEdgeFromListByDest(v1, destination);
        boolean removed2 = removeEdgeFromListByDest(v2, origin);

        return removed1 && removed2;
    }

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
        return findAdjList(data) != null;
    }

    @Override
    public boolean searchEdge(E origin, E destination) {
        AdjList<E> adj = findAdjList(origin);
        if (adj == null) return false;

        Node<Edge<E>> current = adj.getEdges().getHead();
        while (current != null) {
            if (current.getData().getDestination().getData().equals(destination)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public ArrayList<E> adjacentVertices(E data) {
        ArrayList<E> result = new ArrayList<>();
        AdjList<E> adj = findAdjList(data);
        if (adj == null) return result;

        Node<Edge<E>> current = adj.getEdges().getHead();
        while (current != null) {
            result.add(current.getData().getDestination().getData());
            current = current.next;
        }
        return result;
    }

    // ══════════════════════════════════════════════════════════
    //  RECORRIDOS (DFS / BFS)
    // ══════════════════════════════════════════════════════════

    public ArrayList<E> dfs(E start) {
        ArrayList<E> visited = new ArrayList<>();
        if (!searchVertex(start)) return visited;
        dfsRecursive(start, visited);
        return visited;
    }

    private void dfsRecursive(E data, ArrayList<E> visited) {
        visited.add(data);
        AdjList<E> adj = findAdjList(data);
        if (adj == null) return;

        Node<Edge<E>> current = adj.getEdges().getHead();
        while (current != null) {
            E neighborData = current.getData().getDestination().getData();
            if (!visited.contains(neighborData)) {
                dfsRecursive(neighborData, visited);
            }
            current = current.next;
        }
    }

    public ArrayList<E> bfs(E start) {
        ArrayList<E> visited = new ArrayList<>();
        if (!searchVertex(start)) return visited;

        Queue<E> queue = new LinkedList<>();
        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            E current = queue.poll();
            AdjList<E> adj = findAdjList(current);
            if (adj == null) continue;

            Node<Edge<E>> edgeNode = adj.getEdges().getHead();
            while (edgeNode != null) {
                E neighborData = edgeNode.getData().getDestination().getData();
                if (!visited.contains(neighborData)) {
                    visited.add(neighborData);
                    queue.add(neighborData);
                }
                edgeNode = edgeNode.next;
            }
        }
        return visited;
    }

    // ══════════════════════════════════════════════════════════
    //  ALGORITMO DE DIJKSTRA Y CAMINOS
    // ══════════════════════════════════════════════════════════

    public Stack<E> dijkstra(E start, E end) {
        ArrayList<E> allVertices = getAllVertices();
        int n = allVertices.size();

        int[] dist = new int[n];
        int[] prev = new int[n];
        boolean[] visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            dist[i] = Integer.MAX_VALUE;
            prev[i] = -1;
            visited[i] = false;
        }

        int startIdx = allVertices.indexOf(start);
        if (startIdx == -1) return new Stack<>();
        dist[startIdx] = 0;

        for (int iter = 0; iter < n; iter++) {
            int u = -1;
            for (int i = 0; i < n; i++) {
                if (!visited[i] && (u == -1 || dist[i] < dist[u])) {
                    u = i;
                }
            }

            if (u == -1 || dist[u] == Integer.MAX_VALUE) break;
            visited[u] = true;

            AdjList<E> adj = findAdjList(allVertices.get(u));
            if (adj == null) continue;

            Node<Edge<E>> edgeNode = adj.getEdges().getHead();
            while (edgeNode != null) {
                E neighborData = edgeNode.getData().getDestination().getData();
                int w = edgeNode.getData().getWeight();
                int v = allVertices.indexOf(neighborData);

                if (v != -1 && !visited[v] && dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                    prev[v] = u;
                }
                edgeNode = edgeNode.next;
            }
        }

        Stack<E> route = new Stack<>();
        int endIdx = allVertices.indexOf(end);

        if (endIdx == -1 || dist[endIdx] == Integer.MAX_VALUE) return route;

        System.out.println("[DIJKSTRA] Distancia mínima total calculada: " + dist[endIdx]);

        int current = endIdx;
        while (current != -1) {
            route.push(allVertices.get(current));
            current = prev[current];
        }

        return route;
    }

    public ArrayList<E> shortPath(E v, E z) {
        Stack<E> stack = dijkstra(v, z);
        ArrayList<E> path = new ArrayList<>();
        Stack<E> temp = new Stack<>();
        while (!stack.isEmpty()) {
            temp.push(stack.pop());
        }
        while (!temp.isEmpty()) {
            path.add(temp.pop());
        }
        return path;
    }

    public boolean isConexo() {
        if (graph.isEmpty()) return true;
        E firstVertex = graph.get(0).getVertex().getData();
        ArrayList<E> visited = bfs(firstVertex);
        return visited.size() == graph.size();
    }

    // ══════════════════════════════════════════════════════════
    //  AUXILIARES Y REPRESENTACIÓN
    // ══════════════════════════════════════════════════════════

    public ArrayList<E> getAllVertices() {
        ArrayList<E> result = new ArrayList<>();
        Node<AdjList<E>> current = graph.getHead();
        while (current != null) {
            result.add(current.getData().getVertex().getData());
            current = current.next;
        }
        return result;
    }

    public int vertexCount() {
        return graph.size();
    }

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