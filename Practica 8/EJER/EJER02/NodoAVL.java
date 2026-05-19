package EJER02;
//Cada nodo AVL almacena el dato, altura y sus hijos.
public class NodoAVL {
    public int dato;
    public int altura;
    public NodoAVL izquierdo;
    public NodoAVL derecho;
    public NodoAVL(int dato){
        this.dato = dato;
        this.altura = 1;
    }
}