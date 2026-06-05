package EJER.EJER02;
import ACT.ACT04.*;

public class LinkedBST<E extends Comparable<E>> implements BinarySearchTree<E>{
    public class Node{ //Clase interna Nodo.
        public E dato;
        public Node izquierda;
        public Node derecha;

        public Node(E dato){
            this(dato, null, null);
        }

        public Node(E dato, Node izquierda, Node derecha){
            this.dato = dato;
            this.izquierda = izquierda;
            this.derecha = derecha;
        }
    }

    private Node raiz; //Raíz del árbol.
    public LinkedBST(){
        this.raiz = null;
    }

    public void insertar(E dato) throws ItemDuplicated{ //Insertamos.
        this.raiz = insertarRec(this.raiz, dato);
    }
    private Node insertarRec(Node actual, E dato) throws ItemDuplicated{
        if (actual == null){//Si está vacío, crea nodo nuevo.
            return new Node(dato);
        }

        int cmp = dato.compareTo(actual.dato);

        if(cmp < 0){ //Va a la izqiierda.
            actual.izquierda = insertarRec(actual.izquierda, dato);
        }

        else if(cmp > 0){ //Va a la derecha.
            actual.derecha = insertarRec(actual.derecha, dato);
        }

        else{ //Dato duplicado.
            throw new ItemDuplicated("Dato duplicado: " + dato);
        }
        return actual;
    }

    public E buscar(E dato) throws ItemNoFound{
        Node resultado = buscarRec(this.raiz, dato);
        if (resultado == null){
            throw new ItemNoFound("No existe el dato: " + dato);
        }
        return resultado.dato;
    }

    private Node buscarRec(Node actual, E dato){
        if(actual == null || dato.equals(actual.dato)){ //Caso base.
            return actual;
        }

        if (dato.compareTo(actual.dato) < 0) { //Buscar izquierda.
            return buscarRec(actual.izquierda, dato);
        }

        else { //Buscar derecha.
            return buscarRec(actual.derecha, dato);
        }
    }
    public void eliminar(E dato) throws ExceptionIsEmpty{ //Eliminar, aja.
        if (estaVacio()){//Validar árbol vacío.
            throw new ExceptionIsEmpty();
        }
        this.raiz = eliminarRec(this.raiz, dato);
    }

    private Node eliminarRec(Node actual, E dato){
        if (actual == null){
            return null;
        }

        int cmp = dato.compareTo(actual.dato);
        if (cmp < 0){ //Buscar izquierda.
            actual.izquierda = eliminarRec(actual.izquierda, dato);
        }

        else if (cmp > 0){ //Buscar derecha.
            actual.derecha = eliminarRec(actual.derecha, dato);
        }

        else { //Nodo encontrado.
            if (actual.izquierda == null){ //Solo hijo derecho
                return actual.derecha;
            }

            if (actual.derecha == null){ //Solo hijo izquierdo
                return actual.izquierda;
            }

            actual.dato = encontrarMinimo(actual.derecha); //Dos hijos.
            actual.derecha = eliminarRec(actual.derecha, actual.dato); //Elimina sucesor.
        }

        return actual;
    }

    private E encontrarMinimo(Node nodo) { //Encontramos minimo.
        if (nodo.izquierda == null) {
            return nodo.dato;
        }

        else {
            return encontrarMinimo(nodo.izquierda);
        }
    }

    public boolean estaVacio(){ //Validamos
        return this.raiz == null;
    }
 //Pedido del ejercicio 2.

    public void destruirNodos(){ //Elimina todos los nodos del árbol.
        raiz = null;
        System.out.println("Árbol eliminado correctamente");
    }

    public int contarTodosNodos(){ //Aqui contamos todos lo nodos.
        return contarTodosNodosRec(raiz);
    }

    private int contarTodosNodosRec(Node actual){
        if(actual == null){
            return 0;
        }

        return 1
                + contarTodosNodosRec(actual.izquierda)
                + contarTodosNodosRec(actual.derecha);
    }

    public int contarNodos(){ //Cuenta nodos.
        return contarNodosRec(raiz);
    }

    private int contarNodosRec(Node actual){
        if(actual == null){
            return 0;
        }

        if(actual.izquierda == null && actual.derecha == null){ //Si es hoja, no cuenta.
            return 0;
        }
        return 1
                + contarNodosRec(actual.izquierda)
                + contarNodosRec(actual.derecha);
    }

    public int altura(E x){ //Altura desde un nodo específico.
        Node nodo = buscarNodo(raiz, x);
        if(nodo == null){
            return -1; //Si no existe.
        }
        return alturaRec(nodo);
    }

    private int alturaRec(Node actual){
        if(actual == null){
            return -1;
        }
        int izquierda = alturaRec(actual.izquierda);
        int derecha = alturaRec(actual.derecha);
        return Math.max(izquierda, derecha) + 1;
    }

    private Node buscarNodo(Node actual, E dato){ //Buscar nodo auxiliar.
        if(actual == null){
            return null;
        }

        if(dato.compareTo(actual.dato) == 0){
            return actual;
        }

        if(dato.compareTo(actual.dato) < 0){
            return buscarNodo(actual.izquierda, dato);
        }

        return buscarNodo(actual.derecha, dato);
    }

    public int amplitud(int nivel){ //Cantidad de nodos en un nivel.
        return amplitudRec(raiz, nivel);
    }

    private int amplitudRec(Node actual, int nivel){
        if(actual == null){
            return 0;
        }

        if(nivel == 0){ //Nivel encontrado.
            return 1;
        }

        return amplitudRec(actual.izquierda, nivel - 1)
                + amplitudRec(actual.derecha, nivel - 1);
    }

    @Override
    public String toString(){

        return "Arbol: [" + toStringRec(raiz) + "]";
    }

    private String toStringRec(Node actual) {

        if (actual == null){
            return "";
        }

        return toStringRec(actual.izquierda) + " " + actual.dato + " " + toStringRec(actual.derecha);
    }
}