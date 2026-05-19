package EJER01;
//Cada ticket es un nodo con su valor, altura y sus hijos.
public class Nodo{
    public int ticket;      //Número del ticket.
    public int altura;      //Altura del nodo.
    public Nodo izquierdo;  //Hijo izquierdo.
    public Nodo derecho;    //Hijo derecho.
    public Nodo(int ticket){
        this.ticket = ticket;
        this.altura = 1; //Un nodo solo ya tiene altura 1.
    }
}