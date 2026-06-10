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
            // La raíz fue dividida → se crea una nueva raíz con la mediana que devolvio el push
            BNode<E> pnew = new BNode<>(orden);		//se crea un nuevo nodo donde se guaradara la nueva raiz
            pnew.count = 1;	//la cantidad de claves es uno por ser raiz
            pnew.keys.set(0, mediana); //guarda la mediana en la posicion cero del nuevo nodo
            pnew.childs.set(0, root); //El hijo izquierdo de la nueva raiz apunta a la antigua raiz
            pnew.childs.set(1, nDes); //El hijo derecho de la raiz apunta al nuevo nodo creado como Ndes
            root = pnew; //actualizamos la raiz
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
 
        boolean fl = current.searchNode(cl, pos); //verifica en la clase nodoB si esta duplicada la clave
        if (fl) {
            // Clave duplicada: no se inserta
            System.out.println("Item duplicado: " + cl);
            up = false;
            return null;
        }														
 
        // Bajar recursivamente por el hijo en pos[0]
        mediana = push(current.childs.get(pos[0]), cl); //recibe de la posicion de la clase de Bnode donde te dice la posicion a poner
 
        if (up) { //si la bandera esta abajo no hay ningun problema obvia todo eso y retorna la mediana
            if (current.nodeFull(orden - 1)) { //si esta full el nodo llama a la funcion divide 
                // Nodo lleno → dividir
                mediana = dividedNode(current, mediana, pos[0]);
            } else {
                // Hay espacio → insertar directamente
                up = false;
                putNode(current, mediana, nDes, pos[0]); //si hay espacio llama a la funcion putnode para que ordene los valores
            }
        }
        return mediana;
    }
 

     //Inserta la clave {@code cl} y el puntero derecho {@code rd} en el nodo
     //{@code current} a partir de la posición {@code k}, desplazando el resto.

    private void putNode(BNode<E> current, E cl, BNode<E> rd, int k) {
        for (int i = current.count - 1; i >= k; i--) { //for invertido para que corran un espacio, (rd es su hijo derecho)
            current.keys.set(i + 1, current.keys.get(i)); //las claves corren un espacio mas
            current.childs.set(i + 2, current.childs.get(i + 1)); //un hijo se mueve dos porque antes se movia en i+1 y como se esta moviendo a la derecha tiene que moverse una posicion mas
        }
        current.keys.set(k, cl); //adjunta la clave en la posicion deseada
        current.childs.set(k + 1, rd); //junto a su hijo derecho
        current.count++; //incrementa el contador de claves 
    }
 
    private E dividedNode(BNode<E> current, E cl, int k) {
        BNode<E> rd = nDes; //guardar el hijo que venia abajo del nodo si es que tenia
 
        // Posición de la mediana según dónde cae la nueva clave
        int posMdna;
        if (k <= orden / 2) {
            posMdna = orden / 2;	//primero divide el orden en 2 y pregunta si es mayor o igual a k,si es de la posicion 2 en adelante se pasaran al nuevo nodo
        } else {
            posMdna = orden / 2 + 1;
        }
        // Crear el nuevo nodo hermano derecho
        nDes = new BNode<>(orden);
 
        // Copiar la mitad derecha de current a nDes
        for (int i = posMdna; i < orden - 1; i++) { //de la posicion de la mediana obtenida pasamos todos los datos al nuevo nodo nDes
            nDes.keys.set(i - posMdna, current.keys.get(i));
            nDes.childs.set(i - posMdna + 1, current.childs.get(i + 1)); //como se esta diviendo tambien pasas a los hijos derechos desde la posicion 1
            current.childs.set(i+1,null);
            current.keys.set(i,null);
        }
        nDes.count    = (orden - 1) - posMdna; //se actualiza los datos del nuevo nodo creado
        current.count = posMdna; //se actualiza la cantidad de claves que hay en el nodo que lo sacamos
 
        // Insertar la nueva clave en el nodo correcto
        if (k <= orden / 2)							//luego de colapsar el nodo que estaba lleno y dividirlo es hora de insertar el nodo, si de la division es mayor o igual a k entonces lo añade a la izquierda
            putNode(current, cl, rd, k);
        else
            putNode(nDes, cl, rd, k - posMdna); //sino lo añade a la derecha y le resta por la posicion del bnode para resetearlo ya que ya tiene una posicion antes
 
        // Extraer la mediana
        E median = current.keys.get(current.count - 1); //extrae la mediana del nuevo nodo de la izquierda
        nDes.childs.set(0, current.childs.get(current.count)); //aca inyecta el hijo derecho de la mediana si es que tiene para que sea el hijo izquierdo del nuevo nodo creado
        current.count--; //reduce el contador
 
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
 
        int[] pos = new int[1]; //crea otro array list donde llama a la searchnode para que averigue si esta entre sus claves
        boolean found = current.searchNode(cl, pos);
 
        if (found) {
            System.out.println(cl + " se encuentra en el nodo "
                    + current.idNode + " en la posición " + pos[0]); //si esta lo llama con el idnode y la posicion que boto el searchnode
            return true;
        }
        // Descender por el hijo correspondiente
        return searchRec(current.childs.get(pos[0]), cl); //sino desciende por el hijo de la posicion que boto el searchnode y llama recursivamente para hallarlo 
    }
 
    // =========================================================================
    // BÚSQUEDA POR RANGO (Ejercicio 02)
    // =========================================================================
 
    public void searchRange(E min, E max) {
        if (min == null || max == null) {
            System.out.println("Rango inválido: los límites no pueden ser null.");
            return;
        }
        if (min.compareTo(max) > 0) { // solo funciona si el minimo es menor o igual al maximo sino manda invalido
            System.out.println("Rango inválido: min (" + min + ") > max (" + max + ").");
            return;
        }
 
        StringBuilder result = new StringBuilder(); //crea como una pizarra para que se vayan agrupando los datos en rango que pusiste y sea mas eficiente que sumando variables en string
        searchRangeRec(root, min, max, result);
 
        if (result.length() == 0)
            System.out.println("No se encontraron claves en el rango [" + min + ", " + max + "].");
        else
            System.out.println("Claves en [" + min + ", " + max + "]: " + result.toString().trim());
    }
 
    private void searchRangeRec(BNode<E> current, E min, E max, StringBuilder result) { //busqueda podada
        if (current == null) return;
 
        for (int i = 0; i < current.count; i++) {
            E key = current.keys.get(i);
 
            // Solo entrar al hijo izquierdo si puede contener claves >= min
            if (key.compareTo(min) >= 0) { //compara la clave con el minimo para ver si es posible que lo encontre para averiguarlo evitando que revise todas las claves
                searchRangeRec(current.childs.get(i), min, max, result);
            }
 
            // Procesar la clave actual si está en el rango
            if (key.compareTo(min) >= 0 && key.compareTo(max) <= 0) { //si la clave esta mayor o igual al minimo o menor o igual al maximo es por que si o si esta en el rango
                result.append(key).append(" ");
            }
 
            // Si la clave supera el máximo, no hay nada más a la derecha ya supero al maximo 
            if (key.compareTo(max) > 0) { 
                return;
            }
        }
 
        // Visitar el último hijo derecho si es que no encontro niguna clave que haya superado el maximo del rango
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
                current.keys.set(pos[0], successorKey); //colocamos el sucesor en vez de la clave a eliminar para no perder el orden de las claves
                // Ahora eliminar el sucesor de la hoja
                removeRec(current.childs.get(pos[0] + 1), successorKey, current, pos[0] + 1); //volvemos a llamar a la funcion para eliminar el sucesor de abajo que se quedo
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
        int minKeys = (int) Math.ceil(orden / 2.0) - 1; //math.celi redondea un numero es el techo para saber el numero de claves minimas (m/2)-1
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
 
    private void mergeNodes(BNode<E> left, BNode<E> parent,int parentIdx, BNode<E> right) {
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
        if (isEmpty()) return 0; //como la altura es igual por que el arbol B esta equilibrado solo baja todo a la izquierda para hallar la altura del arbol
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
        for (int i = 0; i <= node.count; i++) { //cuenta todas las claves que hay en toda la estructura
            total += sizeRec(node.childs.get(i));
        }
        return total;
    }
}