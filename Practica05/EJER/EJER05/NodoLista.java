public class NodoLista<T>{
    T value; 
    NodoLista<T> siguiente; 
    public NodoLista(T value){
        this.value = value;
        this.siguiente = null;
    }
}
