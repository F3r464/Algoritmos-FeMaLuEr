package EJER02;
//Árbol binario de búsqueda tradicional.
public class ArbolBST {
    private NodoBST raiz;
    //INSERCIÓN.
    public void insertar(int dato){
        raiz = insertar(raiz, dato);
    }
    private NodoBST insertar(NodoBST nodo, int dato){
        //Insertamos normalmente.
        if (nodo == null){
            return new NodoBST(dato);
        }
        if (dato < nodo.dato){
            nodo.izquierdo = insertar(nodo.izquierdo, dato);
        } else if (dato > nodo.dato){
            nodo.derecho = insertar(nodo.derecho, dato);
        }
        return nodo;
    }
    //BÚSQUEDA.
    public boolean buscar(int dato){
        return buscar(raiz, dato);
    }
    private boolean buscar(NodoBST nodo, int dato){
        if (nodo == null) return false;
        if (dato == nodo.dato) return true;
        if (dato < nodo.dato){
            return buscar(nodo.izquierdo, dato);
        } else {
            return buscar(nodo.derecho, dato);
        }
    }
    //ALTURA.
    public int altura(){
        return altura(raiz);
    }
    private int altura(NodoBST nodo){
        if (nodo == null) return 0;
        return 1 + Math.max(
                altura(nodo.izquierdo),
                altura(nodo.derecho)
        );
    }
    //RECORRIDO INORDEN.
    public void inorden(){
        inorden(raiz);
        System.out.println();
    }
    private void inorden(NodoBST nodo){
        if (nodo == null) return;
        inorden(nodo.izquierdo);
        System.out.print(nodo.dato + " ");
        inorden(nodo.derecho);
    }
    //MOSTRAR ÁRBOL.
    public void mostrarArbol(){
        System.out.println("\nÁrbol BST:\n");
        mostrarArbol(raiz, 0);
    }
    private void mostrarArbol(NodoBST nodo, int espacio){
        if (nodo == null) return;
        espacio += 8;
        mostrarArbol(nodo.derecho, espacio);
        System.out.println();

        for (int i = 8; i < espacio; i++){
            System.out.print(" ");
        }
        System.out.println(nodo.dato);
        mostrarArbol(nodo.izquierdo, espacio);
    }
}