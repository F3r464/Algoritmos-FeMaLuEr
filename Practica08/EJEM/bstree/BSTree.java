package bstree;

import exceptions.ItemDuplicated;
import exceptions.ItemNotFound;


public class BSTree<E extends Comparable<E>> {
	 
    protected Node<E> root;   // Raíz del árbol (protected para que AVLTree acceda)
 
    public BSTree() {
        this.root = null;
    }
    
    
    // Insercion Arbol BST
    //---------------------------------------------------------------------
    public void insert(E x) throws ItemDuplicated {
        root = insert(x, root);
    }
 
    protected Node<E> insert(E x, Node<E> node) throws ItemDuplicated {
        if (node == null) {
            return new Node<>(x);
        }
        int cmp = x.compareTo(node.data);
        if (cmp < 0) {
            node.left  = insert(x, node.left);
        } else if (cmp > 0) {
            node.right = insert(x, node.right);
        } else {
            throw new ItemDuplicated(x + " ya existe en el árbol.");
        }
        return node;
    }
    //---------------------------------------------------------------------
    
    //Busqueda Arbol BST
    //---------------------------------------------------------------------
    public E search(E x) throws ItemNotFound {
        return search(x, root);
    }
 
    protected E search(E x, Node<E> node) throws ItemNotFound {
        if (node == null) {
            throw new ItemNotFound(x + " no encontrado en el árbol.");
        }
        int cmp = x.compareTo(node.data);
        if (cmp < 0)  return search(x, node.left);
        if (cmp > 0)  return search(x, node.right);
        return node.data;
    }
    //-------------------------------------------------------------------
    
    //Eliminacion Arbol BST
    //-------------------------------------------------------------------
    public void delete(E x) throws ItemNotFound {
        root = delete(x, root);
    }
 
    protected Node<E> delete(E x, Node<E> node) throws ItemNotFound {
        if (node == null) {
            throw new ItemNotFound(x + " no encontrado para eliminar.");
        }
        int cmp = x.compareTo(node.data);
        if (cmp < 0) {
            node.left  = delete(x, node.left);
        } else if (cmp > 0) {
            node.right = delete(x, node.right);
        } else {
            // Caso 1: Nodo hoja
            if (node.left == null && node.right == null) {
                return null;
            }
            // Caso 2: Un solo hijo
            if (node.left == null)  
            	return node.right;
            if (node.right == null) 
            	return node.left;
            // Caso 3: Dos hijos (Usar el menor de los Mayores, o el Mayor de los Menores)
            Node<E> sucesor = minNode(node.right);
            node.data  = sucesor.data;
            node.right = delete(sucesor.data, node.right);
        }
        return node;
    }
    // ---------------------------------------------------------------------------------------------
    
    // Devuelve el valor minimo a traves del dato node
    //----------------------------------------------------------------------------------------------
    protected Node<E> minNode(Node<E> node) {
        while (node.left != null) node = node.left;
        return node;
    }
    //----------------------------------------------------------------------------------------------
    
    //ALTURA
    //----------------------------------------------------------------------------------------------
    public int height() { return height(root); }
    
    protected int height(Node<E> node) {
        if (node == null) return 0;
        return 1 + Math.max(height(node.left), height(node.right));
    }
    
    //----------------------------------------------------------------------------------------------
    
    
    //Recorridos (InOrder(entrega valores ordenados)(izquierda/raiz/derecha), PreOrden(raiz/izquierda/derecha))
    //---------------------------------------------------------------------------------------------------------
    public void inOrder() {
        System.out.print("InOrder: ");
        inOrder(root);
        System.out.println();
    }
    
    protected void inOrder(Node<E> node) {
        if (node != null) {
            inOrder(node.left);
            System.out.print(node.data + " ");
            inOrder(node.right);
        }
    }
    //--------------------------------------------------------------------------------------------------------
    
    //Recorrido PreOrden(raiz/izquierda/derecha))
    //------------------------------------------------------------------------------------------------------------
    public void preOrder() {
        System.out.print("PreOrder: ");
        preOrder(root);
        System.out.println();
    }
    
    protected void preOrder(Node<E> node) {
        if (node != null) {
            System.out.print(node.data + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }
    //-------------------------------------------------------------------------------------------------------------
    
    
    // Recorrido por amplitud (Ejercicio 05) (BFS recursivo)
    //-----------------------------------------------------------------------------------------------------------
    public void levelOrder() {
        System.out.print("LevelOrder: ");
        int h = height();
        for (int level = 1; level <= h; level++) {
            printLevel(root, level);
        }
        System.out.println();
    }																//Practicar este punto (0.1)
 
    protected void printLevel(Node<E> node, int level) {
        if (node == null) return;
        if (level == 1) {
            System.out.print(node.data + " ");
        } else {
            printLevel(node.left,  level - 1);
            printLevel(node.right, level - 1);
        }
    }
    //--------------------------------------------------------------------------------------------------------------
    // Visualizacion en el main()
    
    public void printTree() {
        System.out.println("Árbol:");
        printTree(root, "", true);
    }
 
    protected void printTree(Node<E> node, String prefix, boolean isRoot) {
        if (node != null) {
            System.out.println(prefix + (isRoot ? "└── " : "├── ") + node);
            if (node.left != null || node.right != null) {
                printTree(node.left,  prefix + "    ", false);
                printTree(node.right, prefix + "    ", false);
            }
        }
    }
 
    public boolean isEmpty() { return root == null; }
}
   //----------------------------------------------------------------------------------------------------------------
    
    
    
