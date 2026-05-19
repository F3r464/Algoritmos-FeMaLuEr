package EJER.EJER02;
//Cada nodo almacena un valor y sus hijos.
public class NodoBST{
    public int dato;          //Valor almacenado.
    public NodoBST izquierdo; //Hijo izquierdo.
    public NodoBST derecho;   //Hijo derecho.

    public NodoBST(int dato){
        this.dato = dato;
    }
}