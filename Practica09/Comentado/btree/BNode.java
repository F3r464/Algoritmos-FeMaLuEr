package btree;
 
import java.util.ArrayList;
 
public class BNode<E extends Comparable<E>> {
 
    // ─── Contador global para asignar IDs únicos a cada nodo ─────────────────
    private static int globalId = 0;
 
    // ─── Atributos del nodo ───────────────────────────────────────────────────
    /** Identificador único de este nodo (se asigna al crearse). */
    public int idNode;
 
    /** Lista de claves almacenadas en el nodo (tamaño fijo = orden). */
    protected ArrayList<E> keys;
 
    /** Lista de punteros a los hijos (tamaño fijo = orden + 1). */
    protected ArrayList<BNode<E>> childs;
 
    /** Número de claves actualmente almacenadas en el nodo. */
    protected int count;
 
    // ─── Constructor ──────────────────────────────────────────────────────────
 
    public BNode(int n) {
        this.idNode = ++globalId;          // ID único, empieza en 1
        this.count  = 0;
        this.keys   = new ArrayList<>(n);
        this.childs = new ArrayList<>(n + 1);
 
        // Inicializar con null para poder usar set() en cualquier posición
        for (int i = 0; i < n; i++) {
            this.keys.add(null);
        }
        for (int i = 0; i <= n; i++) {
            this.childs.add(null);
        }
    }
    
    // ─── Métodos de consulta ──────────────────────────────────────────────────
 
    public boolean nodeFull(int maxKeys) {
        return this.count == maxKeys;
    }
 
    public boolean nodeEmpty() {
        return this.count == 0;
    }
 
    public boolean searchNode(E cl, int[] pos) {
        int i = 0;
        // Avanzar mientras la clave actual sea menor que cl
        while (i < this.count && cl.compareTo(this.keys.get(i)) > 0) {
            i++;
        }
        // ¿Encontramos la clave exactamente?
        if (i < this.count && cl.compareTo(this.keys.get(i)) == 0) {
            pos[0] = i;
            return true;
        }
        // No está: pos apunta al hijo donde debe continuar la búsqueda
        pos[0] = i;
        return false;
    }
 
    // ─── Utilidades ───────────────────────────────────────────────────────────
 
    /**
     * Reinicia el contador global de IDs.
     * Útil para pruebas unitarias donde se quiere comenzar desde 1.
     */
    public static void resetIdCounter() {
        globalId = 0;
    }
 
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nodo[id=").append(idNode).append(", keys=(");
        for (int i = 0; i < count; i++) {
            if (i > 0) sb.append(", ");
            sb.append(keys.get(i));
        }
        sb.append("), count=").append(count).append("]");
        return sb.toString();
    }
}