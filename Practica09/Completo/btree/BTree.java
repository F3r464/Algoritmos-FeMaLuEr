package btree;
 
import exceptions.TreeException;
 
public class BTree<E extends Comparable<E>> {
 
    // Atributos 
    private BNode<E> root;    // Raíz del árbol
    private final int orden;  // Orden del árbol B (máx. de hijos por nodo)
    private boolean up;       // Señal: ¿hay una mediana que debe subir al padre?
    private BNode<E> nDes;    // Nuevo nodo derecho generado en una división
 
    //Constructor  
    public BTree(int orden) {
        this.orden = orden;
        this.root  = null;
    }
 
    // Consultas básicas
    public boolean isEmpty() {
        return root == null;
    }
 
    // =========================================================================
    // INSERCIÓN (Actividad 3.2)
    // =========================================================================
 
    public void insert(E cl) throws TreeException {
        if (cl == null)
            throw new TreeException("No se puede insertar una clave nula.");
 
        up = false;                          // Reinicia la señal de promoción
        E mediana = push(root, cl);
 
        if (up) {
            // La raíz fue dividida → se crea una nueva raíz con la mediana
            BNode<E> pnew = new BNode<>(orden);
            pnew.count = 1;
            pnew.keys.set(0, mediana);
            pnew.childs.set(0, root);
            pnew.childs.set(1, nDes);
            root = pnew;
        }
    }
 
    
     //Método recursivo auxiliar de inserción.
     //Desciende hasta la hoja adecuada y propaga divisiones hacia arriba.

    private E push(BNode<E> current, E cl) {
        int[] pos = new int[1];
        E mediana;
 
        if (current == null) {
            // Llegamos a un puntero nulo → la clave va aquí
            up   = true;
            nDes = null;
            return cl;
        }
 
        boolean fl = current.searchNode(cl, pos);
        if (fl) {
            // Clave duplicada: no se inserta
            System.out.println("Item duplicado: " + cl);
            up = false;
            return null;
        }
 
        // Bajar recursivamente por el hijo en pos[0]
        mediana = push(current.childs.get(pos[0]), cl);
 
        if (up) {
            if (current.nodeFull(orden - 1)) {
                // Nodo lleno → dividir
                mediana = dividedNode(current, mediana, pos[0]);
            } else {
                // Hay espacio → insertar directamente
                up = false;
                putNode(current, mediana, nDes, pos[0]);
            }
        }
        return mediana;
    }
 

     //Inserta la clave {@code cl} y el puntero derecho {@code rd} en el nodo
     //{@code current} a partir de la posición {@code k}, desplazando el resto.

    private void putNode(BNode<E> current, E cl, BNode<E> rd, int k) {
        for (int i = current.count - 1; i >= k; i--) {
            current.keys.set(i + 1, current.keys.get(i));
            current.childs.set(i + 2, current.childs.get(i + 1));
        }
        current.keys.set(k, cl);
        current.childs.set(k + 1, rd);
        current.count++;
    }
 
    private E dividedNode(BNode<E> current, E cl, int k) {
        BNode<E> rd = nDes;
 
        // Posición de la mediana según dónde cae la nueva clave
        int posMdna = (k <= orden / 2) ? orden / 2 : orden / 2 + 1;
 
        // Crear el nuevo nodo hermano derecho
        nDes = new BNode<>(orden);
 
        // Copiar la mitad derecha de current a nDes
        for (int i = posMdna; i < orden - 1; i++) {
            nDes.keys.set(i - posMdna, current.keys.get(i));
            nDes.childs.set(i - posMdna + 1, current.childs.get(i + 1));
        }
        nDes.count    = (orden - 1) - posMdna;
        current.count = posMdna;
 
        // Insertar la nueva clave en el nodo correcto
        if (k <= orden / 2)
            putNode(current, cl, rd, k);
        else
            putNode(nDes, cl, rd, k - posMdna);
 
        // Extraer la mediana
        E median = current.keys.get(current.count - 1);
        nDes.childs.set(0, current.childs.get(current.count));
        current.count--;
 
        up = true;   // Indicar que hay mediana por subir
        return median;
    }
 
    // =========================================================================
    // BÚSQUEDA (Ejercicio 01)
    // =========================================================================
 
    public boolean search(E cl) {
        if (cl == null) return false;
        return searchRec(root, cl);
    }
 
    private boolean searchRec(BNode<E> current, E cl) {
        if (current == null) return false;
 
        int[] pos = new int[1];
        boolean found = current.searchNode(cl, pos);
 
        if (found) {
            System.out.println(cl + " se encuentra en el nodo "
                    + current.idNode + " en la posición " + pos[0]);
            return true;
        }
        // Descender por el hijo correspondiente
        return searchRec(current.childs.get(pos[0]), cl);
    }
 
    // =========================================================================
    // BÚSQUEDA POR RANGO (Ejercicio 02)
    // =========================================================================
 
    public void searchRange(E min, E max) {
        if (min == null || max == null) {
            System.out.println("Rango inválido: los límites no pueden ser null.");
            return;
        }
        if (min.compareTo(max) > 0) {
            System.out.println("Rango inválido: min (" + min + ") > max (" + max + ").");
            return;
        }
 
        StringBuilder result = new StringBuilder();
        searchRangeRec(root, min, max, result);
 
        if (result.length() == 0)
            System.out.println("No se encontraron claves en el rango [" + min + ", " + max + "].");
        else
            System.out.println("Claves en [" + min + ", " + max + "]: " + result.toString().trim());
    }
 
    private void searchRangeRec(BNode<E> current, E min, E max, StringBuilder result) {
        if (current == null) return;
 
        for (int i = 0; i < current.count; i++) {
            E key = current.keys.get(i);
 
            // Solo entrar al hijo izquierdo si puede contener claves >= min
            if (key.compareTo(min) >= 0) {
                searchRangeRec(current.childs.get(i), min, max, result);
            }
 
            // Procesar la clave actual si está en el rango
            if (key.compareTo(min) >= 0 && key.compareTo(max) <= 0) {
                result.append(key).append(" ");
            }
 
            // Si la clave supera el máximo, no hay nada más a la derecha
            if (key.compareTo(max) > 0) {
                return;
            }
        }
 
        // Visitar el último hijo derecho
        searchRangeRec(current.childs.get(current.count), min, max, result);
    }
 
    // =========================================================================
    // ELIMINACIÓN (Ejercicio 03)
    // =========================================================================
 
    public void remove(E cl) throws TreeException {
        if (isEmpty())
            throw new TreeException("El árbol está vacío.");
        if (cl == null)
            throw new TreeException("La clave a eliminar no puede ser null.");
 
        removeRec(root, cl, null, -1);
 
        // Si la raíz quedó vacía tras una fusión, el árbol pierde un nivel
        if (root != null && root.count == 0) {
            root = root.childs.get(0);
        }
    }
 
    private void removeRec(BNode<E> current, E cl,
                           BNode<E> parent, int parentIdx) throws TreeException {
        if (current == null)
            throw new TreeException("La clave " + cl + " no existe en el árbol.");
 
        int[] pos = new int[1];
        boolean found = current.searchNode(cl, pos);
 
        if (found) {
            // ── Caso: la clave está en este nodo ──────────────────────────
            if (isLeaf(current)) {
                // Está en una hoja → eliminar directamente
                deleteKeyAt(current, pos[0]);
            } else {
                // Está en un nodo interno → reemplazar con el sucesor in-order
                BNode<E> successorLeaf = getSuccessorLeaf(current.childs.get(pos[0] + 1));
                E successorKey = successorLeaf.keys.get(0);
                current.keys.set(pos[0], successorKey);
                // Ahora eliminar el sucesor de la hoja
                removeRec(current.childs.get(pos[0] + 1), successorKey, current, pos[0] + 1);
                return;
            }
        } else {
            // ── Caso: la clave no está en este nodo → descender ──────────
            removeRec(current.childs.get(pos[0]), cl, current, pos[0]);
        }
 
        // Tras la eliminación (o retorno de la recursión), verificar mínimo
        if (parent != null) {
            fixUnderflow(current, parent, parentIdx);
        }
    }
 
    
     //Verifica si un nodo es hoja (todos sus hijos son null).

    private boolean isLeaf(BNode<E> node) {
        return node.childs.get(0) == null;
    }
 
    
     //Devuelve la hoja con el menor valor del subárbol cuya raíz es {@code node}
     //(el sucesor in-order = el más a la izquierda).
     
    private BNode<E> getSuccessorLeaf(BNode<E> node) {
        while (node.childs.get(0) != null) {
            node = node.childs.get(0);
        }
        return node;
    }
 
     //Elimina la clave en la posición {@code pos} del nodo dado,
     //desplazando las claves e hijos restantes una posición a la izquierda.

    private void deleteKeyAt(BNode<E> node, int pos) {
        for (int i = pos; i < node.count - 1; i++) {
            node.keys.set(i, node.keys.get(i + 1));
            node.childs.set(i + 1, node.childs.get(i + 2));
        }
        node.keys.set(node.count - 1, null);
        node.childs.set(node.count, null);
        node.count--;
    }
 
    private void fixUnderflow(BNode<E> current, BNode<E> parent, int parentIdx) {
        int minKeys = (int) Math.ceil(orden / 2.0) - 1;
        if (current.count >= minKeys) return;   // Todo bien
 
        // ── Intentar redistribución desde el hermano derecho ─────────────────
        if (parentIdx < parent.count) {
            BNode<E> rightSibling = parent.childs.get(parentIdx + 1);
            if (rightSibling != null && rightSibling.count > minKeys) {
                redistributeFromRight(current, parent, parentIdx, rightSibling);
                return;
            }
        }
 
        // ── Intentar redistribución desde el hermano izquierdo ───────────────
        if (parentIdx > 0) {
            BNode<E> leftSibling = parent.childs.get(parentIdx - 1);
            if (leftSibling != null && leftSibling.count > minKeys) {
                redistributeFromLeft(current, parent, parentIdx, leftSibling);
                return;
            }
        }
 
        // ── Fusión: preferir con el hermano derecho ───────────────────────────
        if (parentIdx < parent.count) {
            BNode<E> rightSibling = parent.childs.get(parentIdx + 1);
            mergeNodes(current, parent, parentIdx, rightSibling);
        } else {
            // Fusión con el hermano izquierdo
            BNode<E> leftSibling = parent.childs.get(parentIdx - 1);
            mergeNodes(leftSibling, parent, parentIdx - 1, current);
        }
    }
 
    private void redistributeFromRight(BNode<E> current, BNode<E> parent,
                                       int parentIdx, BNode<E> rightSibling) {
        // Bajar la clave del padre al final de current
        current.keys.set(current.count, parent.keys.get(parentIdx));
        current.childs.set(current.count + 1, rightSibling.childs.get(0));
        current.count++;
 
        // Subir la primera clave del hermano al padre
        parent.keys.set(parentIdx, rightSibling.keys.get(0));
 
        // Eliminar la primera clave y el primer hijo del hermano
        deleteKeyAt(rightSibling, 0);
        // Ajuste: el primer hijo ya fue movido, reorganizar hijos del hermano
        for (int i = 0; i <= rightSibling.count; i++) {
            rightSibling.childs.set(i, rightSibling.childs.get(i + 1));
        }
        rightSibling.childs.set(rightSibling.count + 1, null);
    }
 
    private void redistributeFromLeft(BNode<E> current, BNode<E> parent,
                                      int parentIdx, BNode<E> leftSibling) {
        // Hacer espacio al inicio de current
        for (int i = current.count; i > 0; i--) {
            current.keys.set(i, current.keys.get(i - 1));
            current.childs.set(i + 1, current.childs.get(i));
        }
        current.childs.set(1, current.childs.get(0));
 
        // Bajar la clave del padre (la separadora izquierda)
        current.keys.set(0, parent.keys.get(parentIdx - 1));
        current.childs.set(0, leftSibling.childs.get(leftSibling.count));
        current.count++;
 
        // Subir la última clave del hermano izquierdo al padre
        parent.keys.set(parentIdx - 1, leftSibling.keys.get(leftSibling.count - 1));
        leftSibling.keys.set(leftSibling.count - 1, null);
        leftSibling.childs.set(leftSibling.count, null);
        leftSibling.count--;
    }
 
    private void mergeNodes(BNode<E> left, BNode<E> parent, int parentIdx, BNode<E> right) {
        // Bajar la clave separadora del padre al final de left
        left.keys.set(left.count, parent.keys.get(parentIdx));
        left.childs.set(left.count + 1, right.childs.get(0));
        left.count++;
 
        // Copiar todas las claves y hijos de right a left
        for (int i = 0; i < right.count; i++) {
            left.keys.set(left.count, right.keys.get(i));
            left.childs.set(left.count + 1, right.childs.get(i + 1));
            left.count++;
        }
 
        // Eliminar la clave separadora y el puntero a right del padre
        for (int i = parentIdx; i < parent.count - 1; i++) {
            parent.keys.set(i, parent.keys.get(i + 1));
            parent.childs.set(i + 1, parent.childs.get(i + 2));
        }
        parent.keys.set(parent.count - 1, null);
        parent.childs.set(parent.count, null);
        parent.count--;
    }
 
    // =========================================================================
    // VISUALIZACIÓN (Actividad 3.3)
    // =========================================================================
 
    @Override
    public String toString() {
        if (isEmpty()) return "BTree is empty...";
        StringBuilder sb = new StringBuilder();
        String fmt = "%-8s | %-22s | %-10s | %s%n";
        sb.append(String.format(fmt, "Id.Nodo", "Claves Nodo", "Id.Padre", "Id.Hijos"));
        sb.append("-".repeat(70)).append("\n");
        writeTree(root, null, sb);
        return sb.toString();
    }
 
    private void writeTree(BNode<E> current, BNode<E> parent, StringBuilder sb) {
        if (current == null) return;
 
        // Construir representación de claves: (k1, k2, ...)
        StringBuilder claves = new StringBuilder("(");
        for (int i = 0; i < current.count; i++) {
            if (i > 0) claves.append(", ");
            claves.append(current.keys.get(i));
        }
        claves.append(")");
 
        String idPadre = (parent == null) ? "--" : "[" + parent.idNode + "]";
 
        // Construir lista de IDs de hijos
        StringBuilder hijos = new StringBuilder();
        for (int i = 0; i <= current.count; i++) {
            if (current.childs.get(i) != null) {
                if (hijos.length() > 0) hijos.append(", ");
                hijos.append(current.childs.get(i).idNode);
            }
        }
        String idHijos = (hijos.length() > 0) ? "[" + hijos + "]" : "--";
 
        sb.append(String.format("%-8d | %-22s | %-10s | %s%n",
                current.idNode, claves.toString(), idPadre, idHijos));
 
        // Preorden: visitar hijos de izquierda a derecha
        for (int i = 0; i <= current.count; i++) {
            writeTree(current.childs.get(i), current, sb);
        }
    }
 
    // =========================================================================
    // MÉTODOS DE UTILIDAD ADICIONALES
    // =========================================================================
 
    //Altura de un arbol
    public int height() {
        if (isEmpty()) return 0;
        int h = 0;
        BNode<E> current = root;
        while (current != null) {
            h++;
            current = current.childs.get(0);
        }
        return h;
    }
 
    //Claves de un arbol
    public int size() {
        return sizeRec(root);
    }
 
    private int sizeRec(BNode<E> node) {
        if (node == null) return 0;
        int total = node.count;
        for (int i = 0; i <= node.count; i++) {
            total += sizeRec(node.childs.get(i));
        }
        return total;
    }
}