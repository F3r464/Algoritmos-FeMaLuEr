// Clase que representa un nodo de B+ Tree
class BPlusNode {
    int[] keys;             // Claves del nodo
    BPlusNode[] children;   // Apuntadores a hijos (solo si no es hoja)
    boolean isLeaf;         // Indica si el nodo es hoja
    int numKeys;            // Número actual de claves
    BPlusNode next;         // Apuntador al siguiente nodo hoja

    // Constructor
    BPlusNode(int t, boolean leaf) {
        keys = new int[2 * t - 1];        // Máximo de claves = 2t-1
        children = new BPlusNode[2 * t];  // Máximo de hijos = 2t
        isLeaf = leaf;
        numKeys = 0;
        next = null;
    }
}

// Clase principal del B+ Tree
class BPlusTree {
    BPlusNode root; // Raíz del árbol
    int t;          // Grado mínimo

    // Constructor
    BPlusTree(int t) {
        this.root = new BPlusNode(t, true); // Inicialmente la raíz es hoja
        this.t = t;
    }

    // ----------------------
    // Búsqueda paso a paso
    // ----------------------
    public BPlusNode search(int key, BPlusNode node) {
        int i = 0;
        // Avanza mientras la clave sea mayor que las del nodo
        while (i < node.numKeys && key > node.keys[i]) i++;

        // Si encontramos la clave, la retornamos
        if (i < node.numKeys && key == node.keys[i]) return node;

        // Si es hoja y no encontramos, no existe
        if (node.isLeaf) return null;

        // Recurre al hijo correspondiente
        return search(key, node.children[i]);
    }

    // ----------------------
    // Inserción paso a paso
    // ----------------------
    public void insert(int key) {
        BPlusNode r = root;
        // Si la raíz está llena, se divide
        if (r.numKeys == 2 * t - 1) {
            BPlusNode s = new BPlusNode(t, false); // Nueva raíz
            root = s;
            s.children[0] = r;
            splitChild(s, 0, r);
            insertNonFull(s, key);
        } else {
            insertNonFull(r, key);
        }
    }

    // Inserta en un nodo que no está lleno
    private void insertNonFull(BPlusNode node, int key) {
        int i = node.numKeys - 1;

        if (node.isLeaf) {
            // Desplaza las claves mayores para abrir espacio
            while (i >= 0 && key < node.keys[i]) {
                node.keys[i + 1] = node.keys[i];
                i--;
            }
            node.keys[i + 1] = key;
            node.numKeys++;
        } else {
            // Encuentra el hijo correcto
            while (i >= 0 && key < node.keys[i]) i--;
            i++;
            // Si el hijo está lleno, se divide
            if (node.children[i].numKeys == 2 * t - 1) {
                splitChild(node, i, node.children[i]);
                if (key > node.keys[i]) i++;
            }
            insertNonFull(node.children[i], key);
        }
    }

    // ----------------------
    // División de un hijo lleno
    // ----------------------
    private void splitChild(BPlusNode parent, int index, BPlusNode child) {
        BPlusNode newNode = new BPlusNode(t, child.isLeaf);
        newNode.numKeys = t - 1;

        // Copia las últimas t-1 claves del hijo al nuevo nodo
        for (int j = 0; j < t - 1; j++) {
            newNode.keys[j] = child.keys[j + t];
        }

        // Si no es hoja, copia también los hijos
        if (!child.isLeaf) {
            for (int j = 0; j < t; j++) {
                newNode.children[j] = child.children[j + t];
            }
        }

        // Reduce el número de claves del hijo original
        child.numKeys = t - 1;

        // Ajusta los hijos del padre
        for (int j = parent.numKeys; j >= index + 1; j--) {
            parent.children[j + 1] = parent.children[j];
        }
        parent.children[index + 1] = newNode;

        // Mueve la clave media al padre
        for (int j = parent.numKeys - 1; j >= index; j--) {
            parent.keys[j + 1] = parent.keys[j];
        }
        parent.keys[index] = child.keys[t - 1];
        parent.numKeys++;

        // Ajusta punteros de hojas
        if (child.isLeaf) {
            newNode.next = child.next;
            child.next = newNode;
        }
    }

    // ----------------------
    // Recorrido de hojas paso a paso
    // ----------------------
    public void printLeaves() {
        BPlusNode current = root;
        // Ir hasta la primera hoja
        while (!current.isLeaf) current = current.children[0];

        System.out.print("Leaf nodes: ");
        while (current != null) {
            for (int i = 0; i < current.numKeys; i++) {
                System.out.print(current.keys[i] + " ");
            }
            current = current.next;
        }
        System.out.println();
    }
}

// ----------------------
// Clase Main para prueba paso a paso
// ----------------------
public class Main {
    public static void main(String[] args) {
        BPlusTree tree = new BPlusTree(3); // Grado mínimo t=3

        // Paso 1: insertar claves iniciales
        int[] keys = {10, 20, 5, 6, 12, 30, 7, 17};
        for (int key : keys) {
            System.out.println("Inserting key: " + key);
            tree.insert(key);
        }

        // Paso 2: mostrar hojas enlazadas
        System.out.println("\nRecorrido de hojas enlazadas:");
        tree.printLeaves();

        // Paso 3: búsqueda de una clave
        int searchKey = 12;
        BPlusNode result = tree.search(searchKey, tree.root);
        if (result != null) {
            System.out.println("\nClave " + searchKey + " encontrada en la hoja.");
        } else {
            System.out.println("\nClave " + searchKey + " NO encontrada.");
        }
    }
}