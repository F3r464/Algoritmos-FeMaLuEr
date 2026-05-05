package actividad2;

public class Nodo<T>{
    public T dato;
    public Nodo<T> next;
    public Nodo(T dato){
        this.dato=dato;
        this.next= null;
    }
}
