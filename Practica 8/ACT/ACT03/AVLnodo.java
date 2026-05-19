package ACT.ACT03;

public class AVLnodo{
    public int dato;      //Número del ticket.
    public int altura;      //Altura del nodo.
    public AVLnodo izquierdo;  //Hijo izquierdo.
    public AVLnodo derecho;    //Hijo derecho.
    public AVLnodo(int ticket){
        this.dato = ticket;
        this.altura = 1; //Un nodo solo ya tiene altura 1.
    }
}