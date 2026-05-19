package EJER.EJER02;
//Árbol AVL con balanceo automático.
public class ArbolAVL {
    private NodoAVL raiz;
    //OBTENEMOS ALTURA.
    private int obtenerAltura(NodoAVL nodo){
        return (nodo == null) ? 0 : nodo.altura;
    }
    //ACTUALIZAMOS ALTURA.
    private void actualizarAltura(NodoAVL nodo){

        nodo.altura = 1 +
                Math.max(
                        obtenerAltura(nodo.izquierdo),
                        obtenerAltura(nodo.derecho)
                );
    }
    //FACTOR DE EQUILIBRIO.
    private int factorEquilibrio(NodoAVL nodo){
        return (nodo == null)
                ? 0
                : obtenerAltura(nodo.derecho)
                - obtenerAltura(nodo.izquierdo);
    }
    //ROTACIÓN DERECHA.
    private NodoAVL rotarDerecha(NodoAVL nodo){
        NodoAVL nuevaRaiz = nodo.izquierdo;
        NodoAVL temporal = nuevaRaiz.derecho;
        nuevaRaiz.derecho = nodo;
        nodo.izquierdo = temporal;
        actualizarAltura(nodo);
        actualizarAltura(nuevaRaiz);
        return nuevaRaiz;
    }
    //ROTACIÓN IZQUIERDA.
    private NodoAVL rotarIzquierda(NodoAVL nodo){
        NodoAVL nuevaRaiz = nodo.derecho;
        NodoAVL temporal = nuevaRaiz.izquierdo;
        nuevaRaiz.izquierdo = nodo;
        nodo.derecho = temporal;
        actualizarAltura(nodo);
        actualizarAltura(nuevaRaiz);
        return nuevaRaiz;
    }
    //BALANCEO AVL.
    private NodoAVL balancear(NodoAVL nodo){
        actualizarAltura(nodo);
        int fe = factorEquilibrio(nodo);
        //Caso derecha.
        if (fe == 2){
            if (factorEquilibrio(nodo.derecho) >= 0){
                return rotarIzquierda(nodo);
            } else {
                nodo.derecho = rotarDerecha(nodo.derecho);
                return rotarIzquierda(nodo);
            }
        }

        //Caso izquierda.
        if (fe == -2){
            if (factorEquilibrio(nodo.izquierdo) <= 0){
                return rotarDerecha(nodo);
            } else {
                nodo.izquierdo = rotarIzquierda(nodo.izquierdo);
                return rotarDerecha(nodo);
            }
        }
        return nodo;
    }
    //INSERCIÓN.
    public void insertar(int dato){
        raiz = insertar(raiz, dato);
    }
    private NodoAVL insertar(NodoAVL nodo, int dato){
        if (nodo == null){
            return new NodoAVL(dato);
        }
        if (dato < nodo.dato){
            nodo.izquierdo = insertar(nodo.izquierdo, dato);
        } else if (dato > nodo.dato){
            nodo.derecho = insertar(nodo.derecho, dato);
        }
        return balancear(nodo);
    }
    //BÚSQUEDA.
    public boolean buscar(int dato){
        return buscar(raiz, dato);
    }
    private boolean buscar(NodoAVL nodo, int dato){
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
        return obtenerAltura(raiz);
    }
    //RECORRIDO INORDEN.
    public void inorden(){
        inorden(raiz);
        System.out.println();
    }
    private void inorden(NodoAVL nodo){
        if (nodo == null) return;
        inorden(nodo.izquierdo);
        System.out.print(nodo.dato + " ");
        inorden(nodo.derecho);
    }
    //MOSTRAR ÁRBOL.
    public void mostrarArbol(){
        System.out.println("\nÁrbol AVL:\n");
        mostrarArbol(raiz, 0);
    }
    private void mostrarArbol(NodoAVL nodo, int espacio){
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