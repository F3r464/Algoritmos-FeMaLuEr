package btree;
import exceptions.TreeException;

public class BTree<E extends Comparable<E>>{
    private BNode<E> root; //Raíz del árbol.
    private final int orden; //Orden del árbol B.
    private boolean up; //Indica si una clave debe subir.
    private BNode<E> nDes; //Nuevo nodo derecho generado al dividir.
    public BTree(int orden){
        this.orden = orden;
        this.root  = null; //El árbol inicia vacío.
    }
    public boolean isEmpty(){

        return root == null; //Verifica si existe raíz.
    }

    public void insert(E cl) throws TreeException {
        if (cl == null)
            throw new TreeException("No se puede insertar una clave nula.");
        up = false; //Reinicia el estado de promoción.
        E mediana = push(root, cl);
        if (up) {
            //Si la raíz se divide, se crea una nueva raíz.
            BNode<E> pnew = new BNode<>(orden);
            pnew.count = 1;
            pnew.keys.set(0, mediana);
            pnew.childs.set(0, root);
            pnew.childs.set(1, nDes);
            root = pnew;
        }
    }

    private E push(BNode<E> current, E cl){
        int[] pos = new int[1];
        E mediana;
        if (current == null){
            //Llegamos a una hoja, la clave debe insertarse aquí.
            up   = true;
            nDes = null;
            return cl;
        }
        boolean fl = current.searchNode(cl, pos);
        if (fl){
            //No se permiten claves repetidas.
            System.out.println("Item duplicado: " + cl);
            up = false;
            return null;
        }
        //Continúa buscando por el hijo correspondiente.
        mediana = push(current.childs.get(pos[0]), cl);
        if (up) {
            if (current.nodeFull(orden - 1)) {
                //Si está lleno, se divide el nodo.
                mediana = dividedNode(current, mediana, pos[0]);
            } else {
                //Si hay espacio, se inserta normalmente.
                up = false;
                putNode(current, mediana, nDes, pos[0]);
            }
        }
        return mediana;
    }

    private void putNode(BNode<E> current, E cl, BNode<E> rd, int k){
        //Mover claves e hijos una posición a la derecha.
        for (int i = current.count - 1; i >= k; i--) {
         
            current.keys.set(i + 1,  current.keys.get(i));
            current.childs.set(i + 2, current.childs.get(i + 1));
        }
        //Insertar la nueva clave.
        current.keys.set(k, cl);
        // Conectar el hijo derecho correspondiente.
        current.childs.set(k + 1, rd);
        current.count++;
    }

    private E dividedNode(BNode<E> current, E cl, int k) {
        BNode<E> rd = nDes;
        //Determina dónde quedará la mediana.
        int posMdna = (k <= orden / 2) ? orden / 2 : orden / 2 + 1;
        //Crear el nuevo nodo derecho.
        nDes = new BNode<>(orden);
        // Mover la mitad derecha al nuevo nodo.
        for (int i = posMdna; i < orden - 1; i++) {
            nDes.keys.set(i - posMdna,       current.keys.get(i));
            nDes.childs.set(i - posMdna + 1, current.childs.get(i + 1));
        }
        //Actualizar cantidades de claves.
        nDes.count    = (orden - 1) - posMdna;
        current.count = posMdna;
        //Insertar la nueva clave en el lado correcto.
        if (k <= orden / 2)
            putNode(current, cl, rd, k);
        else
            putNode(nDes, cl, rd, k - posMdna);
        // La mediana será promovida al padre.
        E median = current.keys.get(current.count - 1);
        //Conectar el primer hijo del nuevo nodo.
        nDes.childs.set(0, current.childs.get(current.count));
        current.count--;
        up = true; //Indica que la mediana debe subir.
        return median;
    }

    @Override
    public String toString() {
        if (isEmpty()) return "El árbol B está vacío.";
        StringBuilder sb = new StringBuilder();

        //Encabezado de la tabla.
        String fmt = "%-8s | %-22s | %-10s | %s%n";
        sb.append(String.format(fmt, "Id.Nodo", "Claves Nodo", "Id.Padre", "Id.Hijos"));
        sb.append("-".repeat(70)).append("\n");

        writeTree(root, null, sb);
        return sb.toString();
    }

    private void writeTree(BNode<E> current, BNode<E> parent, StringBuilder sb){
        if (current == null) return;
        //Claves Nodo (k1, k2, ...)
        StringBuilder claves = new StringBuilder("(");
        for (int i = 0; i < current.count; i++) {
            if (i > 0) claves.append(", ");
            claves.append(current.keys.get(i));
        }
        claves.append(")");

        //si es raíz, [id] si tiene padre.
        String idPadre = (parent == null) ? "--" : "[" + parent.idNode + "]";

        //si es hoja, [id1, id2, ...] si tiene hijos.
        StringBuilder hijos = new StringBuilder();
        for (int i = 0; i <= current.count; i++) {
            if (current.childs.get(i) != null) {
                if (hijos.length() > 0) hijos.append(", ");
                hijos.append(current.childs.get(i).idNode);
            }
        }
        String idHijos = (hijos.length() > 0) ? "[" + hijos + "]" : "--";
        //Fila de la tabla.
        sb.append(String.format("%-8d | %-22s | %-10s | %s%n",
                current.idNode, claves.toString(), idPadre, idHijos));
        //Recursión preorder, primero el nodo, luego sus hijos de izq a der.
        for (int i = 0; i <= current.count; i++) {
            writeTree(current.childs.get(i), current, sb);
        }
    }
}