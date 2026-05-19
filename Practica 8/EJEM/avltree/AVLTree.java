package avltree;
 
import bstree.BSTree;
import bstree.Node;
import exceptions.ItemDuplicated;
import exceptions.ItemNotFound;
 
/**
 * ════════════════════════════════════════════════════════════════
 * ÁRBOL AVL  (Adelson-Velsky & Landis, 1962)
 * ════════════════════════════════════════════════════════════════
 *
 * Herencia:  AVLTree  →  BSTree  →  Node
 *
 * Reutiliza de BSTree : search(), height(), inOrder(),
 * preOrder(), levelOrder(), minNode()
 * Redefine            : insert(), delete(), printTree()
 * Agrega              : balanceToLeft(), balanceToRight(),
 * rotateSL(), rotateSR()
 *
 * @param <E> Tipo de dato comparable
 */
public class AVLTree<E extends Comparable<E>> extends BSTree<E> {
 
    /** Indica si la última operación cambió la altura del subárbol */
    private boolean height;
 
    // ─── Constructor ────────────────────────────────────────────
    public AVLTree() {
        super();
        this.height = false;
    }
 
    // ════════════════════════════════════════════════════════════
    //  INSERCIÓN AVL  (redefine insert() de BSTree)
    // ════════════════════════════════════════════════════════════
 
    @Override
    public void insert(E x) throws ItemDuplicated {
        this.height = false;
        root = insertAVL(x, (NodeAVL<E>) this.root);
    }
 
    /**
     * Inserta x en el subárbol con raíz 'node' y rebalancea si es necesario.
     * Retorna la nueva raíz del subárbol (puede cambiar tras una rotación).
     */
    private NodeAVL<E> insertAVL(E x, NodeAVL<E> node) throws ItemDuplicated {
 
        // ── Caso base: posición encontrada, crear nuevo nodo ────
        if (node == null) {
            this.height = true;
            return new NodeAVL<>(x);
        }
 
        int cmp = x.compareTo(node.getData());
 
        if (cmp == 0) {
            throw new ItemDuplicated(x + " ya existe en el árbol AVL.");
        }
 
        // ── Insertar por la DERECHA (valor mayor) ───────────────
        if (cmp > 0) {
            node.setRight(insertAVL(x, node.getRight()));
 
            if (this.height) {
                switch (node.bf) {
                    case -1:
                        // El árbol recupera el equilibrio
                        node.bf     = 0;
                        this.height = false;
                        break;
                    case 0:
                        // El lado derecho creció un nivel
                        node.bf     = 1;
                        this.height = true;
                        break;
                    case 1:
                        // bf llega a 2 → DESBALANCE hacia la derecha
                        System.out.println("  [AVL] Desbalance DERECHA en nodo " + node.getData());
                        node        = balanceToLeft(node);
                        this.height = false;
                        break;
                }
            }
 
        // ── Insertar por la IZQUIERDA (valor menor) ─────────────
        } else {
            node.setLeft(insertAVL(x, node.getLeft()));
 
            if (this.height) {
                switch (node.bf) {
                    case 1:
                        // El árbol recupera el equilibrio
                        node.bf     = 0;
                        this.height = false;
                        break;
                    case 0:
                        // El lado izquierdo creció un nivel
                        node.bf     = -1;
                        this.height = true;
                        break;
                    case -1:
                        // bf llega a -2 → DESBALANCE hacia la izquierda
                        System.out.println("  [AVL] Desbalance IZQUIERDA en nodo " + node.getData());
                        node        = balanceToRight(node);
                        this.height = false;
                        break;
                }
            }
        }
 
        return node;
    }
 
    // ════════════════════════════════════════════════════════════
    //  BALANCE HACIA LA IZQUIERDA  →  corrige bf = +2
    //  El subárbol DERECHO creció demasiado.
    //  Aplica RSL (caso DD) o rotación doble DI (caso DI).
    // ════════════════════════════════════════════════════════════
 
    private NodeAVL<E> balanceToLeft(NodeAVL<E> node) {
        NodeAVL<E> hijo = node.getRight();
 
        switch (hijo.bf) {
 
            case 1:
                // ── Caso Derecha-Derecha → Rotación Simple Izquierda (RSL) ──
                System.out.println("  [AVL] Caso DD → RSL en nodo " + node.getData());
                node.bf = 0;
                hijo.bf = 0;
                node    = rotateSL(node);
                break;
 
            case -1:
                // ── Caso Derecha-Izquierda → Rotación Doble DI ──────────────
                System.out.println("  [AVL] Caso DI → Rotación Doble en nodo " + node.getData());
                NodeAVL<E> nieto = hijo.getLeft();
                // Ajustar factores de equilibrio según el bf del nieto
                switch (nieto.bf) {
                    case -1: node.bf =  0; hijo.bf =  1; break;
                    case  0: node.bf =  0; hijo.bf =  0; break;
                    case  1: node.bf = -1; hijo.bf =  0; break;
                }
                nieto.bf = 0;
                // 1ra rotación: RSR sobre el hijo derecho
                node.setRight(rotateSR(hijo));
                // 2da rotación: RSL sobre el nodo desbalanceado
                node = rotateSL(node);
                break;
 
            case 0:
                // ── Solo ocurre en eliminación ───────────────────────────────
                node.bf     =  1;
                hijo.bf     = -1;
                node        = rotateSL(node);
                this.height = false;
                break;
        }
 
        return node;
    }
 
    // ════════════════════════════════════════════════════════════
    //  BALANCE HACIA LA DERECHA  →  corrige bf = -2
    //  El subárbol IZQUIERDO creció demasiado.
    //  Aplica RSR (caso II) o rotación doble ID (caso ID).
    // ════════════════════════════════════════════════════════════
 
    private NodeAVL<E> balanceToRight(NodeAVL<E> node) {
        NodeAVL<E> hijo = node.getLeft();
 
        switch (hijo.bf) {
 
            case -1:
                // ── Caso Izquierda-Izquierda → Rotación Simple Derecha (RSR) ──
                System.out.println("  [AVL] Caso II → RSR en nodo " + node.getData());
                node.bf = 0;
                hijo.bf = 0;
                node    = rotateSR(node);
                break;
 
            case 1:
                // ── Caso Izquierda-Derecha → Rotación Doble ID ──────────────
                System.out.println("  [AVL] Caso ID → Rotación Doble en nodo " + node.getData());
                NodeAVL<E> nieto = hijo.getRight();
                // Ajustar factores de equilibrio según el bf del nieto
                switch (nieto.bf) {
                    case  1: node.bf =  0; hijo.bf = -1; break;
                    case  0: node.bf =  0; hijo.bf =  0; break;
                    case -1: node.bf =  1; hijo.bf =  0; break;
                }
                nieto.bf = 0;
                // 1ra rotación: RSL sobre el hijo izquierdo
                node.setLeft(rotateSL(hijo));
                // 2da rotación: RSR sobre el nodo desbalanceado
                node = rotateSR(node);
                break;
 
            case 0:
                // ── Solo ocurre en eliminación ───────────────────────────────
                node.bf     = -1;
                hijo.bf     =  1;
                node        = rotateSR(node);
                this.height = false;
                break;
        }
 
        return node;
    }
 
    // ════════════════════════════════════════════════════════════
    //  ROTACIÓN SIMPLE IZQUIERDA  (RSL)
    // ════════════════════════════════════════════════════════════
 
    private NodeAVL<E> rotateSL(NodeAVL<E> node) {
        System.out.println("  [AVL] RSL en nodo " + node.getData());
        NodeAVL<E> p = node.getRight();
        node.setRight(p.getLeft());
        p.setLeft(node);
        return p;
    }
 
    // ════════════════════════════════════════════════════════════
    //  ROTACIÓN SIMPLE DERECHA  (RSR)
    // ════════════════════════════════════════════════════════════
 
    private NodeAVL<E> rotateSR(NodeAVL<E> node) {
        System.out.println("  [AVL] RSR en nodo " + node.getData());
        NodeAVL<E> p = node.getLeft();
        node.setLeft(p.getRight());
        p.setRight(node);
        return p;
    }
 
    // ════════════════════════════════════════════════════════════
    //  ELIMINACIÓN AVL  (redefine delete() de BSTree)
    // ════════════════════════════════════════════════════════════
 
    @Override
    public void delete(E x) throws ItemNotFound {
        this.height = false;
        root = deleteAVL(x, (NodeAVL<E>) root);
    }
 
    private NodeAVL<E> deleteAVL(E x, NodeAVL<E> node) throws ItemNotFound {
        if (node == null) {
            throw new ItemNotFound(x + " no encontrado.");
        }
 
        int cmp = x.compareTo(node.getData());
 
        if (cmp < 0) {
            // ── Buscar y eliminar en el subárbol IZQUIERDO ──────
            node.setLeft(deleteAVL(x, node.getLeft()));
            if (this.height) {
                node = rebalanceAfterDeleteRight(node); // Corregido: Lado izquierdo pierde -> rebalancea derecho
            }
 
        } else if (cmp > 0) {
            // ── Buscar y eliminar en el subárbol DERECHO ────────
            node.setRight(deleteAVL(x, node.getRight()));
            if (this.height) {
                node = rebalanceAfterDeleteLeft(node);  // Corregido: Lado derecho pierde -> rebalancea izquierdo
            }
 
        } else {
            // ── Nodo encontrado → aplicar caso BST ──────────────
            if (node.getLeft() == null && node.getRight() == null) {
                // Caso 1: Nodo hoja
                this.height = true;
                return null;
 
            } else if (node.getLeft() == null) {
                // Caso 2a: Solo tiene hijo derecho
                this.height = true;
                return (NodeAVL<E>) node.getRight(); // Corregido con el cast
 
            } else if (node.getRight() == null) {
                // Caso 2b: Solo tiene hijo izquierdo
                this.height = true;
                return (NodeAVL<E>) node.getLeft();  // Corregido con el cast
 
            } else {
                // Caso 3: Dos hijos → reemplazar con sucesor inorden
                NodeAVL<E> sucesor = (NodeAVL<E>) minNode(node.getRight());
                System.out.println("  [AVL] Sucesor inorden de "
                        + node.getData() + " → " + sucesor.getData());
                
                node.data = sucesor.getData(); // Corregido asignación directa a variable pública
                
                node.setRight(deleteAVL(sucesor.getData(), node.getRight()));
                if (this.height) {
                    node = rebalanceAfterDeleteLeft(node);
                }
            }
        }
 
        return node;
    }
 
    /**
     * Rebalancear cuando el subárbol IZQUIERDO perdió altura.
     * El bf del padre aumenta en 1; si llega a +2 → balanceToLeft().
     */
    private NodeAVL<E> rebalanceAfterDeleteLeft(NodeAVL<E> node) {
        switch (node.bf) {
            case -1:
                node.bf     = 0;
                this.height = true;
                break;
            case 0:
                node.bf     = 1;
                this.height = false;
                break;
            case 1:
                node = balanceToLeft(node);
                break;
        }
        return node;
    }
 
    /**
     * Rebalancear cuando el subárbol DERECHO perdió altura.
     * El bf del padre disminuye en 1; si llega a -2 → balanceToRight().
     */
    private NodeAVL<E> rebalanceAfterDeleteRight(NodeAVL<E> node) {
        switch (node.bf) {
            case 1:
                node.bf     = 0;
                this.height = true;
                break;
            case 0:
                node.bf     = -1;
                this.height = false;
                break;
            case -1:
                node = balanceToRight(node);
                break;
        }
        return node;
    }
 
    // ════════════════════════════════════════════════════════════
    //  VISUALIZACIÓN CON FACTOR DE EQUILIBRIO  (override)
    // ════════════════════════════════════════════════════════════
 
    @Override
    public void printTree() {
        System.out.println("Árbol AVL:");
        printAVL((NodeAVL<E>) root, "", true);
    }
 
    private void printAVL(NodeAVL<E> node, String prefix, boolean isRoot) {
        if (node != null) {
            System.out.println(prefix + (isRoot ? "└── " : "├── ") + node);
            if (node.getLeft() != null || node.getRight() != null) {
                printAVL(node.getLeft(),  prefix + "    ", false);
                printAVL(node.getRight(), prefix + "    ", false);
            }
        }
    }
 
    // ════════════════════════════════════════════════════════════
    //  EJERCICIO 5 – RECORRIDO POR AMPLITUD RECURSIVO (BFS)
    // ════════════════════════════════════════════════════════════
 
    /**
     * Método público: recorre el árbol nivel por nivel (BFS recursivo).
     */
    public void levelOrderAVL() {
        System.out.print("BFS (niveles): ");
        int alturaTotal = height();               
        for (int nivel = 0; nivel < alturaTotal; nivel++) {
            imprimirNivel((NodeAVL<E>) root, nivel);
        }
        System.out.println();
    }
 
    /**
     * Método auxiliar recursivo para imprimir nivel por nivel.
     */
    private void imprimirNivel(NodeAVL<E> node, int nivel) {
        if (node == null) {
            return;                               
        }
        if (nivel == 0) {
            System.out.print(node.getData() + " "); 
        } else {
            imprimirNivel(node.getLeft(),  nivel - 1);
            imprimirNivel(node.getRight(), nivel - 1);
        }
    }
 
    // ════════════════════════════════════════════════════════════
    //  EJERCICIO 6 – RECORRIDO PREORDEN
    // ════════════════════════════════════════════════════════════
 
    /**
     * Método público: inicia el recorrido preorden desde la raíz.
     */
    public void preOrderAVL() {
        System.out.print("PreOrden AVL: ");
        preOrdenRecursivo((NodeAVL<E>) root);
        System.out.println();
    }
 
    /**
     * Método auxiliar recursivo del preorden.
     */
    private void preOrdenRecursivo(NodeAVL<E> node) {
        if (node == null) {
            return;
        }
        System.out.print(node.getData() + " "); 
        preOrdenRecursivo(node.getLeft());       
        preOrdenRecursivo(node.getRight());      
    }
 
    // ─── Getter de raíz para pruebas externas ───────────────────
    public NodeAVL<E> getRoot() {
        return (NodeAVL<E>) root;
    }
}